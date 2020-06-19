package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo;

public class MessageForView {
    private String timePost;
    private String fromContact;
    private String messageText;

    public MessageForView(String timePost, String fromContact, String messageText) {
        this.timePost = timePost;
        this.fromContact = fromContact;
        this.messageText = messageText;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public String getFromContact() {
        return fromContact;
    }

    public void setFromContact(String fromContact) {
        this.fromContact = fromContact;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
