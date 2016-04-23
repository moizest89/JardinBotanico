package com.moizest89.jbplandelalaguna.UI.Tips.Main;

import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;

import java.util.List;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public interface ITipsView {

    void showData();
    void hideData();
    void setData(List<TipsCategory> tipsCategory);
    void showLoading();
    void hideLoading();

}
