package com.example.fpoly_friend_admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fpoly_friend_admin.databinding.ItemUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context context;
    private final ItemClick itemClick;
    private List<UserProfile> list;

    public UserAdapter(Context context, ItemClick itemClick) {
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
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(UserProfile userProfile) {
            binding.tvName.setText(userProfile.getName());
            binding.tvId.setText("ID: " + userProfile.getUserId());
            binding.tvGender.setText(userProfile.getGender() + ", " + userProfile.getAge());
            binding.tvEducation.setText(userProfile.getEducation());

            if (userProfile.getToken() != null) {
                binding.tvTime.setText(userProfile.getToken());
            }

            if (userProfile.getAvailability() == -101) {
                binding.btnUnBan.setVisibility(View.VISIBLE);
                binding.btnBan.setVisibility(View.GONE);
            } else {
                binding.btnUnBan.setVisibility(View.GONE);
                binding.btnBan.setVisibility(View.VISIBLE);
            }

            binding.btnBan.setOnClickListener(view -> {
                Log.e("AAA","ban");
                binding.btnUnBan.setVisibility(View.VISIBLE);
                binding.btnBan.setVisibility(View.GONE);
                itemClick.ban(userProfile);

            });

            binding.btnUnBan.setOnClickListener(view -> {
                binding.btnUnBan.setVisibility(View.GONE);
                binding.btnBan.setVisibility(View.VISIBLE);
                itemClick.unBan(userProfile);
                Log.e("AAA","unBan");

            });
        }

    }
}

