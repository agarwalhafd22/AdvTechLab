package com.example.lab1q2;

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

public class MainActivity extends AppCompatActivity {

    EditText email, phone, pass;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText=email.getText().toString();
                String phoneText=phone.getText().toString();
                String password=pass.getText().toString();

                if(emailText.isEmpty()||phoneText.isEmpty()||password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!emailText.endsWith("@gmail.com")) {
                    Toast.makeText(MainActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(phoneText.length()!=10)
                    Toast.makeText(MainActivity.this, "Enter valid phone", Toast.LENGTH_SHORT).show();
                else if(password.length()<6)
                    Toast.makeText(MainActivity.this, "Min password length should be 6", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Account created", Toast.LENGTH_SHORT).show();
            }
        });
    }
}