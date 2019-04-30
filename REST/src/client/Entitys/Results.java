package client.Entitys;

/**
 * represents the http results
 */
public class Results {
    private String resultCode;
    private int resultType;
    private String message;
    private int mysqlErrorNumber;
    private String mysqlErrorMessage;
    private int httpResponseCode;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMysqlErrorNumber() {
        return mysqlErrorNumber;
    }

    public void setMysqlErrorNumber(int mysqlErrorNumber) {
        this.mysqlErrorNumber = mysqlErrorNumber;
    }

    public String getMysqlErrorMessage() {
        return mysqlErrorMessage;
    }

    public void setMysqlErrorMessage(String mysqlErrorMessage) {
        this.mysqlErrorMessage = mysqlErrorMessage;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    @Override
    public String toString() {
        return "Results{" +
                "resultCode='" + resultCode + '\'' +
                ", resultType=" + resultType +
                ", message='" + message + '\'' +
                ", mysqlErrorNumber=" + mysqlErrorNumber +
                ", mysqlErrorMessage='" + mysqlErrorMessage + '\'' +
                ", httpResponseCode=" + httpResponseCode +
                '}';
    }
}
