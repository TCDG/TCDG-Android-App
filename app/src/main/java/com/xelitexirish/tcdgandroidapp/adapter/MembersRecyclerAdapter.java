package com.xelitexirish.tcdgandroidapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xelitexirish.tcdgandroidapp.R;
import com.xelitexirish.tcdgandroidapp.handler.GithubObjects;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersRecyclerAdapter extends RecyclerView.Adapter<MembersRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<GithubObjects.GithubMember> orgMembers;

    public MembersRecyclerAdapter(Context context, List<GithubObjects.GithubMember> members){
        this.context = context;
        this.orgMembers = members;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerView = inflater.inflate(R.layout.item_recycler_member, parent, false);
        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GithubObjects.GithubMember orgMember = orgMembers.get(position);

        CircleImageView circleImageView = holder.circleImageViewProfilePic;
        TextView textViewMemberUsername = holder.textViewMemberUsername;
        TextView textViewMemberUrl = holder.textViewMemberUrl;

        Picasso.with(context).load(orgMember.getProfilePictureUrl()).noFade().into(circleImageView);
        textViewMemberUsername.setText(orgMember.getUsername());
        textViewMemberUrl.setText(orgMember.getUrl());
    }

    @Override
    public int getItemCount() {
        return orgMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView circleImageViewProfilePic;
        public TextView textViewMemberUsername;
        public TextView textViewMemberUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            this.circleImageViewProfilePic = (CircleImageView) itemView.findViewById(R.id.circleImageViewProfilePic);
            this.textViewMemberUsername = (TextView) itemView.findViewById(R.id.textViewMemberUsername);
            this.textViewMemberUrl = (TextView) itemView.findViewById(R.id.textViewMemberUrl);
        }
    }
}
