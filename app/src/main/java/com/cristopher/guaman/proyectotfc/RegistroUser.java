package com.cristopher.guaman.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroUser extends AppCompatActivity  implements View.OnClickListener {
    Button mibotonConfirmar, mibotonCancelar;
    EditText nombres, apellidos, email, contraseña, contraseña2;
    FirebaseFirestore mFirestore;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        //Isntanciamos las clases de fireBase
        mFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        nombres = findViewById(R.id.txt_Nombres_R);
        apellidos = findViewById(R.id.txt_Apellidos_R);
        email = findViewById(R.id.txt_Email_R);
        contraseña = findViewById(R.id.txt_Password_R);
        contraseña2 = findViewById(R.id.txt_PassConf_P);
        mibotonConfirmar = findViewById(R.id.btn_CrearCuenta_R);
        //mibotonCancelar=findViewById(R.id.btnCancelar);
        progressDialog = new ProgressDialog(this);

        //mibotonCancelar
        //IiciaCOntrol
        mibotonConfirmar.setOnClickListener(this);
    }

    private void RegistrarUsuario() {
        //Obtenemos el correo y la contraseña de las cajas de texto
        String emailUser = email.getText().toString().trim();
        String passUser = contraseña.getText().toString().trim();
        String passUser2= contraseña2.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacios
        if (TextUtils.isEmpty(emailUser)) {
            Toast.makeText(RegistroUser.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passUser)) {
            Toast.makeText(RegistroUser.this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        //verificamos que las contraseñas sean las mismas
        if (!passUser.equals(passUser2)){
            Toast.makeText(RegistroUser.this, "Sus contraseñas no coinciden intente nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando el registro...");
        progressDialog.show();

        ///creación de nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(emailUser, passUser)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistroUser.this, "Se ha registrado el usuario con el email: " + email.getText(), Toast.LENGTH_SHORT).show();
                            LimpiarDatos();
                            startActivity(new Intent(RegistroUser.this,LoginActivity.class));
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegistroUser.this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistroUser.this, "Ocurrio un error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void LimpiarDatos() {
        nombres.setText("");
        apellidos.setText("");
        email.setText("");
        contraseña.setText("");
        contraseña2.setText("");
    }

    @Override
    public void onClick(View v) {
        RegistrarUsuario();
        /*
        switch (v.getId()) {

            case R.id.btnRegistro:
                //Se invoca al metodo de creación
                RegistrarUsuario();
                break;

            case R.id.btnLogin:
                LimpiarDatos();
                break;
        }
        */
    }
}