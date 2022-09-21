// Generated by view binder compiler. Do not edit!
package com.cristopher.guaman.proyectotfc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cristopher.guaman.proyectotfc.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnGoogle;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final Button btnOlvPass;

  @NonNull
  public final Button btnRegistro;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final EditText txtEmail;

  @NonNull
  public final EditText txtPassword;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnGoogle,
      @NonNull Button btnLogin, @NonNull Button btnOlvPass, @NonNull Button btnRegistro,
      @NonNull ImageView imageView, @NonNull EditText txtEmail, @NonNull EditText txtPassword) {
    this.rootView = rootView;
    this.btnGoogle = btnGoogle;
    this.btnLogin = btnLogin;
    this.btnOlvPass = btnOlvPass;
    this.btnRegistro = btnRegistro;
    this.imageView = imageView;
    this.txtEmail = txtEmail;
    this.txtPassword = txtPassword;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnGoogle;
      Button btnGoogle = ViewBindings.findChildViewById(rootView, id);
      if (btnGoogle == null) {
        break missingId;
      }

      id = R.id.btnLogin;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btn_olvPass;
      Button btnOlvPass = ViewBindings.findChildViewById(rootView, id);
      if (btnOlvPass == null) {
        break missingId;
      }

      id = R.id.btnRegistro;
      Button btnRegistro = ViewBindings.findChildViewById(rootView, id);
      if (btnRegistro == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.txtEmail;
      EditText txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txtPassword;
      EditText txtPassword = ViewBindings.findChildViewById(rootView, id);
      if (txtPassword == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnGoogle, btnLogin, btnOlvPass,
          btnRegistro, imageView, txtEmail, txtPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
