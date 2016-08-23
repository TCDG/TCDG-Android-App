package com.xelitexirish.tcdgandroidapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xelitexirish.tcdgandroidapp.handler.NavigationHandler;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.xelitexirish.tcdgandroidapp.utils.SecretConstants;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(SecretConstants.TWITTER_KEY, SecretConstants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);

        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_main);
        this.mNavigationView = (NavigationView) findViewById(R.id.navigationView_main);
        mNavigationView.setNavigationItemSelectedListener(new NavigationHandler());
    }


}
