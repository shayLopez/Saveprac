package com.example.saveprac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    Button button;
    EditText userN, password, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComp();
        PreferenceManager preferenceManager = new PreferenceManager(this);

        if (preferenceManager.isRegistered()) {
            startActivity(new Intent(this, Login.class));
            finish();
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = userN.getText().toString().trim();
                    String pass = password.getText().toString().trim();
                    String email = Email.getText().toString().trim();

                    if (username.isEmpty())
                        Toast.makeText(Register.this, "Enter a user name", Toast.LENGTH_SHORT).show();

                    else if (pass.isEmpty())
                        Toast.makeText(Register.this, "Enter a password", Toast.LENGTH_SHORT).show();

                    else if (email.isEmpty())
                        Toast.makeText(Register.this, "Enter an E-mail", Toast.LENGTH_SHORT).show();

                    else {
                        preferenceManager.saveUsername(username);
                        preferenceManager.savePassword(pass);
                        preferenceManager.saveEmail(email);
                        preferenceManager.setRegistered(true);
                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    }
                }
            });
        }
    }

    public void initComp() {
        button = findViewById(R.id.button4);
        userN = findViewById(R.id.editTextText4);
        password = findViewById(R.id.editTextText5);
        Email = findViewById(R.id.editTextText6);
    }
}