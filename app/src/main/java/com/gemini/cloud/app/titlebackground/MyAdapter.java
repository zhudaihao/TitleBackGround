package com.gemini.cloud.app.titlebackground;



import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class MyAdapter extends BaseQuickAdapter<DetailsBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<DetailsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailsBean item) {



    }



}
