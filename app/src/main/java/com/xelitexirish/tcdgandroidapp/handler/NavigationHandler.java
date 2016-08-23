package com.xelitexirish.tcdgandroidapp.handler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.xelitexirish.tcdgandroidapp.R;
import com.xelitexirish.tcdgandroidapp.ui.TwitterFragment;

public class NavigationHandler implements NavigationView.OnNavigationItemSelectedListener{

    private Context context;

    public NavigationHandler(Context context){
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Object obj = getScreenFromId(id);

        if(obj instanceof Fragment){
            replaceWithFragment(context, (Fragment) obj);
        }else {
            handleIntent(context, (Class) obj);
        }
        return true;
    }

    private static Object getScreenFromId(int id){
        if (id == R.id.nav_home){

        }else if (id == R.id.nav_twitter){
            TwitterFragment twitterFragment = new TwitterFragment();
            return twitterFragment;
        }
        return null;
    }

    public void replaceWithFragment(Context context, Fragment fragment){
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void handleIntent(Context context, Class intentClass){
        try {
            Intent intent = new Intent(context, intentClass);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
