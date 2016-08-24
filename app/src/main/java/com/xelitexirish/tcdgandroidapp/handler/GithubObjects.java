package com.xelitexirish.tcdgandroidapp.handler;

public class GithubObjects {


    public static class GithubRepo {

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

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getDescription() {
            return description;
        }

        public String getReleaseUrl() {
            return releaseUrl;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public String getLanaguage() {
            return lanaguage;
        }
    }
}
