package com.lakshya.wikisearch.contract;

import com.lakshya.wikisearch.model.WikiPageModel;

import java.util.List;

public interface MainContract {

    void fetchWikiPages(String wikiTitle);
    void setUpdateListener(UpdateListener updateListener);
}
