package com.example.scriba.scribacollege.model;

/**
 * @author Ian Cunningham
 */

public class Upload{

    public String name;
    public String url;
    public String uid;

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