package com.example.diksha_encryption;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.WindowInsetsCompat;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInput;
    private TextView textViewResult;
    private Button btnEncrypt;
    private static final String SECRET_KEY = "1234567890123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.editTextText3);
        textViewResult = findViewById(R.id.textView4);
        btnEncrypt = findViewById(R.id.button2);
        // Set the button click listener
        btnEncrypt.setOnClickListener(v -> {
            String textToEncrypt = editTextInput.getText().toString();

            if (!textToEncrypt.isEmpty()) {
                try {
                    String encryptedText = encryptText(textToEncrypt);
                    textViewResult.setText("Encrypted: " + encryptedText);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error encrypting text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please enter text to encrypt", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String encryptText(String text) throws Exception {
        // Create AES Key from the SECRET_KEY string
        Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // Create and initialize the Cipher for encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the text
        byte[] encryptedTextBytes = cipher.doFinal(text.getBytes());

        // Return the encrypted text as Base64 encoded string
        return Base64.encodeToString(encryptedTextBytes, Base64.DEFAULT);

    }
}