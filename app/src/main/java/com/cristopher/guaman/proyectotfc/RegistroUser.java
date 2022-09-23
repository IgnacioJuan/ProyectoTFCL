package com.cristopher.guaman.proyectotfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUser extends AppCompatActivity  implements View.OnClickListener {
    Button mibotonConfirmar, mibotonCancelar;
    EditText nombres, apellidos, email, contraseña, contraseña2;
    TextInputEditText password;
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

        //onTouch(RegistroUser.this,);

    }

    /*public void ShowHidePass(View view) {

        if(view.getId()==R.id.show_pass_btn){
            if(contraseña.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);
                //Show Password
                contraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);
                //Hide Password
                contraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }*/

    /*public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = contraseña.getCompoundDrawables()[2];
                if (drawable != null && motionEvent.getRawX() >= (contraseña.getRight() - drawable.getBounds().width())) {
                    if (drawable.getConstantState().equals(getResources().getDrawable(R.drawable.ic_baseline_remove_red_eye_enable).getConstantState())) {
                        contraseña.setCompoundDrawablesWithIntrinsicBounds(null,
                                null, getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24), null);
                        contraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    else {
                        contraseña.setCompoundDrawablesWithIntrinsicBounds(null,
                                null, getResources().getDrawable(R.drawable.ic_baseline_remove_red_eye_enable), null);
                        contraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    return false;
                }
                break;
        }
        return false;
    }*/





    /*private void validarCorreo(){
        Pattern pattern = Pattern.compile("(\\W|^)[\\w.\\-]{0,25}@(tecazuay)\\.edu\\.ec(\\W|$)");
        Matcher matcher = pattern.matcher(email.getText());
        if(matcher.find()){

        }

    }*/


    private void RegistrarUsuario() {
        //Obtenemos el correo y la contraseña de las cajas de texto
        String name = nombres.getText().toString().trim();
        String lastname = apellidos.getText().toString().trim();
        String emailUser = email.getText().toString().trim();
        String passUser = contraseña.getText().toString().trim();
        String passUser2= contraseña2.getText().toString().trim();

        Pattern pattern = Pattern.compile("(\\W|^)[\\w.\\-]{0,25}@(tecazuay)\\.edu\\.ec(\\W|$)");
        Matcher matcher = pattern.matcher(email.getText());

        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(lastname)&&TextUtils.isEmpty(emailUser)&&TextUtils.isEmpty(passUser)&&TextUtils.isEmpty(passUser2)){
            Toast.makeText(RegistroUser.this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(name)){
                Toast.makeText(RegistroUser.this,"Porfavor ingrese su nombre",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(lastname)){
                Toast.makeText(RegistroUser.this,"Porfavor ingrese su apellido",Toast.LENGTH_SHORT).show();
                return;
            }
            //Verificamos que las cajas de texto no esten vacios
            if (TextUtils.isEmpty(emailUser)) {
                Toast.makeText(RegistroUser.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!matcher.find()){
                Toast.makeText(RegistroUser.this, "Email no valido", Toast.LENGTH_SHORT).show();
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

            if(passUser.length()<6){
               Toast.makeText(RegistroUser.this,"La contraseña debe tener 6 o mas caracteres", Toast.LENGTH_SHORT).show();
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