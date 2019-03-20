package com.example.edmirsuljic.badradio.fragments;

import android.content.Intent;
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

import com.example.edmirsuljic.badradio.activities.MainActivity;
import com.example.edmirsuljic.badradio.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    private FloatingActionButton signFab, backFab;
    private EditText emailTxt, passwordTxt;
    private VideoView vidView;
    private FirebaseAuth authUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_login, container, false);
        signFab = view.findViewById(R.id.signFab);
        backFab = view.findViewById(R.id.exitSignFab);
        emailTxt = view.findViewById(R.id.signMailTxt);
        passwordTxt = view.findViewById(R.id.signPasswTxt);
        vidView = view.findViewById(R.id.loginVid);

        authUser = FirebaseAuth.getInstance();

        playBackground();

        signFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail = emailTxt.getText().toString();
                String passw = passwordTxt.getText().toString();

                if (isEmailValid(eMail) && isPasswordValid(passw)) {


                    loginUser(eMail, passw, v);
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

    private void loginUser (String mail, String password, final View v) {
        authUser.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = authUser.getCurrentUser();

                            if (user.isEmailVerified()) {
                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                startActivity(intent);

                            } else {

                                Toast.makeText(getContext(), "Please verify your email!",
                                        Toast.LENGTH_LONG).show();
                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
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

        vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                vidView.start();
            }
        });

        Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.badradio_vid);
        vidView.setVideoURI(uri);
        vidView.start();
    }

    @Override
    public void onStart () {
        super.onStart();

        playBackground();
    }


}
