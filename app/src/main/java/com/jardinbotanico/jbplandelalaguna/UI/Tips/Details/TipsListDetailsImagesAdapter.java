package com.jardinbotanico.jbplandelalaguna.UI.Tips.Details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jardinbotanico.jbplandelalaguna.Data.models.TipImage;
import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.Util.ImageViewWithRatio;
import com.jardinbotanico.jbplandelalaguna.Util.OnItemClickListener;
import com.jardinbotanico.jbplandelalaguna.Util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by @moizest89 in SV on 5/12/16.
 */
public class TipsListDetailsImagesAdapter extends RecyclerView.Adapter<TipsListDetailsImagesAdapter.Holder> {

    private Context context;
    private List<TipImage> mData = new ArrayList<>();
    OnItemClickListener mItemClickListener;
    private final static String TAG = TipsListDetailsImagesAdapter.class.getSimpleName();

    public TipsListDetailsImagesAdapter(Context context, List<TipImage> tipImages) {
        this.context = context;
        this.mData = tipImages;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_tip_list_images, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        TipImage tipImage = this.mData.get(position);

        Glide.with(context)
                .load(Util.modifyDropboxUrl(tipImage.getUrl()))
                .placeholder(R.drawable.holder_square) // can also be a drawable
                .error(R.drawable.holder_square) // will be displayed if the image cannot be loaded
                .crossFade()
                .into(holder.IVRatio);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }



    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.IVRatio)
        ImageViewWithRatio IVRatio;

        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            IVRatio.setX(1.0f);
            IVRatio.setY(1.0f);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
