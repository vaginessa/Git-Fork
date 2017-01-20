package com.ig09.gitfork.search_repos.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ig09.gitfork.BuildConfig;
import com.ig09.gitfork.R;
import com.ig09.gitfork.data.model.Repo;
import com.ig09.gitfork.search_repos.presenter.SearchReposPresenter;
import com.ig09.gitfork.search_repos.presenter.SearchReposPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchReposActivity extends AppCompatActivity implements SearchReposView {

    private final String LOG_TAG = SearchReposActivity.class.getSimpleName();

    @BindView(R.id.userInput)
    EditText mUserInput;

    @BindView(R.id.btnBuscarRepos)
    Button mSearchReposBtn;

    @BindView(R.id.logoImageView)
    ImageView mLogoImageView;

    @BindView(R.id.txtReposList)
    TextView mTxtReposList;

    @BindView(R.id.reposList)
    RecyclerView mReposList;

    ProgressDialog progressDialog;

    SearchReposPresenter mSearchReposPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mReposList.setHasFixedSize(true);
        mReposList.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        mSearchReposPresenter = new SearchReposPresenterImpl(this);

        if (BuildConfig.DEBUG){
            mUserInput.setText("ivanguerra09");
        }

/*
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
*/

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @OnClick(R.id.btnBuscarRepos)
    void onSearchUserReposListener() {

        String user = mUserInput.getText().toString().trim();

        mSearchReposPresenter.searchUserRepos(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setQueryHint("Ingresar usuario..");
*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressDialog() {

        progressDialog.setTitle("GitFork");
        progressDialog.setMessage("Buscando repositorios...");

        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {

        if (progressDialog != null){
            progressDialog.dismiss();
        }

    }

    @Override
    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showSearchedRepos(List<Repo> listRepos) {
        /*Log.v(LOG_TAG, listRepos.get(0).getRepoName());*/

        if (listRepos != null){
            mReposList.setAdapter(new ReposAdapter(listRepos));

            mLogoImageView.setVisibility(View.GONE);
            mTxtReposList.setVisibility(View.VISIBLE);
            mReposList.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(this, "NO SE ENCONTRARON RESULTADOS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSearchError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

        if (mReposList.getVisibility() == View.VISIBLE){
            mTxtReposList.setVisibility(View.GONE);
            mReposList.setVisibility(View.GONE);
        }

        mLogoImageView.setVisibility(View.VISIBLE);

    }

}
