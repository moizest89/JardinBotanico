package com.moizest89.jbplandelalaguna.UI.Tips.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moizest89.jbplandelalaguna.Data.models.Formules;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.ImageViewWithRatio;
import com.moizest89.jbplandelalaguna.Util.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.Holder> {

    private Context context;
    private List<TipsCategory> mData = new ArrayList<>();

    OnItemClickListener onItemClickListener;

    public TipsAdapter(Context context, List<TipsCategory> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_tip_categories, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TipsCategory category = this.mData.get(position);
        holder.TVTitle.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.IVCell)
        ImageViewWithRatio IVCell;
        @Bind(R.id.TVTitle)
        TextView TVTitle;


        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            IVCell.setX(1.0f);
            IVCell.setY(1.0f);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
