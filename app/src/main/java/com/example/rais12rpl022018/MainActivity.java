package com.example.rais12rpl022018;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUsername, txtPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnLog);
        txtUsername = (EditText)findViewById(R.id.usm);
        txtPw = (EditText)findViewById(R.id.pswd);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                if (txtUsername.getText().toString().equalsIgnoreCase("admin") && txtPw.getText().toString().equalsIgnoreCase("pw003360")){
                Toast.makeText(getApplicationContext(), "Thank you for LOGIN!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Username/Password is Invalid", Toast.LENGTH_LONG).show();
            }

            }
        });
    };
}