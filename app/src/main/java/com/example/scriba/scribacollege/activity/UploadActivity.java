package com.example.scriba.scribacollege.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.constant.Constants;
import com.example.scriba.scribacollege.model.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //view objects
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonSignOut;
    private EditText editTextName;
    private TextView textViewShow;
    private ImageView imageView;

    //uri to store file
    private Uri filePath;

    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;

    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    //get current user
    final String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonSignOut = (Button) findViewById(R.id.buttonLogout);
        imageView = (ImageView) findViewById(R.id.imageView);
        editTextName = (EditText) findViewById(R.id.editText);
        textViewShow = (TextView) findViewById(R.id.textViewShow);

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(UploadActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        buttonSignOut.setOnClickListener(this);
        textViewShow.setOnClickListener(this);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                            Upload upload = new Upload(editTextName.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString(), user.toString());

                            //adding an upload to firebase database
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonChoose) {
            showFileChooser();
        } else if (view == buttonUpload) {
            uploadFile();
        } else if (view == textViewShow) {
            startActivity(new Intent(this, MyFilesActivity.class));
        } else if (view == buttonSignOut) {
            signOut();
        }
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
