package com.moizest89.jbplandelalaguna.UI.Family.Details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.FamilyImage;
import com.moizest89.jbplandelalaguna.Data.models.TipImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.ImageViewWithRatio;
import com.moizest89.jbplandelalaguna.Util.OnItemClickListener;
import com.moizest89.jbplandelalaguna.Util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by @moizest89 in SV on 5/14/16.
 */
public class FamilyDescriptionImagesAdapter extends RecyclerView.Adapter<FamilyDescriptionImagesAdapter.Holder>{


    private Context context;
    private List<FamilyImage> mData = new ArrayList<>();

    OnItemClickListener mItemClickListener;
    private final static String TAG = FamilyDescriptionImagesAdapter.class.getSimpleName();

    public FamilyDescriptionImagesAdapter(Context context, List<FamilyImage> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_tip_list_images, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        FamilyImage familyImage = this.mData.get(position);

        Glide.with(context)
                .load(Util.modifyDropboxUrl(familyImage.getUrl()))
                .placeholder(R.drawable.holder_rectangular) // can also be a drawable
                .error(R.drawable.holder_rectangular) // will be displayed if the image cannot be loaded
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
