package com.moizest89.jbplandelalaguna.UI.Place.Main;


import com.moizest89.jbplandelalaguna.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/11/16.
 */
public class PlacePresenter {

    private PlaceActivity context;
    private String mSpinnerHeader;
    private String mSpinnerZoneLabel;

    public PlacePresenter(PlaceActivity context) {
        this.context = context;
        this.mSpinnerHeader = this.context.getResources().getString(R.string.place_spinner_label_select_item);
        this.mSpinnerZoneLabel = this.context.getResources().getString(R.string.place_spinner_label_zone);
    }


    public void loadDataForSpinner(){

        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if (i == 0) {
                mData.add(this.mSpinnerHeader);
            } else{
                mData.add(this.mSpinnerZoneLabel+" "+ i);
            }
        }

        this.context.setDataInSpinner(mData);

    }
}
