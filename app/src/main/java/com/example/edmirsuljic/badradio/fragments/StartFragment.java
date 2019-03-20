package com.example.edmirsuljic.badradio.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class StartFragment extends Fragment {

    private VideoView videoView;
    private Button signBtn, regBtn, forgotBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_start, container, false);
       // videoView = view.findViewById(R.id.signInVideoView);
        signBtn = view.findViewById(R.id.signinBtn);
        regBtn = view.findViewById(R.id.regBtn);
        forgotBtn = view.findViewById(R.id.forgotPasswordStart);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new RegisterFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.start_fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LoginFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.start_fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText editText = new EditText(view.getContext());
                editText.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);
                editText.setTextColor(getResources().getColor(R.color.colorPrimary));
                editText.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                AlertDialog dialog = new AlertDialog.Builder(view.getContext(), R.style.AlertDialog)
                        .setTitle("Enter Email")
                        .setView(editText)
                        .setPositiveButton("Restore Password", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                String emailAddress = editText.getText().toString();

                                auth.sendPasswordResetEmail(emailAddress)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getContext(), "Password restore sent!",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
