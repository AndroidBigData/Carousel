package com.zhao.carousel;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class CarousePager implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private Context context;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<ProjectInfo> mList;
    private LinearLayout mDotLayout;
    private LayoutInflater inflater;
    private boolean isClick = false;


    public CarousePager(Context context, List<ProjectInfo> mList, LayoutInflater inflater,boolean isClick) {
        super();
        this.context = context;
        this.mList = mList;
        this.isClick = isClick;
        this.inflater = inflater;
    }

    /**
     * handler处理定时任务
     */
    private Handler mMyHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
            mMyHandler.sendEmptyMessageDelayed(0, 5000);
        }
    };

    public View initView(){
        if (context == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.lunbo_ad, null);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.dot_layout);
        mViewPager.setOnPageChangeListener(this);
        initDots();
        mViewPagerAdapter = new ViewPagerAdapter(mList, context,isClick);
        mViewPager.setAdapter(mViewPagerAdapter);

        //默认在1亿多
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2 )% mList.size()));
        //3秒定时
        mMyHandler.sendEmptyMessageDelayed(0, 3000);
        updateIntroAndDot();
        return view;
    }

    /**
     * 初始化dot
     */
    private void initDots(){
        for (int i = 0; i < mList.size(); i++) {
            View view = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            if(i!=0){//第一个点不需要左边距
                params.leftMargin = 7;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            mDotLayout.addView(view);
        }
    }

    /**
     * 更新文本
     */
    private void updateIntroAndDot(){
        int currentPage = mViewPager.getCurrentItem()%mList.size();

        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            mDotLayout.getChildAt(i).setEnabled(i==currentPage);//设置setEnabled为true的话 在选择器里面就会对应的使用白色颜色
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        updateIntroAndDot();
    }



}
