package com.xelitexirish.tcdgandroidapp.handler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.xelitexirish.tcdgandroidapp.R;
import com.xelitexirish.tcdgandroidapp.ui.HomeFragment;
import com.xelitexirish.tcdgandroidapp.ui.MembersFragment;
import com.xelitexirish.tcdgandroidapp.ui.RepoFragment;
import com.xelitexirish.tcdgandroidapp.ui.TwitterFragment;

import static android.R.attr.id;

public class NavigationHandler {

    private static final String TAG = NavigationHandler.class.getSimpleName();

    public static final int idHome = 1;
    public static final int idReposFragment = 2;
    public static final int idMembersFragment = 3;
    public static final int idTwitter = 19;
    public static final int idSettings = 20;

    public static void handleClick(Context context, IDrawerItem drawerItem) {
        long id = drawerItem.getIdentifier();

        if (id == idHome) {
            HomeFragment homeFragment = new HomeFragment();
            switchScreen(context, homeFragment);

        }else if (id == idReposFragment) {
            RepoFragment fragmentBusRealTime = new RepoFragment();
            switchScreen(context, fragmentBusRealTime);

        }else if (id == idMembersFragment) {
            MembersFragment membersFragment = new MembersFragment();
            switchScreen(context, membersFragment);

        }else if (id == idTwitter) {
            TwitterFragment twitterFragment = new TwitterFragment();
            switchScreen(context, twitterFragment);

        } else if (id == idSettings) {

        }
    }

    public static void switchScreen(Context context, Object screen) {
        if (screen instanceof Fragment) {
            try {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, (Fragment) screen).addToBackStack(null).commit();
            } catch (ClassCastException e) {
                Log.e(TAG, "Can't get fragment manager");
            }

        } else if (screen instanceof Intent) {
            context.startActivity((Intent) screen);
        }
    }
}
