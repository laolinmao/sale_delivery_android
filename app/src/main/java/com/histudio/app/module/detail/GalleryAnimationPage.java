package com.histudio.app.module.detail;


import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.histudio.app.R;
import com.histudio.app.util.Constants;
import com.histudio.ui.base.HiLoadablePage;
import com.histudio.ui.custom.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * 图片走廊界面
 *
 * @author laolin
 */
public class GalleryAnimationPage extends HiLoadablePage implements OnPageChangeListener {

    TextView positionTv;

    private int position;
    private ArrayList<String> mPhotos;

    public GalleryAnimationPage(Activity context) {
        super(context);
    }

    @Override
    protected boolean isNeedLoadTask() {

        return false;
    }

    @Override
    protected boolean isShowEmptyView() {

        return false;
    }

    @Override
    protected View createContentView() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.galleryactivity_animation_layout, null);
        ViewPagerFixed mViewPaper = (ViewPagerFixed) view.findViewById(R.id.pager);

        mPhotos = mBundle.getStringArrayList(Constants.DETAIL_PHOTO_LIST);
        position = mBundle.getInt(Constants.DETAIL_PHOTO_POSITION);


        MyPaperAdapter mPagerAdapter = new MyPaperAdapter(mPhotos);
        mViewPaper.setAdapter(mPagerAdapter);
        mViewPaper.setOffscreenPageLimit(1);
        mViewPaper.setCurrentItem(position);
        mViewPaper.setOnPageChangeListener(this);
        TextView sumTv = (TextView) view.findViewById(R.id.sum);
        positionTv = (TextView) view.findViewById(R.id.position);
        positionTv.setText(String.valueOf(position + 1));
        sumTv.setText(String.valueOf(mPhotos.size()));
        return view;
    }

    @Override
    protected void loadingMoreItemTask() {

    }

    @Override
    protected void loadingTask() {

    }

    private class MyPaperAdapter extends PagerAdapter {
        private List<String> photos;

        public MyPaperAdapter(List<String> mPhotos) {
            this.photos = mPhotos;
        }

        @Override
        public int getCount() {
            if (photos == null) {
                return 0;
            } else {
                return photos.size();
            }
        }

        @Override
        public View instantiateItem(final ViewGroup container, final int position) {
            PhotoView photoView = (PhotoView) LayoutInflater.from(getContext()).inflate(R.layout.gallery_general_layout, null);
            photoView.asyncLoadImage(photos.get(position));
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    finish();
                }
            });
            photoView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    Bitmap bmp = HiManager.getBean(FrescoManager.class).getCachedBitmapFromFrescoLocalCache(photos.get(position));
//                    if (bmp != null) {
//                        HiManager.getBean(PhoManager.class).handleGenderClick(mActivity, bmp);
//                    }
                    return false;
                }
            });

            container.addView(photoView);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        positionTv.setText("" + (position + 1));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPhotos(ArrayList<String> photos) {
        mPhotos = photos;
    }
}
