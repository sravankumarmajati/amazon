package ac.app.base.exceptions;

import ac.app.base.constant.Code;

public class DeployException extends BaseException {
    public DeployException(Code code, String msg) {
        super(code, msg);
    }
}
