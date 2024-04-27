package calculator.enums;

import lombok.Getter;

@Getter
public enum AppMessageType {
    EXIT("exit"),
    INVALID_INPUT("잘못된 입력입니다. 숫자를 입력하세요."),
    ARITHMETIC("사칙연산"),
    CIRCLE("원의 넓이"),
    REMOVE("remove"),
    INQUIRY("inquiry")
    ;


    private final String messageName;

    AppMessageType(String message) {
        this.messageName = message;
    }
}
