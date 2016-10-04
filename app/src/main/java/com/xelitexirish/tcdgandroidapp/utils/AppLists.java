package com.xelitexirish.tcdgandroidapp.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AppLists {


    public static class UpdateGithubMembers extends AsyncTask<Void, Void, Void> {

        private Context context;

        public UpdateGithubMembers(Context context){this.context = context;}

        @Override
        protected Void doInBackground(Void... voids) {
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

            return null;
        }
    }

    public static class UpdateGithubRepos extends AsyncTask<Void, Void, Void> {

        private Context context;

        public UpdateGithubRepos(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = jsonParser.makeHttpRequest(context, Constants.GITHUB_ORG_MEMBERS, "GET", new HashMap<String, String>());

            try {
                //JSONArray jsonArray = jsonObject.getJSONArray("array");
                if (jsonArray != null) {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonItem = jsonArray.getJSONObject(x);

                        String username = jsonItem.getString("login");
                        String url = jsonItem.getString("url");
                        String profilePicture = jsonItem.getString("avatar_url");

                        GithubObjects.GithubMember member = new GithubObjects.GithubMember(username, url, profilePicture);
                        orgMembers.add(member);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
