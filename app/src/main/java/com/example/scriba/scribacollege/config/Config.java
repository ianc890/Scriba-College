package com.example.scriba.scribacollege.config;

/**
 * Created by Ian C on 12/04/2017.
 */

public class Config {

    //URL to our login.php file

    public static final String SERVER_URL = "http://ianc.x10host.com/ScribaCollege/testing/uploads/";

    public static final String LOGIN_URL = "http://ianc.x10host.com/ScribaCollege/testing/login.php";

    public static final String UPLOAD_URL = "http://ianc.x10host.com/ScribaCollege/testing/UploadToServer.php";

    public static final String RETRIEVE_URL = "http://ianc.x10host.com/ScribaCollege/testing/RetrieveFromServer.php";

    public static final String RETRIEVE_FILES_URL = "http://ianc.x10host.com/ScribaCollege/testing/getFiles.php";

    public static final String INSERT_NOTE_URL = "http://ianc.x10host.com/ScribaCollege/testing/insertNote.php";

    public static final String RETRIEVE_NOTES_URL = "http://ianc.x10host.com/ScribaCollege/testing/getNotes.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
