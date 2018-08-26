package com.lakshya.wikisearch.presenter;

import com.lakshya.wikisearch.api.RestAdapter;
import com.lakshya.wikisearch.contract.MainContract;
import com.lakshya.wikisearch.contract.UpdateListener;
import com.lakshya.wikisearch.model.WikiPageModel;
import com.lakshya.wikisearch.model.WikiQueryModel;
import com.lakshya.wikisearch.model.WikiResponseModel;
import com.lakshya.wikisearch.model.WikiSearchModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract{

    private UpdateListener mUpdateListener;

    @Override
    public void fetchWikiPages(final String wikiTitle) {
        final List<WikiPageModel> mWikiPageModels = new ArrayList<>();
        new RestAdapter().getApiClient().fetchWikiPages(wikiTitle, wikiTitle).enqueue(new Callback<WikiResponseModel>() {
            @Override
            public void onResponse(Call<WikiResponseModel> call, Response<WikiResponseModel> response) {
                if(response.isSuccessful()){
                    WikiResponseModel wikiResponseModel = response.body();
                    WikiQueryModel wikiQueryModel = wikiResponseModel.getWikiQueryModel();
                    if(wikiQueryModel != null){
                        List<WikiPageModel> wikiPageModels = wikiQueryModel.getWikiPageModelList();
                        List<WikiSearchModel> wikiSearchModelList = wikiQueryModel.getWikiSearchModels();
                        //sorting the wiki pages in ascending order
                        for (WikiSearchModel wikiSearchModel : wikiSearchModelList){
                            for(WikiPageModel wikiPageModel : wikiPageModels){
                                if(wikiSearchModel.getWikiPageId() == wikiPageModel.getWikiPageId()){
                                    mWikiPageModels.add(wikiPageModel);
                                }
                            }
                        }
                    }
                    else {

                    }
                    //send list to activity to update the adapter
                    mUpdateListener.onSuccess(mWikiPageModels, wikiTitle);
                }
            }

            @Override
            public void onFailure(Call<WikiResponseModel> call, Throwable t) {

            }
        });
    }

    public void setUpdateListener(UpdateListener updateListener){
        mUpdateListener = updateListener;
    }
}
