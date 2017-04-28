package com.example.administrator.pushtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

public class MainActivity extends AppCompatActivity{
    private Banner mBanner;
    private TextView mTextView;

    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};

    String[] titles=new String[]{"十大星级品牌联盟","嗨购5折不要停","12趁现在","12.12趁现在","实打实大顶顶顶顶"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"UBz5CTUHqGA5HVPsyW69i7CB");

        mBanner= (Banner) findViewById(R.id.banner);
        mTextView= (TextView) findViewById(R.id.textView);

        initBanner();
        initBannerListener();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        String result=intent.getStringExtra("result");
        if(result != null){
            mTextView.setText(result);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initBannerListener() {
        mBanner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Log.d("MainActivity","加载中");
                Glide.with(getApplicationContext()).load(url).into(view);
                Log.d("MainActivity","加载完");
            }
        });

        mBanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(MainActivity.this, "你点击了", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initBanner() {
        //显示圆形指示器和标题
        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        //设置轮播样式:居中
        mBanner.setIndicatorGravity(Banner.CENTER);
        //设置轮播要显示的标题和图片对应
        mBanner.setBannerTitle(titles);
        //自动轮播
        mBanner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(3000);

    }


}
