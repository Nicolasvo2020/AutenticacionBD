package com.example.autenticacionbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btnIniciarSesion;
    EditText emailLogin, passlogin;
    ProgressBar progressBarLogin;
    FirebaseAuth fAuth;
    TextView llevarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin=findViewById(R.id.emailLogin);
        passlogin=findViewById(R.id.passlogin);
        progressBarLogin=findViewById(R.id.progressBarLogin);
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);
        llevarRegistro=findViewById(R.id.llevarRegistro);
        fAuth=FirebaseAuth.getInstance();//recibimos la instancia para realizar las operaciones

        /*if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailLogin.getText().toString().trim();
                String password= passlogin.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    emailLogin.setError("Se requiere el eMail");
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    passlogin.setError("Se requiere el Password");
                    return;
                }

                if (password.length()<6)
                {
                    passlogin.setError("El PASSWORD debe de tener al menos 6 caracteres");
                    return;
                }

                progressBarLogin.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Logeo Exitoso",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Se produjo un ERROR,No pudiste ingresar" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            progressBarLogin.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });
        llevarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registro.class));
            }
        });

    }
}