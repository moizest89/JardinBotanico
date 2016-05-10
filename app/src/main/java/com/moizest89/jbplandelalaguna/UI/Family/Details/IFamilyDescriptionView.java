package com.moizest89.jbplandelalaguna.UI.Family.Details;

import com.moizest89.jbplandelalaguna.Data.models.Family;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public interface IFamilyDescriptionView {

    void showData();
    void showLoader();
    void hideLoader();

    void setMessage(String mMessage);
    void setData(Family family);

}
