package com.jardinbotanico.jbplandelalaguna.UI.Tips.List;

import com.jardinbotanico.jbplandelalaguna.Data.models.Tip;

import java.util.List;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public interface ITipsListView {

    void showData();
    void hideData();
    void setData(List<Tip> tips);
    void showLoading();
    void hideLoading();

    void setMessageDataError();
    void setMessageDataEmpty();
}
