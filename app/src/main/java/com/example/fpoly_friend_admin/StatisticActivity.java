package com.example.fpoly_friend_admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fpoly_friend_admin.databinding.ActivityStatisticBinding;

public class StatisticActivity extends AppCompatActivity implements StatisticAdapter.ItemClick {
    ActivityStatisticBinding binding;
    StatisticAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        setClick();
    }

    private void initView() {
        userAdapter = new StatisticAdapter(this, this);
        binding.recUser.setAdapter(userAdapter);
        userAdapter.setData(App.userProfileList);
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void ban(UserProfile userProfile) {

    }

    @Override
    public void unBan(UserProfile userProfile) {

    }
}