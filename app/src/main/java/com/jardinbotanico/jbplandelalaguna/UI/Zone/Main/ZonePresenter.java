package com.jardinbotanico.jbplandelalaguna.UI.Zone.Main;


import com.jardinbotanico.jbplandelalaguna.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/11/16.
 */
public class ZonePresenter {

    private ZoneActivity context;
    private String mSpinnerHeader;
    private String mSpinnerZoneLabel;

    public ZonePresenter(ZoneActivity context) {
        this.context = context;
        this.mSpinnerHeader = this.context.getResources().getString(R.string.place_spinner_label_select_item);
        this.mSpinnerZoneLabel = this.context.getResources().getString(R.string.place_spinner_label_zone);
    }


    public void loadDataForSpinner(){

        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            if (i == 0) {
                mData.add(this.mSpinnerHeader);
            } else{
                mData.add(this.mSpinnerZoneLabel+" "+ i);
            }
        }

        this.context.setDataInSpinner(mData);

    }
}
