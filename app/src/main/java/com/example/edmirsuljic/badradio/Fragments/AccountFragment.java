package com.example.edmirsuljic.badradio.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edmirsuljic.badradio.Activities.StartActivity;
import com.example.edmirsuljic.badradio.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AccountFragment extends Fragment  {

    private Button pwBtn, deleteBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        pwBtn = view.findViewById(R.id.passwordChangeBtn);
        deleteBtn = view.findViewById(R.id.deleteAccountBtn);

        pwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changePassword(v);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(v);
            }
        });

        return view;
    }

    private void changePassword (View v) {

        final EditText pwEdit = new EditText(v.getContext());
        pwEdit.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP);
        pwEdit.setTextColor(getResources().getColor(R.color.colorPrimary));
        pwEdit.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        AlertDialog pwDialog = new AlertDialog.Builder(v.getContext(), R.style.AlertDialog)
                .setTitle("Enter New Password")
                .setView(pwEdit)
                .setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String newPassword = pwEdit.getText().toString();

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        user.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getContext(), "Password updated",
                                                    Toast.LENGTH_LONG).show();

                                        } else {

                                            Toast.makeText(getContext(), "Cannot change Password",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        pwDialog.show();
    }

    private void deleteAccount (final View v) {

        final EditText accPass = new EditText(v.getContext());
        accPass.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP);
        accPass.setTextColor(getResources().getColor(R.color.colorPrimary));
        accPass.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        AlertDialog pwDialog = new AlertDialog.Builder(v.getContext(), R.style.AlertDialog)
                .setTitle("Enter Password")
                .setView(accPass)
                .setPositiveButton("Delete Account", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String password = accPass.getText().toString();

                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), password);

                        user.reauthenticate(authCredential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            user.delete()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(getContext(), "Account deleted",
                                                                        Toast.LENGTH_LONG).show();

                                                                Intent i = new Intent(v.getContext(), StartActivity.class);
                                                                startActivity(i);

                                                            } else {

                                                                Toast.makeText(getContext(), "Cannot change Password",
                                                                        Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        pwDialog.show();

    }


}
