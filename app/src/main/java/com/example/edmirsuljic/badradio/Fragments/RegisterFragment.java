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

import com.example.edmirsuljic.badradio.Activities.MainActivity;
import com.example.edmirsuljic.badradio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {

    public RegisterFragment () {

    }

    MainActivity main = new MainActivity();
    Button register, back;
    EditText email, username, password;
    private FirebaseAuth mAuth;
    public boolean loggedIn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();
        email = inflate.findViewById(R.id.email);
        username = inflate.findViewById(R.id.username);
        password = inflate.findViewById(R.id.password);

        register = inflate.findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the email, username and password is valid if it is it saves it to database and take user back
                //to login screen else error output and user have to re-enter information
                if (isEmailValid(email.getText().toString()) == true
                        && isPasswordValid(password.toString()) == true) {
                    //first check passed and information can get added to database and send user to login window UNLESS email or username
                    // already exists in that case print out error message
                    System.out.println("worlddd111");
                    createAccount();

                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = com.example.edmirsuljic.badradio.Fragments.LoginFragment.class;

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

                } else if (isEmailValid(email.getText().toString()) == false
                || isPasswordValid(password.getText().toString()) == false){
                    Toast.makeText(getContext(),"Invalid email or password",Toast.LENGTH_LONG).show();
                }
            }
        });

        back = inflate.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = com.example.edmirsuljic.badradio.Fragments.LoginFragment.class;



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

    public void createAccount (){
        System.out.println("worlddd");
        email.setText("testtttttttttttt");
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener((Executor) RegisterFragment.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    // checks if what the user put in matches @ in order for it to be valid as a mail
    private boolean isEmailValid(String mail) {
        return mail.contains("@");
    }

    // checks if what the user put in matches the allowed symbols and correct length
    private boolean isUserValid(String user) {
        return user.matches("[0-9a-zA-Z]{3,16}");
    }

    // checks if what the user put in matches the allowed symbols and correct length
    private boolean isPasswordValid(String password) {
        return password.matches("[0-9a-zA-Z]{6,16}");
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            loggedIn = true;
            System.out.println("test123");
        } else {
            loggedIn = false;
            System.out.println("test321");
        }
    }

}
