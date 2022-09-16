package com.cristopher.guaman.proyectotfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button mibotonConfirmar ,mibotonLogin ,btnGoogle;
    private EditText name, email, password;
    FirebaseFirestore mFirestore;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth= FirebaseAuth.getInstance();

        email =(EditText) findViewById(R.id.txtEmail);
        password=(EditText) findViewById(R.id.txtPassword);

        mibotonConfirmar= (Button) findViewById(R.id.btnRegistro);
        mibotonLogin=(Button) findViewById(R.id.btnLogin);
        btnGoogle=(Button) findViewById(R.id.btnGoogle);

        progressDialog=new ProgressDialog(this);

        mibotonConfirmar.setOnClickListener(this);
        mibotonLogin.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        createRequest();
    }

    //Creamos la solicitud
    private void createRequest() {


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    ///Creamos la pntalla de google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //resultado devuelto al iniciar la intencion desde GoogleSingInApi.getSingIntent
        if (requestCode== RC_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                //Fue exitoso
                GoogleSignInAccount account=task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account); //se ejecuta el metodo para logearse con google
            }catch (ApiException e){
                Toast.makeText(this, e.getMessage()+" xd ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    /*
    private void RegistrarUsuario(){
        //Obtenemos el correo y la contraseña de las cajas de texto
        String emailUser=email.getText().toString().trim();
        String passUser=password.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacios
        if(TextUtils.isEmpty(emailUser)){
            Toast.makeText(LoginActivity.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passUser)){
            Toast.makeText(LoginActivity.this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando el registro...");
        progressDialog.show();

        ///creación de nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(emailUser,passUser)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Se ha registrado el usuario con el email: " + email.getText(), Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(LoginActivity.this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Ocurrio un error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    */

    private void Registro(){
        startActivity(new Intent(LoginActivity.this,RegistroUser.class));
    }

    private void LoguearUsuario() {
        //Obtenemos el correo y la contraseña de las cajas de texto
        String emailUser=email.getText().toString().trim();
        String passUser=password.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacios
        if(TextUtils.isEmpty(emailUser)){
            Toast.makeText(LoginActivity.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passUser)){
            Toast.makeText(LoginActivity.this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Ingresando al sistema...");
        progressDialog.show();

        ///Consultar si el usuario existe y loguear usuario
        firebaseAuth.signInWithEmailAndPassword(emailUser,passUser)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            Toast.makeText(LoginActivity.this, "Bienvenido " + email.getText(), Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(LoginActivity.this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Verifique que sus datos esten correctos", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegistro:
                //Se invoca al metodo de creación
                 Registro();
                break;

            case R.id.btnLogin:
                LoguearUsuario();
                break;

            case R.id.btnGoogle:
                signIn();
                break;
        }

    }

}