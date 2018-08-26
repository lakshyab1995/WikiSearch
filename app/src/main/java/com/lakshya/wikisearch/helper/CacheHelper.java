package com.lakshya.wikisearch.helper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lakshya.wikisearch.contract.CacheListener;
import com.lakshya.wikisearch.model.WikiPageModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CacheHelper {

    private static final String TAG = CacheHelper.class.getSimpleName();
    private Context mContext;
    private CacheListener mCacheListener;
    private Set<Integer> mViewedWikiIdList = new LinkedHashSet<>();
    private Set<WikiPageModel> mRecentViewedWikiSet = new LinkedHashSet<>();
    private List<WikiPageModel> mRecentViewedWiki = new ArrayList<>();

    public static CacheHelper with(Context context){
        CacheHelper cacheHelper = new CacheHelper();
        cacheHelper.mContext = context;
        return cacheHelper;
    }

    public void storeData(String wikiTitle, List<WikiPageModel> wikiPageModelList){
        Gson gson = new Gson();
        String wikiPageModels = gson.toJson(wikiPageModelList);
        SharedPrefHelper.with(mContext).add(wikiTitle, wikiPageModels);
    }

    public void retrieveData(String wikiTitle){
        String wikiPageModels = SharedPrefHelper.with(mContext).get(wikiTitle,"");
        if(!TextUtils.isEmpty(wikiPageModels)){
            Gson gson = new Gson();
            Type type = new TypeToken<List<WikiPageModel>>() {}.getType();
            List<WikiPageModel> wikiPageModels1 = gson.fromJson(wikiPageModels, type);
            mCacheListener.onCacheSuccess(wikiPageModels1);
        }
        else {
            mCacheListener.onCacheFailure(wikiTitle);
        }
    }

    public void storeViewedWiki(WikiPageModel wikiPageModel){
        //mRecentViewedWikiSet.add(wikiPageModel);
        mViewedWikiIdList.add(wikiPageModel.getWikiPageId());
        Gson gson = new Gson();
        String wikiPageIds = gson.toJson(mViewedWikiIdList);
        SharedPrefHelper.with(mContext).add("history", wikiPageIds);
        Log.d(TAG,"Added Page Ids: " + wikiPageIds);
        String wikiPageContent = gson.toJson(wikiPageModel);
        SharedPrefHelper.with(mContext).add(String.valueOf(wikiPageModel.getWikiPageId()), wikiPageContent);
        Log.d(TAG,"Added Wiki Page: " + wikiPageContent);
    }

    public List<WikiPageModel> retrieveViewedWikiList(){
        String wikiIds = SharedPrefHelper.with(mContext).get("history","");
        Gson gson = new Gson();
        Type type = new TypeToken<Set<Integer>>() {}.getType();
        Type type1 = new TypeToken<WikiPageModel>() {}.getType();
        if(!TextUtils.isEmpty(wikiIds)){
            Log.d(TAG,"Retrieved Ids : " + wikiIds);
            Set<Integer> viewedWikiIds = gson.fromJson(wikiIds, type);
            for(int wikiId : viewedWikiIds){
                String wikiPageContent = SharedPrefHelper.with(mContext).get(String.valueOf(wikiId), "");
                if(!TextUtils.isEmpty(wikiPageContent)){
                    Log.d(TAG, "Retrieved Wiki Page: " + wikiPageContent);
                    WikiPageModel wikiPageModel = gson.fromJson(wikiPageContent, type1);
                    mRecentViewedWikiSet.add(wikiPageModel);
                }
            }
            mRecentViewedWiki.addAll(mRecentViewedWikiSet);
        }
        return mRecentViewedWiki;
    }

    public void setCacheListener(CacheListener cacheListener){
        mCacheListener  = cacheListener;
    }
}
