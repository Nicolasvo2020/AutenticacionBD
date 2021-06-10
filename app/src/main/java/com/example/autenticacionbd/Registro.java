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


public class Registro extends AppCompatActivity {
    EditText Nombrecompleto,EMAIL,PAS,telefono;
    Button btnRegistro;
    TextView txtAuttenticacion, txtCrearCuenta, llevarLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Nombrecompleto=findViewById(R.id.Nombrecompleto);
        EMAIL=findViewById(R.id.EMAIL);
        PAS=findViewById(R.id.PAS);
        telefono=findViewById(R.id.telefono);
        btnRegistro=findViewById(R.id.btnRegistro);
        llevarLogin=findViewById(R.id.llevarLogin);
        progressBar=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();//recibimos la instancia para realizar las operaciones

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= EMAIL.getText().toString().trim();
                String password= PAS.getText().toString().trim();
                //validamos datos que se ingresan

                if (TextUtils.isEmpty(email))
                {
                    EMAIL.setError("Se requiere el eMail");
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    PAS.setError("Se requiere el Password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Registro de Ususarios en FireBase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //si la tarea es exitosa
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Registro.this,"Usuario creado exitosamente",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Registro.this,"Se produjo un ERROR, Usuario no creado",Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });
        llevarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }
}