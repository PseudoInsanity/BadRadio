package com.example.edmirsuljic.badradio.adapters;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioStation;

import java.util.ArrayList;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    public static ArrayList<RadioStation> mList;
    private OnItemClickListener mListener;
    private ViewHolder viewHolder;
    private int lastPos;
    private boolean isClicked = false;

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


        if (position == lastPos) {
            holder.playButton.setImageResource(R.drawable.ic_pause24dp);
        } else {
            holder.playButton.setImageResource(R.drawable.ic_play24dp);
        }

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePlayButton(position);
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
            playButton = itemView.findViewById(R.id.button);



        }
    }


    public interface OnItemClickListener {
        void onPlayClicked(int position);
    }

    private void changePlayButton(int position) {
        if (lastPos == position) {

            int prevPos = lastPos;
            lastPos = -1;
            notifyItemChanged(prevPos);
            notifyItemChanged(lastPos);
        } else {

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
