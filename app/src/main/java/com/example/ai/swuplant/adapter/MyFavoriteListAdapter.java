package com.example.ai.swuplant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.entity.PlantModel;
import java.util.List;

public class MyFavoriteListAdapter extends RecyclerView.Adapter<MyFavoriteListAdapter.ViewHolder>{


    private List<PlantModel> plantModels;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public MyFavoriteListAdapter(Context context, List<PlantModel> list){
        this.context = context;
        this.plantModels = list;
        inflater = LayoutInflater.from(this.context);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.angiosperm_image);
            textView = itemView.findViewById(R.id.angiosperm_name);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.my_favorite_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlantModel model = plantModels.get(position);
        holder.imageView.setImageResource(model.getPlantImageId());
        holder.textView.setText(model.getPlantCNName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(model.getPlantCNName());
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
