package com.moizest89.jbplandelalaguna.Util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moizest89.jbplandelalaguna.Data.models.DummyData;
import com.moizest89.jbplandelalaguna.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;



public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.Holder> {

    private List<DummyData> mData = new ArrayList<>();
    private Context context;
    private final static String TAG = DummyAdapter.class.getSimpleName();

    OnItemClickListener mOnItemClickListener;


    public DummyAdapter(List<DummyData> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.item_dummy_list, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        DummyData data = this.mData.get(position);

        Log.e(TAG, data.getImage());

        Glide.with(context)
            .load(data.getImage())
                .centerCrop()
                .into(holder.IVCell);

        holder.TVTitle.setText(data.getName());
        holder.TVTitle.setTypeface(new Fonts().Roboto_Regular(this.context));

    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        @Bind(R.id.IVCell)
        ImageViewWithRatio IVCell;
        @Bind(R.id.TVTitle)
        TextView TVTitle;

        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            IVCell.setX(3.0f);
            IVCell.setY(2.0f);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClickListener(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }



}
