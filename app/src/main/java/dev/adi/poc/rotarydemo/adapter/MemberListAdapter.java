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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.DashButtonModel;
import dev.adi.poc.rotarydemo.model.EventModel;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {

    Context context;
    List<MemberModel> listMembers;

    public MemberListAdapter(Context context, List<MemberModel> listMembers) {
        this.context = context;
        this.listMembers = listMembers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MemberModel model = listMembers.get(position);
        holder.setData(model);
    }

    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMemName, tvMemPhone, tvMemEmail, tvMemDob, tvMemDesignation;
        ImageView ivMemPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMemName = (TextView) itemView.findViewById(R.id.tv_mem_name);
            tvMemPhone = (TextView) itemView.findViewById(R.id.tv_mem_phone);
            tvMemEmail = (TextView) itemView.findViewById(R.id.tv_mem_email);
            tvMemDob = (TextView) itemView.findViewById(R.id.tv_mem_dob);
            tvMemDesignation = (TextView) itemView.findViewById(R.id.tv_mem_designation);
            ivMemPhoto = (ImageView) itemView.findViewById(R.id.iv_member_photo);
        }

        public void setData(MemberModel model   ) {
            tvMemName.setText(model.memName);
            tvMemPhone.setText(model.memPhone);
            tvMemEmail.setText(model.memEmail);
            tvMemDob.setText("DOB : " + model.memDob);
            tvMemDesignation.setText(model.memDesignation);

            if (model.memPhoto.length() > 0) {
                Picasso.with(context).load(model.memPhoto).placeholder(R.drawable.event_placeholder).fit().into(ivMemPhoto);
            }
        }
    }
}
