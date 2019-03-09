package com.example.ai.swuplant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.entity.PlantModel;

import java.util.List;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.ViewHolder>{


    private List<PlantModel> plantModels;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public PlantListAdapter(Context context, List<PlantModel> list){
        this.context = context;
        this.plantModels = list;
        inflater = LayoutInflater.from(this.context);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.plant_image);
            textView = itemView.findViewById(R.id.plant_name);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.palnt_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlantModel model = plantModels.get(position);
        Glide.with(context).load(model.getPlantImageURL()).into(holder.imageView);
        holder.textView.setText(model.getPlantChineseName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(model.getPlantChineseName());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return plantModels.size();
    }


    public interface OnItemClickListener{
        public void onItemClick(String plantName);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}