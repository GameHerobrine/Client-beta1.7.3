package dozer.event.impl;

import dozer.event.EventCancellable;

public class ChatEvent extends EventCancellable {

    private String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
