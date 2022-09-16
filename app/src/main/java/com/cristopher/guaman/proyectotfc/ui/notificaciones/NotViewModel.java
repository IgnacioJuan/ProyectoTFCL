package com.cristopher.guaman.proyectotfc.ui.notificaciones;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    TextView fechaCompleta, hora, dia,mes,anio;


    public NotViewModel() {
        Date d = new Date();
        SimpleDateFormat fecc = new SimpleDateFormat("d, MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        mText = new MutableLiveData<>();
        mText.setValue(fechacComplString);

    }

    public LiveData<String> getText() {
        return mText;
    }


}