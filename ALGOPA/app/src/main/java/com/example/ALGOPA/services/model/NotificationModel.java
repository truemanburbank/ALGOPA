package com.example.ALGOPA.services.model;

import androidx.lifecycle.MutableLiveData;

public class NotificationModel {

    public String to;
    //public MutableLiveData<Boolean> to;
    //public String to = new String();

    public Notification notification = new Notification();

    public static class Notification {
        public String title;
        public String text;

    }
}
