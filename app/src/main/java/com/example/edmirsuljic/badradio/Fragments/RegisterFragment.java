package com.example.edmirsuljic.badradio.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edmirsuljic.badradio.R;

public class RegisterFragment extends Fragment {

    public RegisterFragment () {

    }

    Button register, back;
    EditText email, username, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_register, container, false);

        email = inflate.findViewById(R.id.email);
        username = inflate.findViewById(R.id.username);
        password = inflate.findViewById(R.id.password);

        register = inflate.findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the email, username and password is valid if it is it saves it to database and take user back
                //to login screen else error output and user have to re-enter information
                if (isEmailValid(email.getText().toString()) == true && isUserValid(username.toString()) == true
                        && isPasswordValid(password.toString()) == true) {
                    //first check passed and information can get added to database and send user to login window UNLESS email or username
                    // already exists in that case print out error message

                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = LoginFragment.class;

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

                } else if (isEmailValid(email.getText().toString()) == false || isUserValid(username.getText().toString()) == false
                || isPasswordValid(password.getText().toString()) == false){
                    Toast.makeText(getContext(),"Invalid email, username or password",Toast.LENGTH_LONG).show();
                }
            }
        });

        back = inflate.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = LoginFragment.class;

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

}
