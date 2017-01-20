package com.ig09.gitfork.search_repos.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ig09.gitfork.R;
import com.ig09.gitfork.data.model.Repo;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ivan on 19/01/2017.
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    private List<Repo> listRepos;

    public ReposAdapter(List<Repo> listRepos){
        this.listRepos = listRepos;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);

        RepoViewHolder rVH = new RepoViewHolder(v);

        return rVH;
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

        holder.mRepoNameTextView.setText(listRepos.get(position).getRepoName());
    }

    @Override
    public int getItemCount() {
        return (listRepos != null)? listRepos.size() : 0;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

         private TextView mRepoNameTextView;

        public RepoViewHolder(View itemView) {
            super(itemView);

            mRepoNameTextView = (TextView) itemView.findViewById(R.id.textViewRepoName);
        }
    }

}
