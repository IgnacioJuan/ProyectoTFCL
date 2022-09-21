package com.cristopher.guaman.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarActivity extends AppCompatActivity {

    private EditText gmail;
    private String correo;
    private Button recuperar;
    private ProgressDialog progress;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.);
        gmail = findViewById(R.id.txtEmailRecuperar);
        recuperar = findViewById(R.id.btn_Recuperar);

        auth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);

        getRecuperar();


    }

    private void getRecuperar(){
        recuperar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = gmail.getText().toString().trim();
                if(!correo.isEmpty()){
                    progress.setMessage("Espere un momento...");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    getEnviarCorre();
                }else{
                    Toast.makeText(RecuperarActivity.this, "El correo no se pudo enviar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getEnviarCorre(){
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RecuperarActivity.this, "Revise su correo", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RecuperarActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(RecuperarActivity.this, "No es posible enviar el correo", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}