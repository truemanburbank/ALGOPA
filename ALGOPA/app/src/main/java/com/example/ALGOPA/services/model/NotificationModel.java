package com.example.ALGOPA.services.model;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;

public class NotificationModel {

    public String to;

    public Notification notification = new Notification();

    public static class Notification {
        public String title;
        public String text;

    }
}
