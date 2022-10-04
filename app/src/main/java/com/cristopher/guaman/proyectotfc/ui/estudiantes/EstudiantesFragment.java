package com.cristopher.guaman.proyectotfc.ui.estudiantes;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cristopher.guaman.proyectotfc.MainActivity;
import com.cristopher.guaman.proyectotfc.R;
import com.cristopher.guaman.proyectotfc.interfaces.PracticantesAPI;
import com.cristopher.guaman.proyectotfc.models.ListAdapter;
import com.cristopher.guaman.proyectotfc.models.Practicantes;
import com.cristopher.guaman.proyectotfc.network.ApiEstudiante;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstudiantesFragment extends Fragment {

    //private FragmentEstudiantesBinding binding;
    EditText edtCedulaBuscar;
    TextView txtCedula;
    TextView txtNombres;
    TextView txtCarrera;
    TextView txtCorreo;

    private List<Practicantes> practicantes;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //EstudiantesViewModel estudiantesViewModel = new ViewModelProvider(this).get(EstudiantesViewModel.class);

        //binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        /*final TextView textCedula = binding.txtCedula;
        final TextView textNombres = binding.txtNombres;
        final TextView textCarrera = binding.txtCarrera;
        final TextView textCorreo = binding.txtCorreo;


        final EditText textCedulaBuscar = binding.txtBuscarPrac;
        final Button btnBuscar = binding.btnBuscarPract;*/


        //estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        /*estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCedula::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textNombres::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCarrera::setText);
        estudiantesViewModel.getText().observe(getViewLifecycleOwner(), textCorreo::setText);*/
        //return root;
        return inflater.inflate(R.layout.fragment_estudiantes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtCedulaBuscar = view.findViewById(R.id.txtBuscarPrac);
        /*txtCedula = view.findViewById(R.id.txtCedula);
        txtNombres = view.findViewById(R.id.txtNombres);
        txtCarrera = view.findViewById(R.id.txtCarrera);
        txtCorreo = view.findViewById(R.id.txtCorreo);*/
        Button btnBuscarPrac = view.findViewById(R.id.btnBuscarPract);

        Date d = new Date();
        SimpleDateFormat fecc = new SimpleDateFormat("d, MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        TextView textView = view.findViewById(R.id.text_gallery);
        textView.setText(fechacComplString);

        recyclerView = view.findViewById(R.id.listEstudiantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        btnBuscarPrac.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPracticantes();

                /*practicantes = new ArrayList<>();
                practicantes.add(new Practicantes("0101995892","ASTUDILLO TOCACHI MANUEL EDUARDO","TECNOLOGÍA SUPERIOR EN DESARROLLO DE SOFTWARE","eduardoeat10@gmail.com"));

                ListAdapter listAdapter = new ListAdapter(practicantes, getActivity().getApplicationContext());
                RecyclerView recyclerView = view.findViewById(R.id.listEstudiantes);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(listAdapter);*/
                //find(edtCedulaBuscar.getText().toString());

            }
        });


    }

    public void init(){

        practicantes = new ArrayList<>();
        practicantes.add(new Practicantes("0101995892","ASTUDILLO TOCACHI MANUEL EDUARDO","TECNOLOGÍA SUPERIOR EN DESARROLLO DE SOFTWARE","eduardoeat10@gmail.com"));

        //ListAdapter listAdapter = new ListAdapter(practicantes, );

    }

    public void showPracticantes(){
        Call<List<Practicantes>> call = ApiEstudiante.getStudents().create(PracticantesAPI.class).getStudents();
        call.enqueue(new Callback<List<Practicantes>>() {
            @Override
            public void onResponse(Call<List<Practicantes>> call, Response<List<Practicantes>> response) {
                if(response.isSuccessful()){
                    practicantes = response.body();
                    listAdapter = new ListAdapter(practicantes, getActivity().getApplicationContext());
                    recyclerView.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Practicantes>> call, Throwable t) {

            }
        });
    }

    private void find(String cedula){
        MainActivity main = new MainActivity();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.18.126:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        PracticantesAPI practicantesAPI = retrofit.create(PracticantesAPI.class);
        Call <Practicantes> call = practicantesAPI.find(cedula);
        call.enqueue(new Callback<Practicantes>() {
            @Override
            public void onResponse(Call<Practicantes> call, Response<Practicantes> response) {
                try{

                    if(response.isSuccessful()){
                        Practicantes p = response.body();
                       /* txtCedula.setText(p.getPrac_cedula());
                        txtNombres.setText(p.getPrac_nombres());
                        txtCarrera.setText(p.getPrac_descripcion());
                        txtCorreo.setText(p.getPrac_correo());*/

                    }

                }catch (Exception ex) {
                    Log.d("Error",ex.getMessage());

                }
            }

            @Override
            public void onFailure(Call<Practicantes> call, Throwable t) {

            }
        });
    }

}