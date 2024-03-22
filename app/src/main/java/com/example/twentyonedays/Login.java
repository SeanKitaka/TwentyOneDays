package com.example.twentyonedays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText editusername, editpassword;
    TextView signupRedirectText;
    Button loginBtn, googleLoginBtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editusername = findViewById(R.id.login_username);
        editpassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        googleLoginBtn = findViewById(R.id.google_login_btn);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }
        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()){

                }

                else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(Login.this, Dashboard.class);
        startActivity(intent);
    }


    public Boolean validateUsername(){
        String val = editusername.getText().toString();
        if (val.isEmpty()){
            editusername.setError("Email cannot be empty");
            return false;
        } else{
            editusername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = editpassword.getText().toString();
        if (val.isEmpty()){
            editpassword.setError("password cannot be empty");
            return false;
        } else{
            editpassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String username = editusername.getText().toString().trim();
        String Password = editpassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    editusername.setError(null);
                    String passwordfromDB = snapshot.child(username).child("password").getValue(String.class);

                    if(passwordfromDB != null && passwordfromDB.equals(Password)){

                        String namefromDB = snapshot.child(username).child("name").getValue(String.class);
                        String emailfromDB = snapshot.child(username).child("email").getValue(String.class);
                        String usernamefromDB = snapshot.child(username).child("username").getValue(String.class);
                        // Correct password
                        Intent intent = new Intent(Login.this, HabitSetup.class);

                        intent.putExtra("name", namefromDB);
                        intent.putExtra("email", emailfromDB);
                        intent.putExtra("username", usernamefromDB);
                        intent.putExtra("password", passwordfromDB);

                        startActivity(intent);
                    } else{
                        // Incorrect password
                        editpassword.setError("Invalid Credentials");
                        editpassword.requestFocus();
                    }
                }
                else{
                    // User doesn't exist
                    editusername.setError("User doesn't exist");
                    editusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

}