package com.histudio.ui.custom.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.histudio.base.entity.Banner;
import com.histudio.ui.R;
import com.histudio.ui.custom.HiImageView;

import java.util.LinkedList;
import java.util.List;

/**
 * banner控件
 *
 * @author laolin
 */
public class MBannerView extends FrameLayout {

    private AutoScrollViewPager mPosterPager;
    private LinearLayout pointsLayout;
    private TextView mPonitText;
    private int index = 0;
    private List<Banner> banners;
    /**
     * 标记点集合
     */
    private List<ImageView> points;
    /**
     * 广告个数
     */
    private int count = 5;
    /**
     * 循环间隔
     */
    private int interval = 4000;

    private PosterPagerAdapter mAdapter;

    public MBannerView(Context context) {
        super(context);
    }

    public MBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 最先调用，保证有数据
     */
    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }


    public void initView() {
        removeAllViews();
        index = 0;
        points = new LinkedList<>();
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.banner_view, null);
        mPosterPager = (AutoScrollViewPager) contentView.findViewById(R.id.poster_pager);
        pointsLayout = (LinearLayout) contentView.findViewById(R.id.poster_points);
        initPoints();
        initPoster();
        addView(contentView);
    }

    private void initPoints() {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(8, 15, 8, 15);
        count = banners.size();
        if (count > 1) {
            for (int i = 0; i < count; i++) {
                // 添加标记点
                ImageView point = new ImageView(getContext());

                if (i == index % count) {
                    point.setBackgroundResource(R.drawable.guid_page_indicator_focused);
                } else {
                    point.setBackgroundResource(R.drawable.guid_page_indicator_unfocused);
                }
                point.setLayoutParams(lp);

                points.add(point);
                pointsLayout.addView(point);

            }
        }
    }

    private void initPoster() {

        mAdapter = new PosterPagerAdapter();
        mPosterPager.setAdapter(mAdapter);
        mPosterPager.setCurrentItem(count * 500);
        mPosterPager.setInterval(interval);
        mPosterPager.setOnPageChangeListener(new PosterPageChange());
        // 在刚进入onresume中，数据还没返回，所以导致无法滑动，刚开始时手动开始
        mPosterPager.startAutoScroll();
        mPosterPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPosterPager.stopAutoScroll();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        mPosterPager.startAutoScroll();

                        break;
                    case MotionEvent.ACTION_UP:
                        mPosterPager.startAutoScroll();
                        break;

                    default:
                        break;
                }

                return false;
            }

        });

    }

    class PosterPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (count > 1) {
                return Integer.MAX_VALUE;
            } else {
                return 1;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            HiImageView imageView = (HiImageView) LayoutInflater.from(getContext()).inflate(R.layout.banner_item, null);
            //            imageView.asyncLoadImage(UUtil.getBannerImage(banners.get(position % count)));
            imageView.asyncLoadImage(banners.get(position % count).getBanner_image_url());
            (container).addView(imageView);

            imageView.setOnClickListener(new PosterClickListener(position % count));

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((ImageView) object);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    class PosterPageChange implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            index = position;
            if (count > 1) {
                for (int i = 0; i < count; i++) {
                    points.get(i).setBackgroundResource(R.drawable.guid_page_indicator_unfocused);
                }

                points.get(position % count).setBackgroundResource(R.drawable.guid_page_indicator_focused);
            }
        }

    }

    class PosterClickListener implements OnClickListener {

        private int position;

        public PosterClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (iPosterClickListener != null) {
                iPosterClickListener.setOnPosterClick(position);
            }

        }

    }

    public void onResume() {
        if (mPosterPager != null) {
            mPosterPager.startAutoScroll();
        }
    }

    public void onPause() {
        if (mPosterPager != null) {
            mPosterPager.stopAutoScroll();
        }
    }

    public interface IPosterClickListener {
        void setOnPosterClick(int position);
    }

    private IPosterClickListener iPosterClickListener = null;

    public void setOnPosterClickListener(IPosterClickListener iPosterClickListener) {
        this.iPosterClickListener = iPosterClickListener;
    }

    public void updateView() {
        mAdapter.notifyDataSetChanged();
    }

}
