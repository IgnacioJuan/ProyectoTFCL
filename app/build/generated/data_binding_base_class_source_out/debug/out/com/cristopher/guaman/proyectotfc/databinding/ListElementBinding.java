// Generated by view binder compiler. Do not edit!
package com.cristopher.guaman.proyectotfc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cristopher.guaman.proyectotfc.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListElementBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView carreraTextView;

  @NonNull
  public final TextView cedulaTextView;

  @NonNull
  public final TextView correoTextView;

  @NonNull
  public final CardView cv;

  @NonNull
  public final ImageView iconImageView;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final TextView nombresTextView;

  private ListElementBinding(@NonNull CardView rootView, @NonNull TextView carreraTextView,
      @NonNull TextView cedulaTextView, @NonNull TextView correoTextView, @NonNull CardView cv,
      @NonNull ImageView iconImageView, @NonNull LinearLayout linearLayout,
      @NonNull TextView nombresTextView) {
    this.rootView = rootView;
    this.carreraTextView = carreraTextView;
    this.cedulaTextView = cedulaTextView;
    this.correoTextView = correoTextView;
    this.cv = cv;
    this.iconImageView = iconImageView;
    this.linearLayout = linearLayout;
    this.nombresTextView = nombresTextView;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListElementBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListElementBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_element, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListElementBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.carreraTextView;
      TextView carreraTextView = ViewBindings.findChildViewById(rootView, id);
      if (carreraTextView == null) {
        break missingId;
      }

      id = R.id.cedulaTextView;
      TextView cedulaTextView = ViewBindings.findChildViewById(rootView, id);
      if (cedulaTextView == null) {
        break missingId;
      }

      id = R.id.correoTextView;
      TextView correoTextView = ViewBindings.findChildViewById(rootView, id);
      if (correoTextView == null) {
        break missingId;
      }

      CardView cv = (CardView) rootView;

      id = R.id.iconImageView;
      ImageView iconImageView = ViewBindings.findChildViewById(rootView, id);
      if (iconImageView == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.nombresTextView;
      TextView nombresTextView = ViewBindings.findChildViewById(rootView, id);
      if (nombresTextView == null) {
        break missingId;
      }

      return new ListElementBinding((CardView) rootView, carreraTextView, cedulaTextView,
          correoTextView, cv, iconImageView, linearLayout, nombresTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
