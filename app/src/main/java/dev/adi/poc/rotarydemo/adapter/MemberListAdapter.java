package dev.adi.poc.rotarydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
import dev.adi.poc.rotarydemo.ui.MemberActivity;

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
        final MemberModel model = listMembers.get(position);
        holder.setData(model);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MemberActivity.class);
                i.putExtra("member-detail", model);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMemName, tvMemDesignation;
        ImageView ivMemPhoto;
        CardView parent;

        public ViewHolder(View itemView) {
            super(itemView);
            parent = (CardView) itemView.findViewById(R.id.member_host_view);
            tvMemName = (TextView) itemView.findViewById(R.id.tv_mem_name);
            tvMemDesignation = (TextView) itemView.findViewById(R.id.tv_mem_designation);
            ivMemPhoto = (ImageView) itemView.findViewById(R.id.iv_member_photo);
        }

        public void setData(MemberModel model   ) {
            tvMemName.setText(model.memName);
            tvMemDesignation.setText(model.memDesignation);

            if (model.memPhoto.length() > 0) {
                Picasso.with(context).load(model.memPhoto).placeholder(R.drawable.event_placeholder).fit().into(ivMemPhoto);
            }
        }
    }
}
