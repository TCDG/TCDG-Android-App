package com.xelitexirish.tcdgandroidapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xelitexirish.tcdgandroidapp.R;
import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;

import java.util.List;

public class RepoRecyclerAdapter extends RecyclerView.Adapter<RepoRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<GithubObjects.GithubRepo> orgRepos;

    public RepoRecyclerAdapter(Context context, List<GithubObjects.GithubRepo> orgRepos){
        this.context = context;
        this.orgRepos = orgRepos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerView = inflater.inflate(R.layout.item_recycler_repo, parent, false);
        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GithubObjects.GithubRepo repo = orgRepos.get(position);

        TextView textViewRepoName = holder.textViewRepoName;
        textViewRepoName.setText(repo.getName());

        TextView textViewRepoDescription = holder.textViewRepoDescription;
        textViewRepoDescription.setText(repo.getDescription());
    }

    @Override
    public int getItemCount() {
        return orgRepos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewRepoName;
        public TextView textViewRepoDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            this.textViewRepoName = (TextView) itemView.findViewById(R.id.textViewRepoName);
            this.textViewRepoDescription = (TextView) itemView.findViewById(R.id.textViewRepoDescription);
        }
    }
}
