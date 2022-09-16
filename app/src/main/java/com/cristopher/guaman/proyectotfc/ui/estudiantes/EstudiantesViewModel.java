package com.cristopher.guaman.proyectotfc.ui.estudiantes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstudiantesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstudiantesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}