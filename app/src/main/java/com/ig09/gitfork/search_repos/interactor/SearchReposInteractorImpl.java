package com.ig09.gitfork.search_repos.interactor;

import com.ig09.gitfork.data.model.Repo;
import com.ig09.gitfork.data.net.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 19/01/2017.
 */
public class SearchReposInteractorImpl implements SearchReposInteractor {

    private final String GITHUB_API_URL = "https://api.github.com";

    public SearchReposInteractorImpl(){

    }

    private Retrofit initRetrofitService(){

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(GITHUB_API_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

        return retrofit;
    }

    private GithubService createGithubService(){

        Retrofit retrofitService = initRetrofitService();

        GithubService githubService = retrofitService.create(GithubService.class);

        return githubService;
    }

    @Override
    public void searchRepos(String user, final OnSearchReposFinishedListener listener) {

        GithubService githubService = createGithubService();

        Call<List<Repo>> listRepos = githubService.listRepos(user);

        listRepos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                listener.onSearchReposSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

                listener.onSearchReposFailure(t.getMessage());

            }
        });

    }
}
