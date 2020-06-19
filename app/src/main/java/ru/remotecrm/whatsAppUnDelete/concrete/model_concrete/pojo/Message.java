package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timePost;
    private String fromContact;
    private String messageText;

    public Message(int id, long timePost, String fromContact, String messageText) {
        this.id = id;
        this.timePost = timePost;
        this.fromContact = fromContact;
        this.messageText = messageText;
    }

    @Ignore
    public Message(long timePost, String fromContact, String messageText) {
        this.timePost = timePost;
        this.fromContact = fromContact;
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimePost() {
        return timePost;
    }

    public void setTimePost(long timePost) {
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
