package enity.request;

public class CommonRequest {
    //    功能名称
    private String FunctionName;

    //    功能代码
    private String FunctionCode;

    //    调用时间
    private String DateTime;

    //    锁具编号
    private String LockID;

    public String getFunctionName() {
        return FunctionName;
    }

    public void setFunctionName(String functionName) {
        FunctionName = functionName;
    }

    public String getFunctionCode() {
        return FunctionCode;
    }

    public void setFunctionCode(String functionCode) {
        FunctionCode = functionCode;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getLockID() {
        return LockID;
    }

    public void setLockID(String lockID) {
        LockID = lockID;
    }
}
