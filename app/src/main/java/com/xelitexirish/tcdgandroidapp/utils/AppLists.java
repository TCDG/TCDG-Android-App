package com.xelitexirish.tcdgandroidapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppLists {

    private static List<GithubObjects.GithubRepo> orgRepos = new ArrayList<>();

    private static String TAG_ORG_REPOS_OBJ = "ORG_REPOS_OBJECTS";

    public static void setupLists(Context context){

        if (getOrgReposFromPref(context) == null || getOrgReposFromPref(context).isEmpty()){
            new UpdateGithubLists(context).execute();
        }else {
            orgRepos = getOrgReposFromPref(context);
        }
    }

    public static List<GithubObjects.GithubRepo> getOrgRepos(){
        return orgRepos;
    }

    public static class UpdateGithubLists extends AsyncTask<Void, Void, Void> {

        private Context context;

        public UpdateGithubLists(Context context){this.context = context;}

        @Override
        protected Void doInBackground(Void... voids) {
            if(orgRepos.isEmpty()) addOrgRepoInfo();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            saveAllLists();
        }

        private void addOrgRepoInfo(){
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = jsonParser.makeHttpRequest(context, Constants.GITHUB_ORG_REPOS, "GET", new HashMap<String, String>());

            try {
                //JSONArray jsonArray = jsonObject.getJSONArray("array");
                if (jsonArray != null){
                    for (int x = 0; x < jsonArray.length(); x++){
                        JSONObject jsonItem = jsonArray.getJSONObject(x);

                        String id = jsonItem.getString("id");
                        String name = jsonItem.getString("name");
                        String url = jsonItem.getString("html_url");
                        String description = jsonItem.getString("description");
                        String releaseUrl = jsonItem.getString("releases_url");
                        String lastUpdated = jsonItem.getString("updated_at");
                        String lanaguage = jsonItem.getString("language");

                        GithubObjects.GithubRepo repo = new GithubObjects.GithubRepo(id, name, url, description, releaseUrl, lastUpdated, lanaguage);
                        orgRepos.add(repo);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void saveAllLists(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();

            String orgReposGson = gson.toJson(orgRepos);

            editor.putString(TAG_ORG_REPOS_OBJ, orgReposGson);
            editor.apply();
        }
    }


    /**
     * Helper
     */
    private static List<GithubObjects.GithubRepo> getOrgReposFromPref(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();

        String jsonString = preferences.getString(TAG_ORG_REPOS_OBJ, "");
        List<GithubObjects.GithubRepo> orgRepos = gson.fromJson(jsonString, new TypeToken<List<GithubObjects.GithubRepo>>(){}.getType());
        return orgRepos;
    }
}
