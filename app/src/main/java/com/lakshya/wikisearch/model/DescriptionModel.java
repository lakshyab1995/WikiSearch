package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DescriptionModel implements Serializable{

    @SerializedName("description")
    @Expose
    private List<String> mWikiDesc;

    public List<String> getWikiDesc() {
        return mWikiDesc;
    }

    public void setWikiDesc(List<String> wikiDesc) {
        mWikiDesc = wikiDesc;
    }
}
