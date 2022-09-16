package com.cristopher.guaman.proyectotfc.ui.estudiantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cristopher.guaman.proyectotfc.databinding.FragmentEstudiantesBinding;

public class EstudiantesFragment extends Fragment {

    private FragmentEstudiantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstudiantesViewModel estudiantesViewModel =
                new ViewModelProvider(this).get(EstudiantesViewModel.class);

        binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}