package com.example.edmirsuljic.badradio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.edmirsuljic.badradio.adapters.RadioAdapter;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioHandler;
import com.example.edmirsuljic.badradio.radio_related.RadioStation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RadioHandler radioHandler;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        radioHandler = new RadioHandler();

        for (RadioStation r: radioHandler.getRadioStationList()) {
            System.out.println("namn: " + r.getName());
        }

        System.out.println("Im in the home fragment");
        recyclerView = inflate.findViewById(R.id.radio_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(inflate.getContext());
        adapter = new RadioAdapter(radioHandler.getRadioStationList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return inflate;
    }


}
