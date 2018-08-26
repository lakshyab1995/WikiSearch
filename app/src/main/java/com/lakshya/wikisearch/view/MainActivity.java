package com.lakshya.wikisearch.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.lakshya.wikisearch.R;
import com.lakshya.wikisearch.WikiUtility;
import com.lakshya.wikisearch.WikiConstants;
import com.lakshya.wikisearch.adapter.WikiAdapter;
import com.lakshya.wikisearch.contract.CacheListener;
import com.lakshya.wikisearch.contract.MainContract;
import com.lakshya.wikisearch.contract.UpdateListener;
import com.lakshya.wikisearch.contract.WikiPageClickListener;
import com.lakshya.wikisearch.helper.CacheHelper;
import com.lakshya.wikisearch.model.WikiPageModel;
import com.lakshya.wikisearch.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, UpdateListener, WikiPageClickListener, CacheListener, View.OnTouchListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SearchView mSearchView;
    private RecyclerView mWikiList;
    private MainContract mMainContract;
    private WikiAdapter mWikiAdapter;
    private CacheHelper mCacheHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWikiList = findViewById(R.id.wikiList);
        mMainContract = new MainPresenter();
        mMainContract.setUpdateListener(this);
        mWikiList.setLayoutManager(new LinearLayoutManager(this));
        mWikiList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mWikiList.setOnTouchListener(this);
        mCacheHelper = CacheHelper.with(this);
        mCacheHelper.setCacheListener(this);
        List<WikiPageModel> wikiPageModels = mCacheHelper.retrieveViewedWikiList();
        //mCacheHelper.retrieveAllData();
        mWikiAdapter = new WikiAdapter(this, this, wikiPageModels);
        mWikiList.setAdapter(mWikiAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint(getString(R.string.search_hint));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String wikiTitle) {
        if (TextUtils.isEmpty(wikiTitle)) {
            List<WikiPageModel> wikiPageModels = mCacheHelper.retrieveViewedWikiList();
            mWikiAdapter.setAdapterData(wikiPageModels);
        } else {
            mCacheHelper.retrieveData(wikiTitle);
        }
        return true;
    }

    @Override
    public void onSuccess(List<WikiPageModel> wikiPageModelList, String wikiTitle) {
        mWikiAdapter.setAdapterData(wikiPageModelList);
        mCacheHelper.storeData(wikiTitle, wikiPageModelList);
    }

    @Override
    public void onClick(View view, WikiPageModel wikiPageModel) {
        mCacheHelper.storeViewedWiki(wikiPageModel);
        Intent intent = new Intent(this, WikiWebActivity.class);
        intent.putExtra("WikiPageUrl", wikiPageModel.getWikiPageUrl());
        startActivity(intent);
    }

    @Override
    public void onCacheSuccess(List<WikiPageModel> wikiPageModelList) {
        //Cache Hit, Fetching data from cache
        mWikiAdapter.setAdapterData(wikiPageModelList);
    }

    @Override
    public void onCacheFailure(String wikiTitle) {
        //Cache Miss, fetching data from API
        mMainContract.fetchWikiPages(wikiTitle);
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WikiUtility.hideSoftKeyboard(this);
        return false;
    }
}
