package com.ig09.gitfork.search_repos.view;

import com.ig09.gitfork.data.model.Repo;

import java.util.List;

/**
 * Created by Ivan on 19/01/2017.
 */
public interface SearchReposView {

    void showProgressDialog();

    void hideProgressDialog();

    void hideSoftKeyboard();

    void showSearchedRepos(List<Repo> listRepos);

    void showSearchError(String error);
}
