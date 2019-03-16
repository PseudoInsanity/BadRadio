package com.example.edmirsuljic.badradio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edmirsuljic.badradio.adapters.RadioAdapter;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioHandler;

public class HomeFragment extends Fragment {
    RadioHandler radioHandler = new RadioHandler();
    TextView radioStation;
    ImageButton button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.radio_list);
        radioStation = (TextView) inflate.findViewById(R.id.radioStation);
        //button = (ImageButton) inflate.findViewById(R.id.button);
        recyclerView.setHasFixedSize(true);

        //Context context = inflate.getContext().getApplicationContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(inflate.getContext().getApplicationContext());


        recyclerView.setLayoutManager(layoutManager);
        RadioAdapter radioAdapter = new RadioAdapter(inflate.getContext(), radioHandler.getRadioStation());
        recyclerView.setAdapter(radioAdapter);

        return inflate;
    }

}
