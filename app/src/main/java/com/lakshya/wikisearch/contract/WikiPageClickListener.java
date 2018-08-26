package com.lakshya.wikisearch.contract;

import android.view.View;

import com.lakshya.wikisearch.model.WikiPageModel;

public interface WikiPageClickListener {

    void onClick(View view, WikiPageModel wikiPageModel);
}
