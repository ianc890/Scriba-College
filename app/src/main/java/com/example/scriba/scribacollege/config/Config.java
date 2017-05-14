package com.example.scriba.scribacollege.config;

/**
 * @author Ian Cunningham
 */

public class Config {

    public static final String ACCESS_TOKEN = "7efbbf4e74144cf191c50b6d2892d2ca  ";

    //URL to our login.php file

    public static final String SERVER_URL = "http://ianc.x10host.com/ScribaCollege/testing/uploads/";

    public static final String REGISTER_URL = "http://ianc.x10host.com/ScribaCollege/testing/register.php";

    public static final String LOGIN_URL = "http://ianc.x10host.com/ScribaCollege/testing/login.php";

    public static final String UPLOAD_URL = "http://ianc.x10host.com/ScribaCollege/testing/UploadToServer.php";

    public static final String RETRIEVE_URL = "http://ianc.x10host.com/ScribaCollege/testing/RetrieveFromServer.php";

    public static final String RETRIEVE_FILES_URL = "http://ianc.x10host.com/ScribaCollege/testing/getFiles.php";

    public static final String INSERT_NOTE_URL = "http://ianc.x10host.com/ScribaCollege/testing/insertNote.php";

    public static final String RETRIEVE_NOTES_URL = "http://ianc.x10host.com/ScribaCollege/testing/getNotes.php";

    public static final String INSERT_QUESTION_URL = "http://ianc.x10host.com/ScribaCollege/testing/insertQuestion.php";

    public static final String RETRIEVE_QUESTIONS_URL = "http://ianc.x10host.com/ScribaCollege/testing/getQuestions.php";

    public static final String CREATE_STUDY_PLAN_URL = "http://ianc.x10host.com/ScribaCollege/testing/createStudyPlan.php";

    public static final String RETRIEVE_STUDY_PLAN_URL = "http://ianc.x10host.com/ScribaCollege/testing/retrieveStudyPlan.php";

    public static final String RETRIEVE_SUBJECTS_URL = "http://ianc.x10host.com/ScribaCollege/testing/getSubjects.php";

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
