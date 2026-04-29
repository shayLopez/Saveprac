package com.example.saveprac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    EditText userN, pasoremail;
    Button button;
    CheckBox remem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initcomp();

        PreferenceManager pref = new PreferenceManager(this);
if (pref.isAutoLog())
{
    startActivity(new Intent(Login.this, MainActivity.class));
    finish();
}
else {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredUser = userN.getText().toString().trim();
                String enteredPassOrEmail = pasoremail.getText().toString().trim();

                if (enteredUser.equals(pref.getUsername()) &&
                        (enteredPassOrEmail.equals(pref.getPassword()) ||
                                enteredPassOrEmail.equals(pref.getEmail()))) {
                        if (remem.isChecked())
                            pref.setAutolog(true);

                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }

    public void initcomp() {
        remem=findViewById(R.id.checkBox);
        userN = findViewById(R.id.editTextText2);
        pasoremail = findViewById(R.id.editTextText3);
        button = findViewById(R.id.button);
    }
}