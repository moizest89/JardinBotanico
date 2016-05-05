package com.moizest89.jbplandelalaguna.UI.Tips.List;

import com.moizest89.jbplandelalaguna.Data.models.Tip;
import com.moizest89.jbplandelalaguna.Data.models.Tips;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;

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
