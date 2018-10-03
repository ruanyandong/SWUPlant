package com.example.ai.swuplant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.entity.BatchManageModel;
import com.example.ai.swuplant.entity.PlantModel;
import java.util.List;

public class MyFavoriteListAdapter extends RecyclerView.Adapter<MyFavoriteListAdapter.ViewHolder>{


    private List<BatchManageModel> plantModels;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    // 用于判断是否显示底部和checkbox
    public boolean flage = false;

    public MyFavoriteListAdapter(Context context, List<BatchManageModel> list){
        this.context = context;
        this.plantModels = list;
        inflater = LayoutInflater.from(this.context);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_favorite_image);
            textView = itemView.findViewById(R.id.my_favorite_name);
            checkBox = itemView.findViewById(R.id.checkbox);
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
        BatchManageModel model = plantModels.get(position);

        holder.imageView.setImageResource(model.getPlantModel().getPlantImageId());
        holder.textView.setText(model.getPlantModel().getPlantCNName());
        // 根据isSelected来设置checkbox的显示状况
        if (flage) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        holder.checkBox.setChecked(model.getCheck());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getCheck()) {
                    model.setCheck(false);
                } else {
                    model.setCheck(true);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(model.getPlantModel().getPlantCNName());
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
