package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WikiQueryModel implements Serializable{

    @SerializedName("pages")
    @Expose
    private List<WikiPageModel> mWikiPageModelList;

    @SerializedName("prefixsearch")
    @Expose
    private List<WikiSearchModel> mWikiSearchModels;

    public List<WikiPageModel> getWikiPageModelList() {
        return mWikiPageModelList;
    }

    public void setWikiPageModelList(List<WikiPageModel> wikiPageModelList) {
        mWikiPageModelList = wikiPageModelList;
    }

    public List<WikiSearchModel> getWikiSearchModels() {
        return mWikiSearchModels;
    }

    public void setWikiSearchModels(List<WikiSearchModel> wikiSearchModels) {
        mWikiSearchModels = wikiSearchModels;
    }
}
