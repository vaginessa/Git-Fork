package com.ig09.gitfork.data.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("name")
    private String repoName;

    public Repo(){

    }

    public Repo(String repoName){
        this.repoName = repoName;
    }

    public String getRepoName(){
        return this.repoName;
    }

}