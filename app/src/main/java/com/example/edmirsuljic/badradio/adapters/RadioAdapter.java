package com.example.edmirsuljic.badradio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edmirsuljic.badradio.Activities.MainActivity;
import com.example.edmirsuljic.badradio.Service.MusicService;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Radio_Related.RadioStation;

import java.util.ArrayList;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    private ArrayList<RadioStation> mList;
    private OnItemClickListener mListener;
    private int lastPos = -1;


    public RadioAdapter(ArrayList<RadioStation> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RadioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(listView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.radioTitle.setText(mList.get(position).getName());
        System.out.println("URL33: " + mList.get(position).getUrl());


        if (position == lastPos) {
            holder.playButton.setImageResource(R.drawable.ic_pause24dp);
        } else {
            holder.playButton.setImageResource(R.drawable.ic_play24dp);
        }

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlayButton(v, position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView radioTitle;
        private ImageView playButton;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            radioTitle = itemView.findViewById(R.id.radioStation);
            playButton = itemView.findViewById(R.id.rowPlayButton);
        }
    }


    public interface OnItemClickListener {
        void onPlayClicked(int position);
    }

    private void handlePlayButton(View view, int position) {
        Context context = view.getContext();
        if (lastPos == position) {

            Intent i = new Intent(context, MusicService.class);
            context.stopService(i);
            int prevPos = lastPos;
            lastPos = -1;
            notifyItemChanged(prevPos);
            notifyItemChanged(lastPos);

        } else {

            Intent i = new Intent(context, MusicService.class);
            context.stopService(i);
            i.putExtra("stationURL", mList.get(position).getUrl());
            i.putExtra("stationName", mList.get(position).getName());
            context.startService(i);
            int prevPos = lastPos;
            lastPos = position;
            notifyItemChanged(prevPos);
            notifyItemChanged(lastPos);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
