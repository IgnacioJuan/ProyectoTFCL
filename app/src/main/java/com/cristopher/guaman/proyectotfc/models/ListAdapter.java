package com.cristopher.guaman.proyectotfc.models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cristopher.guaman.proyectotfc.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Practicantes> mData;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(List<Practicantes> itemList,Context context){
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Practicantes> items){
        mData=items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name, cedula, carrera, correo;
        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.cedulaTextView);
            cedula = itemView.findViewById(R.id.nombresTextView);
            carrera = itemView.findViewById(R.id.carreraTextView);
            correo = itemView.findViewById(R.id.correoTextView);

        }
        void bindData(final Practicantes item){
            name.setText(item.getNombre_completo());
            cedula.setText(item.getCedula());
            carrera.setText(item.getCar_descripcion());
            correo.setText(item.getPer_correo());

        }
    }
}
