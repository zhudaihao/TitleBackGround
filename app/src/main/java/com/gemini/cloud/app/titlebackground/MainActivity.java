package com.gemini.cloud.app.titlebackground;

import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "zdh";
    private ImageView iv_back, iv_icon;
    private RecyclerView recyclerView;
    private RelativeLayout rlTitle;
    private TextView tvTitle;
    private ImmersionBar mImmersionBar;//沉浸式


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImmersionBar();

        recyclerView = findViewById(R.id.recycler_view);
        rlTitle = findViewById(R.id.rl_title);
        tvTitle = findViewById(R.id.tv_title);

        View header = View.inflate(getApplicationContext(), R.layout.layout_header, null);
        iv_icon = header.findViewById(R.id.iv_icon);
        iv_back = header.findViewById(R.id.iv_back);

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
        myAdapter.addHeaderView(header);
        recyclerView.setAdapter(myAdapter);

        //加载背景，
        Glide.with(getApplicationContext())
                .load(R.mipmap.icon)
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
        //recyclerView的滑动，，判断向上滑动的距离是否大于y
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                //获取滑动距离，，通过布局管理器
                //1.获得视图的第一条木的下标
                //2.根据下标获得view条目,,,在获得条目的高度
                //3.下标*条目高度-可见视图距离顶部的高度
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = layoutManager.findFirstVisibleItemPosition();
                View firstVisiableChildView = layoutManager.findViewByPosition(position);
                int itemHeight = firstVisiableChildView.getHeight();
                int y = (position) * itemHeight - firstVisiableChildView.getTop();

                if (y <= 0) {
                    //设置标题的背景颜色
                    rlTitle.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
                    tvTitle.setTextColor(Color.argb((int) 0, 0, 0, 0));
                    Log.e(TAG, "----------------y <= 0----" + y);
                } else if (y > 0 && y <= 150) {
                    //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) y / 150;
                    float alpha = (255 * scale);
                    rlTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    tvTitle.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                    Log.e(TAG, "---------------y > 0 && y <= 150---" + y);
                } else {
                    //滑动到banner下面设置普通颜色
                    rlTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    tvTitle.setTextColor(Color.argb((int) 255, 0, 0, 0));
                    Log.e(TAG, "------------------" + y);
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
