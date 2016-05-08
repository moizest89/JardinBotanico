package com.moizest89.jbplandelalaguna.UI.Zone.Details;

import java.util.List;

/**
 * Created by @moizest89 in SV on 5/5/16.
 */
public interface IZoneView {

    void showLoader();
    void hideLoader();
    void showData();
    void hideData();
    void setData(List<String> data);

}
