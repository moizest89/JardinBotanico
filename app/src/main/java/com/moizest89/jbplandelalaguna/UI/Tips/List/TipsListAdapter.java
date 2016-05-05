package com.moizest89.jbplandelalaguna.UI.Tips.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.Tip;
import com.moizest89.jbplandelalaguna.Data.models.TipImage;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Fonts;
import com.moizest89.jbplandelalaguna.Util.ImageViewWithRatio;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsListAdapter extends RecyclerView.Adapter<TipsListAdapter.Holder> {

    private Context context;
    private List<Tip> mData = new ArrayList<>();

    public TipsListAdapter(Context context, List<Tip> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_tips_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Tip tip = this.mData.get(position);

        holder.TVTipName.setTypeface(new Fonts().RobotoCondensed_Regular(this.context));
        holder.TVTipDescription.setTypeface(new Fonts().RobotoCondensed_Regular(this.context));

        holder.TVTipName.setText(tip.getName());
        holder.TVTipDescription.setText(tip.getDescription());

        //Image
        List<TipImage> tipImage = tip.getTipImages();

        if(tipImage.size() > 0) {
            Glide.with(context)
                    .load(tipImage.get(0).getUrl())
//                .placeholder(R.drawable.placeholder) // can also be a drawable
//                .error(R.drawable.placeholder) // will be displayed if the image cannot be loaded
                    .centerCrop()
                    .into(holder.IVTipImage);
        }
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.IVTipImage)
        ImageViewWithRatio IVTipImage;
        @Bind(R.id.TVTipName)
        TextView TVTipName;
        @Bind(R.id.TVTipDescription)
        TextView TVTipDescription;

        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            this.IVTipImage.setX(16.0f);
            this.IVTipImage.setY(9.0f);


        }
    }
}
