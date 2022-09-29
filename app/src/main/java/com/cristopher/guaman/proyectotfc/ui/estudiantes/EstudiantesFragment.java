package com.cristopher.guaman.proyectotfc.ui.estudiantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cristopher.guaman.proyectotfc.databinding.FragmentEstudiantesBinding;

public class EstudiantesFragment extends Fragment {

    private FragmentEstudiantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        EstudiantesViewModel estudiantesViewModel = new ViewModelProvider(this).get(EstudiantesViewModel.class);

        binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        final TextView textCedula = binding.txtCedula;
        final TextView textNombres = binding.txtNombres;
        final TextView textCarrera = binding.txtCarrera;
        final TextView textCorreo = binding.txtCorreo;


        final EditText textCedulaBuscar = binding.txtBuscarPrac;
        final Button btnBuscar = binding.btnBuscarPract;


        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCedula::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textNombres::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCarrera::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCorreo::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}