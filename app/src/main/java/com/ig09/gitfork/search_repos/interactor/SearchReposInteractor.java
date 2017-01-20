package com.ig09.gitfork.search_repos.interactor;

import com.ig09.gitfork.data.model.Repo;
import com.ig09.gitfork.search_repos.presenter.SearchReposPresenterImpl;

import java.util.List;

/**
 * Created by Ivan on 19/01/2017.
 */
public interface SearchReposInteractor {

    public interface OnSearchReposFinishedListener {

        public void onSearchReposSuccess(List<Repo> listRepos);
        public void onSearchReposFailure(String error);

    }

    void searchRepos(String user, OnSearchReposFinishedListener listener);

}
