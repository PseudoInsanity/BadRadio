package com.example.edmirsuljic.badradio.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioStation;
import com.example.edmirsuljic.badradio.services.MusicService;

import java.util.ArrayList;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    private ArrayList<RadioStation> mList;
    private OnItemClickListener mListener;
    private ViewHolder viewHolder;
    private int lastPos;
    private boolean isClicked = false;
    MusicService musicService = new MusicService();


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
        musicService.url = mList.get(position).getUrl();
        //holder.url = (mList.get(position).getUrl());


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
        private String url;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            radioTitle = itemView.findViewById(R.id.radioStation);
            playButton = itemView.findViewById(R.id.button);


        }
    }


    public interface OnItemClickListener {
        void onPlayClicked(int position);
    }

    private void handlePlayButton(View view, int position) {
        Context context = view.getContext();
        if (lastPos == position) {

            int prevPos = lastPos;
            lastPos = -1;
            notifyItemChanged(prevPos);
            notifyItemChanged(lastPos);
            Intent i = new Intent(view.getContext(), MusicService.class);
            context.stopService(i);
        } else {

            int prevPos = lastPos;
            lastPos = position;
            notifyItemChanged(prevPos);
            notifyItemChanged(lastPos);
            Intent i = new Intent(view.getContext(), MusicService.class);
            context.startService(i);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
