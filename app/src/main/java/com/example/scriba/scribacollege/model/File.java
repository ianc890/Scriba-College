package com.example.scriba.scribacollege.model;

import java.io.Serializable;

/**
 * Created by Ian C on 30/04/2017.
 */

public class File implements Serializable {

    private static final long serialVersionUID = -7060210544600464481L;
    private int id;
    private String filename;
    private String filepath;

    public File(int id, String filename, String filepath) {
        this.id = id;
        this.filename = filename;
        this.filepath = filepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
