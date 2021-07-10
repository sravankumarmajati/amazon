package ac.app.base.constant;

public enum Code {
    //通用部分
    SUCCESS(0, "success"),

    // 内部错误 4000 开头
    CONTRACT_INVOKE_ERROR(4000, "contract invoke failed"),
    BLOCK_QUERY_ERROR(4001, "block query failed"),
    TRANSACTION_QUERY_ERROR(4002, "transaction query failed"),
    POST_DATA_ERROR(4003, "post data failed"),
    NODE_INFO_NOT_FOUND(5201, "node info not found"),
    PREDATA_NOT_FOUND(4004, "pre data not found"),

    TX_UNSIGNED(4005, "unsigned"),
    DEPLOY_FAILED(4006, "deployed"),

    // 用户请求非法 9000开头
    INVALID_PARAM_ERROR(9200, "invalid param"),
    INVALID_PARAM_LENGTH(9201, "invalid param length"),
    INVALID_PARAM_FORMAT(9202, "invalid param format"),

    NOT_FOUND(9404, "resource not found"),
    METHOD_NOT_SUPPORT(9405, "method not support"),

    // 授权非法
    AUTH_ERROR(9300, "permission deny"),
    AUTH_FORBID(9300, "access forbid"),
    AUTH_VERIFY_ERROR(9300, "access permission verify failed"),

    // 未知错误
    UNKNOWN_ERROR(9999, "system error");

    private int code;
    private String msg;

    // 构造方法
    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

