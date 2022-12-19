package com.example.fpoly_friend_admin;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public static final String CHANNEL_ID = "push_notification_id";
    public static Context context;
    public static SharePref sharePref;
    public static String TAG = "AAA";
    public static FirebaseUser user;
    public static List<UserProfile> userProfileList;
    public static UserProfile currentUser;
    public static FirebaseDatabase database;
    public static DatabaseReference userRef;
    public static DatabaseReference matchRef;

    @Override
    public void onCreate() {
        super.onCreate();
        userProfileList = new ArrayList<>();
        currentUser = new UserProfile();
        context = getApplicationContext();
        sharePref = new SharePref(context);
        user = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user_profile/");
        matchRef = database.getReference("user_profile_match/");

        createChanelNotification();
    }

    private void createChanelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "PushNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
