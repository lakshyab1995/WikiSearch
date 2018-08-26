package com.lakshya.wikisearch.contract;

import com.lakshya.wikisearch.model.WikiPageModel;

import java.util.List;

public interface CacheListener {

    void onCacheSuccess(List<WikiPageModel> wikiPageModelList);

    void onCacheFailure(String message);
}
