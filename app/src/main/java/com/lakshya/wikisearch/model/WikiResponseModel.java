package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WikiResponseModel implements Serializable{

    @SerializedName("query")
    @Expose
    private WikiQueryModel mWikiQueryModel;

    public WikiQueryModel getWikiQueryModel() {
        return mWikiQueryModel;
    }

    public void setWikiQueryModel(WikiQueryModel wikiQueryModel) {
        mWikiQueryModel = wikiQueryModel;
    }
}
