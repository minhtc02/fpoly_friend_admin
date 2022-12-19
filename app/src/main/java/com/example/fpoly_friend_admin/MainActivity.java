package com.example.fpoly_friend_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fpoly_friend_admin.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AAA";
    ActivityMainBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnUser.setOnClickListener(view -> startActivity(new Intent(this, UserActivity.class)));
        binding.btnStatistic.setOnClickListener(view -> startActivity(new Intent(this, StatisticActivity.class)));
        binding.btnReport.setOnClickListener(view -> startActivity(new Intent(this, ReportActivity.class)));

        initView();
        setClick();
        getData();
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
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "" + e);
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e(TAG, "profile list empty");
            }
        });
    }

    private void setClick() {
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}