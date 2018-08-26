package com.lakshya.wikisearch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lakshya.wikisearch.R;
import com.lakshya.wikisearch.contract.WikiPageClickListener;
import com.lakshya.wikisearch.model.WikiPageModel;

import java.util.ArrayList;
import java.util.List;

public class WikiAdapter extends RecyclerView.Adapter<WikiAdapter.WikiViewHolder> {

    private Context mContext;
    private List<WikiPageModel> mWikiPageModelList = new ArrayList<>();
    private WikiPageClickListener mWikiPageClickListener;

    public WikiAdapter(Context context, WikiPageClickListener wikiPageClickListener, List<WikiPageModel> wikiPageModels) {
        mContext = context;
        mWikiPageClickListener = wikiPageClickListener;
        mWikiPageModelList = wikiPageModels;
    }

    @NonNull
    @Override
    public WikiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_wiki, viewGroup, false);
        return new WikiViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WikiViewHolder wikiViewHolder, int position) {
        wikiViewHolder.mWikiTitle.setText(mWikiPageModelList.get(position).getWikiTitle());
        if (mWikiPageModelList.get(position).getDescriptionModel() != null && mWikiPageModelList.get(position).getDescriptionModel().getWikiDesc() != null) {
            wikiViewHolder.mWikiDesc.setText(mWikiPageModelList.get(position).getDescriptionModel().getWikiDesc().get(0));
        }
        if (mWikiPageModelList.get(position).getThumbnailModel() != null && !TextUtils.isEmpty(mWikiPageModelList.get(position).getThumbnailModel().getWikiImageUrl())) {
            Glide.with(mContext)
                    .load(mWikiPageModelList.get(position).getThumbnailModel().getWikiImageUrl())
                    .into(wikiViewHolder.mWikiImage);
        }
        wikiViewHolder.mLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWikiPageClickListener.onClick(view, mWikiPageModelList.get(wikiViewHolder.getAdapterPosition()));
            }
        });
    }

    public void setAdapterData(List<WikiPageModel> wikiPageModels) {
        mWikiPageModelList.clear();
        mWikiPageModelList = wikiPageModels;
        notifyDataSetChanged();
    }

    public void removeAdapterData() {
        mWikiPageModelList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWikiPageModelList.size();
    }

    public class WikiViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup mLayoutContainer;
        private ImageView mWikiImage;
        private TextView mWikiTitle;
        private TextView mWikiDesc;

        public WikiViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayoutContainer = itemView.findViewById(R.id.layout_container);
            mWikiImage = itemView.findViewById(R.id.wikiImage);
            mWikiTitle = itemView.findViewById(R.id.wikiTitle);
            mWikiDesc = itemView.findViewById(R.id.wikiDesc);
        }
    }
}
