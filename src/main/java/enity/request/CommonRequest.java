package enity.request;

public class CommonRequest {
    //    ��������
    private String FunctionName;

    //    ���ܴ���
    private String FunctionCode;

    //    ����ʱ��
    private String DateTime;

    //    ���߱��
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
