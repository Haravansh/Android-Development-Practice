package com.harry.viewpager_fragement_pt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class MyPagerAdapter extends PagerAdapter {

    private Context mContext;

    public MyPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ModelCourse modelCourse = ModelCourse.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ViewGroup layout = (ViewGroup) inflater.inflate(modelCourse.getmLayoutID(), container, false);
        container.addView(layout);

        return layout;

    }



    @Override
    public int getCount() {
        return ModelCourse.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        ModelCourse customPagerEnum = ModelCourse.values()[position];
        return  mContext.getString(customPagerEnum.getmTitleID());
    }
}
