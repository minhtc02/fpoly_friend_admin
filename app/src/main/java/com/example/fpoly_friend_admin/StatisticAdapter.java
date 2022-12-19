package com.example.fpoly_friend_admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fpoly_friend_admin.databinding.ItemStatisticBinding;

import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder> {
    private final Context context;
    private final ItemClick itemClick;
    private List<UserProfile> list;

    public StatisticAdapter(Context context, ItemClick itemClick) {
        this.context = context;
        this.itemClick = itemClick;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<UserProfile> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStatisticBinding binding = ItemStatisticBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public interface ItemClick {
        void ban(UserProfile userProfile);

        void unBan(UserProfile userProfile);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemStatisticBinding binding;

        public ViewHolder(ItemStatisticBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(UserProfile userProfile) {
            binding.tvName.setText(userProfile.getName());
            binding.tvId.setText("ID: " + userProfile.getUserId());
            binding.tvGender.setText(userProfile.getGender() + ", " + userProfile.getAge());
            binding.tvEducation.setText(userProfile.getEducation());
            int progress = ((userProfile.getMatch()*100) / App.userProfileList.size()) ;
            Log.e("AAA","match: "+userProfile.getMatch());
            Log.e("AAA","App.userProfileList.size(): "+App.userProfileList.size());
            Log.e("AAA","progress: "+progress);
            binding.arcProgress1.setProgress((int)progress);
        }

    }
}

