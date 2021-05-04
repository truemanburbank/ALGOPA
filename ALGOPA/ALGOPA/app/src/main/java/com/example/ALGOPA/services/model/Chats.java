package com.example.ALGOPA.services.model;

public class Chats {
    private String receiverId; // 받는이
    private String senderId; // 보낸이
    private String message; // 메시지 내용
    private String timestamp; // 시간
    private boolean seen; // 봤는지 안 봤는지

    public Chats(String receiverId, String senderId, String message, String timestamp, boolean seen) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = timestamp;
        this.seen = seen;
    }

    public Chats() {

    }


    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen= seen;
    }


    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
