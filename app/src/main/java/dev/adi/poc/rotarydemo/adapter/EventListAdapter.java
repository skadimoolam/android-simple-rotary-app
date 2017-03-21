package dev.adi.poc.rotarydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.EventModel;
import dev.adi.poc.rotarydemo.model.MemberModel;
import dev.adi.poc.rotarydemo.ui.EventActivity;
import dev.adi.poc.rotarydemo.ui.MemberActivity;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    Context context;
    List<EventModel> listEvents;

    public EventListAdapter(Context context, List<EventModel> listEvents) {
        this.context = context;
        this.listEvents = listEvents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EventModel model = listEvents.get(position);
        holder.setData(model);
        holder.hostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EventActivity.class);
                i.putExtra("event-detail", model);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEventDate, tvEventName;
        ImageView ivEventPhoto;
        CardView hostView;

        public ViewHolder(View view) {
            super(view);
            hostView = (CardView) view.findViewById(R.id.event_host_view);
            tvEventName = (TextView) view.findViewById(R.id.tv_event_name);
            tvEventDate = (TextView) view.findViewById(R.id.tv_event_date);
            ivEventPhoto = (ImageView) view.findViewById(R.id.iv_event_img);
        }

        public void setData(EventModel model) {
            if (model.eveName.length() > 25) {
                tvEventName.setText(model.eveName.substring(0, 25) + "...");
            } else {
                tvEventName.setText(model.eveName);
            }

            tvEventDate.setText(model.eveDate);

            if (model.evePhotoUrl.length() > 0) {
                Picasso.with(context).load(model.evePhotoUrl).placeholder(R.drawable.event_placeholder).fit().into(ivEventPhoto);
            } else {
                Picasso.with(context).load(R.drawable.event_placeholder).fit().into(ivEventPhoto);
            }
        }

    }
}
