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

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.DashButtonModel;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {

    Context context;
    ArrayList<MemberModel> listMembers;

    public MemberListAdapter(Context context, ArrayList<MemberModel> listMembers) {
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

        TextView tvMemName, tvMemContact, tvMemEmail, tvMemDob, tvMemMarriage, tvMemBloodGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMemName = (TextView) itemView.findViewById(R.id.tv_mem_name);
            tvMemContact = (TextView) itemView.findViewById(R.id.tv_mem_contact);
            tvMemEmail = (TextView) itemView.findViewById(R.id.tv_mem_email);
            tvMemDob = (TextView) itemView.findViewById(R.id.tv_mem_dob);
            tvMemMarriage = (TextView) itemView.findViewById(R.id.tv_mem_marriage);
            tvMemBloodGroup = (TextView) itemView.findViewById(R.id.tv_mem_blood_group);
        }

        public void setData(MemberModel model   ) {
            tvMemName.setText(model.memName);
            tvMemContact.setText(model.memContact);
            tvMemEmail.setText(model.memEmail);
            tvMemDob.setText("DOB : " + model.memDob);
            tvMemMarriage.setText("Marriage Date : " + model.memMarriage);
            tvMemBloodGroup.setText("Blood Group : " + model.memBloodGroup);
        }
    }
}
