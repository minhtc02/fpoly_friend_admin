package com.example.fpoly_friend_admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fpoly_friend_admin.databinding.ActivityReportBinding;

public class ReportActivity extends AppCompatActivity {
    ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(view -> onBackPressed());
    }
}