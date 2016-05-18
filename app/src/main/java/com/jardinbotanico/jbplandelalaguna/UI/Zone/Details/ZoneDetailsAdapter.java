package com.jardinbotanico.jbplandelalaguna.UI.Zone.Details;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.Util.TouchImageView;
import com.jardinbotanico.jbplandelalaguna.Util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public class ZoneDetailsAdapter extends PagerAdapter {

    private Context context;
    private List<String> mData = new ArrayList<>();



    OnItemClickListener mItemClickListener;

    public ZoneDetailsAdapter(Context context,List<String> mData ) {
        this.context = context;
        this.mData = mData;

    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return super.saveState();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void startUpdate(View container) {
        // TODO Auto-generated method stub
        super.startUpdate(container);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        ViewPager pager = (ViewPager) container;
        pager.removeView((View) object);
    }

    public Context getActivity(){
        return this.context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View vi=container;
        vi = LayoutInflater.from(this.context).inflate(R.layout.item_zone_details, container, false);

        TouchImageView IVDescriptiom = (TouchImageView) vi.findViewById(R.id.IVDescriptiom);

        Glide.with(context)
                .load(Util.modifyDropboxUrl(this.mData.get(position)))
                .placeholder(R.drawable.holder_rectangular) // can also be a drawable
                .error(R.drawable.holder_rectangular) // will be displayed if the image cannot be loaded
                .crossFade()
                .into(IVDescriptiom);


        ViewPager pager = (ViewPager) container;
        pager.addView(vi);
        return vi;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String videoUrl);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
