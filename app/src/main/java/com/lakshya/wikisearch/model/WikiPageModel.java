package com.lakshya.wikisearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WikiPageModel implements Serializable{

    @SerializedName("pageid")
    @Expose
    private int mWikiPageId;

    @SerializedName("title")
    @Expose
    private String mWikiTitle;

    @SerializedName("fullurl")
    @Expose
    private String mWikiPageUrl;

    @SerializedName("thumbnail")
    @Expose
    private ThumbnailModel mThumbnailModel;

    @SerializedName("terms")
    @Expose
    private DescriptionModel mDescriptionModel;

    public String getWikiTitle() {
        return mWikiTitle;
    }

    public void setWikiTitle(String wikiTitle) {
        mWikiTitle = wikiTitle;
    }

    public String getWikiPageUrl() {
        return mWikiPageUrl;
    }

    public void setWikiPageUrl(String wikiPageUrl) {
        mWikiPageUrl = wikiPageUrl;
    }

    public int getWikiPageId() {
        return mWikiPageId;
    }

    public void setWikiPageId(int wikiPageId) {
        mWikiPageId = wikiPageId;
    }

    public ThumbnailModel getThumbnailModel() {
        return mThumbnailModel;
    }

    public void setThumbnailModel(ThumbnailModel thumbnailModel) {
        mThumbnailModel = thumbnailModel;
    }

    public DescriptionModel getDescriptionModel() {
        return mDescriptionModel;
    }

    public void setDescriptionModel(DescriptionModel descriptionModel) {
        mDescriptionModel = descriptionModel;
    }

    @Override
    public String toString() {
        return "ID: " + getWikiPageId() + "Title: " + getWikiTitle() + "Image Url: " + getThumbnailModel().getWikiImageUrl();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mWikiTitle.hashCode();
        result = 31 * result + mWikiPageId;
        result = 31 * result + mWikiPageUrl.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof WikiPageModel)) {
            return false;
        }

        WikiPageModel wiki = (WikiPageModel) o;

        return wiki.mWikiTitle.equals(mWikiTitle) &&
                wiki.mWikiPageId == mWikiPageId &&
                wiki.mWikiPageUrl.equals(mWikiPageUrl);
    }
}
