package com.example.edmirsuljic.badradio.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edmirsuljic.badradio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    private FirebaseAuth mAuth;

    Button signinButton, registerButton;
    //test
    EditText username, password;
    //RegisterFragment update = new RegisterFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        username = inflate.findViewById(R.id.username);
        password = inflate.findViewById(R.id.password);

        signinButton = inflate.findViewById(R.id.signinButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check with database if username && password match if so
                //the user will be logged in
            loginUser();
            }
        });

        registerButton = inflate.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on-click will take user to the register window
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = RegisterFragment.class;

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return inflate;
    }

    public void loginUser () {
        mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener((Executor) LoginFragment.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }



}
