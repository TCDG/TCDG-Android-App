package com.xelitexirish.tcdgandroidapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.xelitexirish.tcdgandroidapp.handler.NavigationHandler;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.xelitexirish.tcdgandroidapp.ui.HomeFragment;
import com.xelitexirish.tcdgandroidapp.utils.AppLists;
import com.xelitexirish.tcdgandroidapp.utils.SecretConstants;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private AccountHeader mNavDrawerHeader = null;
    private Drawer mNavDrawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(SecretConstants.TWITTER_KEY, SecretConstants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);

        // Create the account header
        buildNavHeader(false, savedInstanceState);

        // Create the nav drawer
        buildNavDrawer(savedInstanceState);

        AppLists.updateLists(this);

        // Set default fragment
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
        }
    }

    private void buildNavHeader(boolean compact, Bundle savedInstanceState) {
        mNavDrawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.nav_header_background)
                .withCompactStyle(compact)
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private void buildNavDrawer(Bundle savedInstanceState) {
        mNavDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(mNavDrawerHeader)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(NavigationHandler.idHome),
                        new PrimaryDrawerItem().withName("TCDG Members").withIcon(GoogleMaterial.Icon.gmd_folder_person).withIdentifier(NavigationHandler.idMembersFragment),
                        new PrimaryDrawerItem().withName("TCDG Repos").withIcon(GoogleMaterial.Icon.gmd_file).withIdentifier(NavigationHandler.idReposFragment)
                )
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName("Twitter").withIcon(GoogleMaterial.Icon.gmd_twitter).withIdentifier(NavigationHandler.idTwitter),
                        new SecondaryDrawerItem().withName("Settings").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(NavigationHandler.idSettings)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        NavigationHandler.handleClick(MainActivity.this, drawerItem);
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = mNavDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {

        if (mNavDrawer != null && mNavDrawer.isDrawerOpen()) {
            mNavDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
