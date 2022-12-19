package com.example.fpoly_friend_admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fpoly_friend_admin.databinding.ActivityUserBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity implements UserAdapter.ItemClick {
    private static final String TAG = "AAA";
    ActivityUserBinding binding;
    UserAdapter userAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        userAdapter = new UserAdapter(this, this);
        binding.recUser.setAdapter(userAdapter);
        userAdapter.setData(App.userProfileList);
//        updateUI();

        getData();

        binding.btnBack.setOnClickListener(view -> onBackPressed());
    }

    private void getData() {
        App.userProfileList.clear();
        progressDialog.show();
        App.userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                        if (userProfile != null) {
                            App.userProfileList.add(userProfile);
                            Log.e(TAG, "userProfile" + userProfile.getAvailability());
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "" + e);
                    }
                }
                progressDialog.dismiss();
                userAdapter.setData(App.userProfileList);
                Log.e(TAG, "update");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(TAG, "profile list empty");
            }
        });

    }

    void updateUI() {
        getData();

    }


    @Override
    public void ban(UserProfile userProfile) {
        DatabaseReference myRef = App.database.getReference("user_profile/" + userProfile.getUserId() + "/availability");
        myRef.setValue(-101, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void unBan(UserProfile userProfile) {
        DatabaseReference myRef = App.database.getReference("user_profile/" + userProfile.getUserId() + "/availability");
        myRef.setValue(1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                progressDialog.dismiss();
            }
        });
    }
}