package com.example.scriba.scribacollege.model;

/**
 * @author Ian Cunningham
 */

public class Note {

    private String content;
    private String createdAt;
    private int fileId;

    public Note(String content, String createdAt, int fileId) {
        this.content = content;
        this.createdAt = createdAt;
        this.fileId = fileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
