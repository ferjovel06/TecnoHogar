package com.example.tecnohogar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tecnohogar.R;
import com.example.tecnohogar.model.ElectricistaModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ElectricistasAdapter extends FirestoreRecyclerAdapter<ElectricistaModel, ElectricistasAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ElectricistasAdapter(@NonNull FirestoreRecyclerOptions<ElectricistaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull ElectricistaModel electricistaModel) {
        viewHolder.elName.setText(electricistaModel.getElName());
        viewHolder.elDes.setText(electricistaModel.getElDes());
        viewHolder.elHorario.setText(electricistaModel.getElHorario());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_electricistas, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView elName, elDes, elHorario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            elName = itemView.findViewById(R.id.el_name);
            elDes = itemView.findViewById(R.id.el_des);
            elHorario = itemView.findViewById(R.id.el_horario);
        }
    }
}