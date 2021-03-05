package com.gemini.cloud.app.titlebackground;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "zdh";
    private ImageView iv_back, iv_icon;
    private RecyclerView recyclerView;
    private RelativeLayout rlTitle;
    private TextView tvTitle;
    private ImmersionBar mImmersionBar;//沉浸式
    private ScrollView scrollView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initImmersionBar();
        recyclerView = findViewById(R.id.recycler_view);
        rlTitle = findViewById(R.id.rl_title);
        tvTitle = findViewById(R.id.tv_title);
        scrollView=findViewById(R.id.scrollView);
        iv_icon = findViewById(R.id.iv_icon);
        iv_back =findViewById(R.id.iv_back);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        List<DetailsBean> data = new ArrayList<>();
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());
        data.add(new DetailsBean());

        MyAdapter myAdapter = new MyAdapter(R.layout.item_image, data);
        recyclerView.setAdapter(myAdapter);

        //加载背景，
        Glide.with(getApplicationContext())
                .load(R.mipmap.icon_text)
                .dontAnimate()
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(this, 14, 5))
                .into(iv_back);


        Glide.with(getApplicationContext())
                .load(R.mipmap.icon)
                .dontAnimate()
                .into(iv_icon);


        setTitle();


    }

    private void setTitle() {
        //ScrollView 滑动变色
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int height = rlTitle.getMeasuredHeight(); //title 高
                Log.e(TAG,"----------mHeight " + height);
                if (scrollY <= 0) {
                    rlTitle.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
                    tvTitle.setTextColor(Color.argb((int) 0, 0, 0, 0));
                } else if (scrollY <= height) {
                    //获取渐变率
                    float scale = (float) scrollY / height;
                    float alpha = (255 * scale);
                    rlTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    tvTitle.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                } else {
                    rlTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    tvTitle.setTextColor(Color.argb((int) 255, 0, 0, 0));

                }
            }
        });
    }



    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.colorBar)//默认白色
                .init();
    }

}
