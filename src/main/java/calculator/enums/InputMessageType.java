package calculator.enums;

import lombok.Getter;

@Getter
public enum InputMessageType {
    EXIT("exit"),
    REMOVE("remove"),
    INQUIRY("inquiry");


    private final String messageName;

    InputMessageType(String message) {
        this.messageName = message;
    }

    public static boolean findSymbol(String messageName) {
        for (InputMessageType value : InputMessageType.values()) {
            if (messageName.equals(value.getMessageName())) {
                return true;
            }
        }
        return false;
    }
}
