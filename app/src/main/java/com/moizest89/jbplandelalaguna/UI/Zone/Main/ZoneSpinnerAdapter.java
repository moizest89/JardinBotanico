package com.moizest89.jbplandelalaguna.UI.Zone.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Fonts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/11/16.
 */
public class ZoneSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<String> mData = new ArrayList<>();
    private static LayoutInflater inflater=null;

    public ZoneSpinnerAdapter(Context context, List<String> mData) {
        this.context = context;
        this.mData = mData;

        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = inflater.inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTypeface(new Fonts().Roboto_Black(this.context));
        textView.setText(this.mData.get(position));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = inflater.inflate(R.layout.toolbar_spinner_item_actionbar, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(this.mData.get(position));
        return view;
    }
}
