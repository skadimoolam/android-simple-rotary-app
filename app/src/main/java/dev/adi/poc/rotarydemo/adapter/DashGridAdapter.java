package dev.adi.poc.rotarydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.DashButtonModel;

public class DashGridAdapter extends RecyclerView.Adapter<DashGridAdapter.ViewHolder> {

    Context context;
    ArrayList<DashButtonModel> listButtons;

    public DashGridAdapter(Context context, ArrayList<DashButtonModel> listButtons) {
        this.context = context;
        this.listButtons = listButtons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dash_btn, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DashButtonModel btnModel = listButtons.get(position);

        holder.ivBtnImage.setImageResource(btnModel.btnImg);
        if (btnModel.activityName != null) {
            holder.viewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, (Class<?>) btnModel.activityName);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listButtons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBtnImage;
        LinearLayout viewHome;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBtnImage = (ImageView) itemView.findViewById(R.id.iv_item_img);
            viewHome = (LinearLayout) itemView.findViewById(R.id.ll_view_home);
        }
    }
}
