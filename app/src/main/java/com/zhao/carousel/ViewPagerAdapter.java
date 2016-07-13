package com.zhao.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ProjectInfo> mList;
    private Context mContext;
    private boolean isClick = false;
    ImageOptions imageOptions;

    public ViewPagerAdapter(List<ProjectInfo> list, Context context,boolean isClick) {
        this.mContext = context;
        this.mList = list;
        this.isClick = isClick;
        imageOptions=new ImageOptions.Builder()
//                .setLoadingDrawableId(R.mipmap.zyls_lunbo)
                .setConfig(Bitmap.Config.ARGB_8888)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
    }

    /**
     * 返回多少page
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 判断当前滑动view等不等进来的对象
     *
     * true: 表示不去创建，使用缓存 false:去重新创建 view： 当前滑动的view
     * object：将要进入的新创建的view，由instantiateItem方法创建
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 类似于BaseAdapger的getView方法 用了将数据设置给view 由于它最多就3个界面，不需要viewHolder
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.dapter_ad, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        final ProjectInfo info = mList.get(position % mList.size());
        String ad = info.getImgUrl();
        x.image().bind(imageView,ad,imageOptions);
        if (isClick) {
            imageView.setClickable(true);
            /**
             * 设置图片点击事件
             */
            imageView.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
//                        Intent intent = new Intent(mContext, HouseDetail.class);
//                        //防止activity重复启动
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                                | Intent.FLAG_ACTIVITY_SINGLE_TOP
//                                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("url", info.getUrl());
//                        intent.putExtras(bundle);
//                        mContext.startActivity(intent);
                    }
                });
                                             }else {
                                                 imageView.setClickable(false);
                                             }
                                             container.addView(view);// 一定不能少，将view加入到viewPager中

                                             return view;
                                         }

                    /**
                     * 销毁page position： 当前需要消耗第几个page object:当前需要消耗的page
                     */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        }