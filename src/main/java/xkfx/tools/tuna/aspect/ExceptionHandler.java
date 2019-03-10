package xkfx.tools.tuna.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xkfx.tools.tuna.util.ServiceRecord;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@RestControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    private static final ThreadLocal<ServiceRecord> SERVICE_RECORD_THREAD_LOCAL = new ThreadLocal<>();

    private ResponseEntity<?> handleUnknownExceptions(Throwable e) {
        LOGGER.error("", e);
        Map<String, Object> nodes = new HashMap<>();
        nodes.put("message", "服务器内部错误！");
        return new ResponseEntity<>(nodes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 切面无法捕获Spring调用控制层方法
     * 前所抛出的异常（例如将null转化为primitive类型）
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleFrameworkExceptions(Throwable e) {
        return handleUnknownExceptions(e);
    }

    @Pointcut("execution(public org.springframework.http.ResponseEntity xkfx.tools.tuna.web.*.*(..))")
    public void controllerMethod() {}

    @Around("controllerMethod()")
    public Object handleExceptions(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Object[] parameterValue = jp.getArgs();
        SERVICE_RECORD_THREAD_LOCAL.set(new ServiceRecord(System.currentTimeMillis(), signature, parameterValue));

        ResponseEntity<?> result;
        try {
            result = (ResponseEntity<?>) jp.proceed();
        } catch (Throwable e) {
            Map<String, Object> nodes = new HashMap<>();
            if (e instanceof ValidationException) {
                nodes.put("message", e.getMessage());
                result = new ResponseEntity<>(nodes, HttpStatus.BAD_REQUEST);
            } else {
                result = handleUnknownExceptions(e);
            }
        }
        return result;
    }

    @AfterReturning(pointcut = "controllerMethod()", returning = "result")
    public void logRecord(Object result) {
        ServiceRecord record = SERVICE_RECORD_THREAD_LOCAL.get();
        if (record != null) {
            record.setResult(result);
            record.setEndTime(System.currentTimeMillis());
            LOGGER.info(record.toString());
        } else {
            LOGGER.warn("Abnormal service method, result={}", result);
        }
        SERVICE_RECORD_THREAD_LOCAL.remove();
    }
}
