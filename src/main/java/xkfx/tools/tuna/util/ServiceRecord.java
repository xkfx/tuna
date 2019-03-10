package xkfx.tools.tuna.util;

import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

public class ServiceRecord {

    private long startTime;
    private long endTime;
    private MethodSignature signature;
    private Object[] args;
    private Object result;

    public ServiceRecord(long startTime, MethodSignature signature, Object[] args) {
        this.startTime = startTime;
        this.args = args;
        this.signature = signature;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("ServiceRecord{")
                .append("signature=").append(signature)
                .append(", args=").append(Arrays.toString(args))
                .append(", result=").append(result)
                .append(", time=").append(endTime - startTime)
                .append("}").toString();
    }
}
