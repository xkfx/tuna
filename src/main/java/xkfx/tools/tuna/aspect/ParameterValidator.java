package xkfx.tools.tuna.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

@Aspect
@Configuration
public class ParameterValidator {

    private ExecutableValidator executableValidator;

    public ParameterValidator() {
        // 开启快速失败返回模式（顾名思义...）
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).
                configure().addProperty("hibernate.validator.fail_fast", "true" ).
                buildValidatorFactory();
        executableValidator = validatorFactory.getValidator().forExecutables();
    }

    @Pointcut("execution(* xkfx.tools.tuna.service.*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public Object validate(ProceedingJoinPoint jp) throws Throwable {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters(jp.getThis(), method, jp.getArgs());
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.iterator().next().getMessage());
        }
        return jp.proceed();
    }
}