package com.ig09.gitfork.search_repos.presenter;

import com.ig09.gitfork.data.model.Repo;
import com.ig09.gitfork.search_repos.interactor.SearchReposInteractor;
import com.ig09.gitfork.search_repos.interactor.SearchReposInteractorImpl;
import com.ig09.gitfork.search_repos.view.SearchReposView;

import java.util.List;

/**
 * Created by Ivan on 19/01/2017.
 */
public class SearchReposPresenterImpl implements SearchReposPresenter, SearchReposInteractor.OnSearchReposFinishedListener {

    private SearchReposView mSearchReposView;
    private SearchReposInteractor mSearchReposInteractor;

    public SearchReposPresenterImpl(SearchReposView searchReposView){
        this.mSearchReposView = searchReposView;
        this.mSearchReposInteractor = new SearchReposInteractorImpl();
    }

    @Override
    public void searchUserRepos(String user) {
        mSearchReposView.hideSoftKeyboard();
        mSearchReposView.showProgressDialog();
        mSearchReposInteractor.searchRepos(user, this);
    }

    @Override
    public void onSearchReposSuccess(List<Repo> listRepos) {
        mSearchReposView.hideProgressDialog();
        mSearchReposView.showSearchedRepos(listRepos);
    }

    @Override
    public void onSearchReposFailure(String error) {
        mSearchReposView.hideProgressDialog();
        mSearchReposView.showSearchError(error);
    }
}
