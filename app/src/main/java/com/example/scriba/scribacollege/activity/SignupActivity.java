package com.example.scriba.scribacollege.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scriba.scribacollege.R;
import com.example.scriba.scribacollege.config.Config;
import com.example.scriba.scribacollege.helper.Register;
import com.example.scriba.scribacollege.model.User;
import com.example.scriba.scribacollege.util.HashUtil;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextFirstname;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button buttonRegister;

    private TextView myClickableUrl;

    private User user;
    private Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextFirstname = (EditText) findViewById(R.id.editTextFirstname);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        myClickableUrl = (TextView) findViewById(R.id.clickableURL);
        myClickableUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == buttonRegister){
                    registerUser();
                }
            }
        });
    }

    private void clearText(TextView firstname, TextView surname, TextView email, TextView username, TextView password) {
        firstname.setText("");
        surname.setText("");
        email.setText("");
        username.setText("");
        password.setText("");
    }

    private void registerUser() {
        String firstname = editTextFirstname.getText().toString();
        String surname = editTextSurname.getText().toString();
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        String encryptedPassword = HashUtil.sha256(password);

        register(firstname, surname, email, username, encryptedPassword);

        clearText(editTextFirstname, editTextSurname, editTextEmail, editTextUsername, editTextPassword);
    }

    private void register(String firstname, String surname, String email, String username, String password) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            Register reg = new Register();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignupActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                Map<String, String> data = new HashMap<String,String>();
                data.put("firstname",params[0]);
                data.put("surname",params[1]);
                data.put("email",params[2]);
                data.put("username",params[3]);
                data.put("password",params[4]);

                String result = reg.sendPostRequest(Config.REGISTER_URL,data);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(firstname, surname, email, username, password);
    }
}
