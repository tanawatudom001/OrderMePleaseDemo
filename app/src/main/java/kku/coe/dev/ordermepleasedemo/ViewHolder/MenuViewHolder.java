package kku.coe.dev.ordermepleasedemo.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kku.coe.dev.ordermepleasedemo.Interface.ItemClickListener;
import kku.coe.dev.ordermepleasedemo.R;
import kku.coe.dev.ordermepleasedemo.Interface.ItemClickListener;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);

        txtMenuName =(TextView)itemView.findViewById(R.id.);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
