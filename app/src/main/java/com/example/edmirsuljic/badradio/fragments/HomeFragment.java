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
    RadioHandler radioHandler = new RadioHandler();
    TextView radioStationTextView;
    ImageButton button;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RadioStation radioStation = new RadioStation();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        System.out.println("Im in the home fragment");
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.radio_list);
        radioStationTextView = (TextView) inflate.findViewById(R.id.radioStation);
        button = (ImageButton) inflate.findViewById(R.id.button);
        recyclerView.setHasFixedSize(true);

        //Context context = inflate.getContext().getApplicationContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(inflate.getContext().getApplicationContext());


        recyclerView.setLayoutManager(layoutManager);
        //här kommer listan in i adaptern
        RadioAdapter radioAdapter = new RadioAdapter(inflate.getContext().getApplicationContext(), getRadioStationName());
        System.out.println("Radio in Adapter: " + radioHandler.getRadioStation());
        System.out.println("Radio in method: " + getRadioStationName());
        recyclerView.setAdapter(radioAdapter);

        return inflate;
    }

    //här hämtar jag listan från radiohandlern som i sin tur hämtar datan från fb. du ser i printoutsen att datan stämmer så får ut rätt men kan inte trycka in det i adaptern av någon anledning
    private List<RadioStation> getRadioStationName() {
        List<RadioStation> radioStations = new ArrayList<>();
        RadioStation radioStation = new RadioStation();

        radioStations = radioHandler.getRadioStation();

        radioStations.add(radioStation);
        return radioStations;
    }


}
