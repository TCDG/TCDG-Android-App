package com.xelitexirish.tcdgandroidapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xelitexirish.tcdgandroidapp.R;
import com.xelitexirish.tcdgandroidapp.RepoRecyclerAdapter;
import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;
import com.xelitexirish.tcdgandroidapp.utils.AppLists;

import java.util.ArrayList;
import java.util.List;

public class RepoFragment extends Fragment {

    private static SwipeRefreshLayout mRefreshLayout;

    private ContextMenuRecyclerView mRecyclerView;
    private static RepoRecyclerAdapter adapter;
    private static List<GithubObjects.GithubRepo> orgRepos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                updateRecyclerView(getContext());
            }
        });

        setupRecyclerView(view);
    }

    private void setupRecyclerView(View view) {
        orgRepos = AppLists.getOrgRepos();

        adapter = new RepoRecyclerAdapter(getContext(), orgRepos);
        mRecyclerView = (ContextMenuRecyclerView) view.findViewById(R.id.recyclerViewRepos);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Seperator
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        registerForContextMenu(mRecyclerView);
    }

    public void updateRecyclerView(Context context) {
        orgRepos.clear();

        AppLists.updateLists(context);

        orgRepos.addAll(AppLists.getOrgRepos());
        adapter.notifyDataSetChanged();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}
