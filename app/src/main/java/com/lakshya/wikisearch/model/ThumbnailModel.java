package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThumbnailModel implements Serializable{

    @SerializedName("source")
    @Expose
    private String mWikiImageUrl;

    public String getWikiImageUrl() {
        return mWikiImageUrl;
    }

    public void setWikiImageUrl(String wikiImageUrl) {
        mWikiImageUrl = wikiImageUrl;
    }
}
