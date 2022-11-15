package com.example.tecnohogar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tecnohogar.R;
import com.example.tecnohogar.models.ElectricistaModel;

import java.util.List;

public class ElectricistaAdapters extends RecyclerView.Adapter<ElectricistaAdapters.ViewHolder> {

    private Context context;
    private List<ElectricistaModel> electricistaModelList;

    public ElectricistaAdapters(Context context, List<ElectricistaModel> electricistaModelList) {
        this.context = context;
        this.electricistaModelList = electricistaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_electricistas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(electricistaModelList.get(position).getImg_url()).into(holder.elImg);
        holder.elName.setText(electricistaModelList.get(position).getNombre());
        holder.elDes.setText(electricistaModelList.get(position).getDescripcion());
        holder.elHorario.setText(electricistaModelList.get(position).getHorario());
        holder.elRating.setText(electricistaModelList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return electricistaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView elImg;
        TextView elName, elDes, elHorario, elRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            elImg = itemView.findViewById(R.id.el_img);
            elName = itemView.findViewById(R.id.el_name);
            elDes = itemView.findViewById(R.id.el_des);
            elHorario = itemView.findViewById(R.id.el_horario);
            elRating = itemView.findViewById(R.id.el_rating);
        }
    }
}
