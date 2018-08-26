package com.lakshya.wikisearch.contract;

import com.lakshya.wikisearch.model.WikiPageModel;

import java.util.List;

public interface UpdateListener {

    void onSuccess(List<WikiPageModel> wikiPageModelList, String wikiTitle);
}
