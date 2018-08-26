package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class
WikiSearchModel implements Serializable{

    @SerializedName("pageid")
    @Expose
    private int mWikiPageId;

    public int getWikiPageId() {
        return mWikiPageId;
    }

    public void setWikiPageId(int wikiPageId) {
        mWikiPageId = wikiPageId;
    }
}
