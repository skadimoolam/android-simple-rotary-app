package dev.adi.poc.rotarydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.DashButtonModel;

public class DashGridAdapter extends RecyclerView.Adapter<DashGridAdapter.ViewHolder> {

    Context context;
    ArrayList<DashButtonModel> listButtons;
    Point dispDimension;
    float heightInPixel, paddingInPixel;

    public DashGridAdapter(Context context, ArrayList<DashButtonModel> listButtons, Point dispDimension) {
        this.context = context;
        this.listButtons = listButtons;
        this.dispDimension = dispDimension;
        heightInPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, context.getResources().getDisplayMetrics());
        paddingInPixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dash_btn, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DashButtonModel btnModel = listButtons.get(position);
        holder.tvItemTitle.setText(btnModel.btnTitle);
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
        TextView tvItemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBtnImage = (ImageView) itemView.findViewById(R.id.iv_item_img);
            tvItemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            viewHome = (LinearLayout) itemView.findViewById(R.id.ll_view_home);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dispDimension.x/3, ((int) heightInPixel));
            lp.setMargins((int)paddingInPixel, (int)paddingInPixel, (int)paddingInPixel, (int)paddingInPixel);
            viewHome.setLayoutParams(lp);
        }
    }
}
