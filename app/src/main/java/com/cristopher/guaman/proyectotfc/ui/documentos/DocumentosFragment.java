package com.cristopher.guaman.proyectotfc.ui.documentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cristopher.guaman.proyectotfc.databinding.FragmentDocumentosBinding;

public class DocumentosFragment extends Fragment {

    private FragmentDocumentosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DocumentosViewModel documentosViewModel =
                new ViewModelProvider(this).get(DocumentosViewModel.class);

        binding = FragmentDocumentosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        documentosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}