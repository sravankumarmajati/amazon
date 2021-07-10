package ac.app.base.exceptions;

import ac.app.base.constant.Code;

public class SignException extends BaseException {
    public SignException(Code code, String msg) {
        super(code, msg);
    }
}
