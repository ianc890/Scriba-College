package com.example.scriba.scribacollege.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ian C on 30/03/2017.
 */

@IgnoreExtraProperties
public class Upload{

    public String name;
    public String url;
    public String uid;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url, String uid) {
        this.name = name;
        this.url= url;
        this.uid= uid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}