package com.example.edmirsuljic.badradio.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioStation;

import java.util.List;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    private Context context;
    private List<RadioStation> mList;
    private MyOnClickListener mMyOnClickListener;
    private int currentPlayingPosition = -1;


    public RadioAdapter(Context context, List<RadioStation> lists) {
        this.mList = lists;
        this.context = context;
    }


    @NonNull
    @Override
    public RadioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.cell_layout, parent, false);

        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RadioStation radioStation;
        radioStation = mList.get(position);
        holder.radioTitle.setText(radioStation.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        mMyOnClickListener = myOnClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton btnPlay, btnPause;
        private TextView radioTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            radioTitle = itemView.findViewById(R.id.radioStation);


          /*  btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    int prev = currentPlayingPosition;
                    currentPlayingPosition = position;

                    if (prev >= 0)
                        notifyItemChanged(prev);  // refresh previously playing view

                    notifyItemChanged(currentPlayingPosition);

                    mMyOnClickListener.playOnClick(v, position);
                }
            });

            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    notifyItemChanged(position);
                    currentPlayingPosition = -1;
                    mMyOnClickListener.pauseOnClick(v, position);
                }
            });*/
        }
    }

    private interface MyOnClickListener {
        void playOnClick(View v, int position);

        void pauseOnClick(View v, int position);
    }
}
