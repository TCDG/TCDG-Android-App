package com.xelitexirish.tcdgandroidapp.handler;

import android.content.Context;
import android.os.AsyncTask;

import com.xelitexirish.tcdgandroidapp.utils.Constants;
import com.xelitexirish.tcdgandroidapp.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GitHubHandler {

    public static ArrayList<GithubRepo> orgRepos = new ArrayList<>();

    public static void init(Context context){
        new UpdateOrgRepos(context).execute();
    }

    public static class UpdateOrgRepos extends AsyncTask<Void, Void, Void> {

        private Context context;

        public UpdateOrgRepos(Context context){this.context = context;}

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

                        GithubRepo repo = new GithubRepo(id, name, url, description, releaseUrl, lastUpdated, lanaguage);
                        orgRepos.add(repo);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private static class GithubRepo{

        private String id;
        private String name;
        private String url;
        private String description;
        private String releaseUrl;
        private String lastUpdated;
        private String lanaguage;

        public GithubRepo(String id, String name, String url, String description, String releaseUrl, String lastUpdated, String language) {

            this.id = id;
            this.name = name;
            this.url = url;
            this.description = description;
            this.releaseUrl = releaseUrl;
            this.lastUpdated = lastUpdated;
            this.lanaguage = language;
        }

        public String getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String getUrl(){
            return url;
        }

        public String getDescription(){
            return description;
        }

        public String getReleaseUrl(){
            return releaseUrl;
        }

        public String getLastUpdated(){
            return lastUpdated;
        }

        public String getLanaguage(){
            return lanaguage;
        }
    }
}
