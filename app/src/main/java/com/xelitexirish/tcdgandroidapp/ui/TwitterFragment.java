package com.xelitexirish.tcdgandroidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TwitterFragment extends ListFragment {

    private final String TWITTER_NAME = "dacollectivedev";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserTimeline timeline = new UserTimeline.Builder().screenName(TWITTER_NAME).build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(getActivity(), timeline);
        setListAdapter(adapter);
    }
}
