package com.example.edmirsuljic.badradio.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {


    private FloatingActionButton regFab, backFab;
    private EditText emailTxt, passwordTxt;
    private VideoView videoView;
    private FirebaseAuth authUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        regFab = view.findViewById(R.id.regFab);
        backFab = view.findViewById(R.id.exitRegFab);
        emailTxt = view.findViewById(R.id.regMailTxt);
        passwordTxt = view.findViewById(R.id.regPasswTxt);
        videoView = view.findViewById(R.id.regVidView);

        authUser = FirebaseAuth.getInstance();

        playBackground();

        regFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eMail = emailTxt.getText().toString();
                String passw = passwordTxt.getText().toString();

                if (isEmailValid(eMail) && isPasswordValid(passw)) {
                    createAccount(eMail, passw);
                }
            }
        });

        backFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new StartFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.start_fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private void createAccount (String mail, String password){

        authUser.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = authUser.getCurrentUser();
                            user.sendEmailVerification();

                            Toast.makeText(getContext(), "Verification mail sent!",
                                    Toast.LENGTH_LONG).show();

                            Fragment fragment = new StartFragment();
                            FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                            transaction.replace(R.id.start_fragment_holder, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean isEmailValid(String mail) {
        return mail.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.matches("[0-9a-zA-Z]{6,16}");
    }

    private void playBackground () {

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.start();
            }
        });

        Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.badradio_vid);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public void onStart () {
        super.onStart();

        playBackground();
    }



}
