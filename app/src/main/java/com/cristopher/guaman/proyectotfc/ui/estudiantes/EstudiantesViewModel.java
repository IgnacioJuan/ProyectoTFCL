package com.cristopher.guaman.proyectotfc.ui.estudiantes;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EstudiantesViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;
    private MutableLiveData<String> mText;


    TextView fechaCompleta, hora, dia,mes,anio;
    EditText edtCedula;
   TextView cedula, nombres, carrera, correo;
    Button btnBuscar;

    public EstudiantesViewModel() {
        /*Date d = new Date();
        SimpleDateFormat fecc = new SimpleDateFormat("d, MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        mText = new MutableLiveData<>();
        mText.setValue(fechacComplString);*/
    }

    public LiveData<String> getText() {
        return mText;
    }

    public class SimpleAdapter extends RecyclerView.ViewHolder{

        public SimpleAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}