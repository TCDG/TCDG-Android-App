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
import com.xelitexirish.tcdgandroidapp.adapter.MembersRecyclerAdapter;
import com.xelitexirish.tcdgandroidapp.adapter.RepoRecyclerAdapter;
import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;
import com.xelitexirish.tcdgandroidapp.utils.AppLists;

import java.util.List;

public class MembersFragment extends Fragment{

    private static SwipeRefreshLayout mRefreshLayout;

    private ContextMenuRecyclerView mRecyclerView;
    private static MembersRecyclerAdapter adapter;
    private static List<GithubObjects.GithubMember> orgMembers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_members, container, false);
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
        orgMembers = AppLists.orgMembers;

        adapter = new MembersRecyclerAdapter(getContext(), orgMembers);
        mRecyclerView = (ContextMenuRecyclerView) view.findViewById(R.id.recyclerViewMembers);
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
        orgMembers.clear();

        AppLists.updateLists(context);

        orgMembers.addAll(AppLists.orgMembers);
        adapter.notifyDataSetChanged();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}
