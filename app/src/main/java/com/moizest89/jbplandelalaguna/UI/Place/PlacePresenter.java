package com.moizest89.jbplandelalaguna.UI.Place;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/11/16.
 */
public class PlacePresenter {

    private PlaceActivity context;

    public PlacePresenter(PlaceActivity context) {
        this.context = context;
    }


    public void loadDataForSpinner(){

        List<String> mData = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            mData.add("Zona "+i);
        }

        this.context.setDataInSpinner(mData);

    }
}
