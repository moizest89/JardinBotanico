package com.moizest89.jbplandelalaguna.UI.Tips.Main;

import com.moizest89.jbplandelalaguna.Data.models.Categories;
import com.moizest89.jbplandelalaguna.Data.models.Category;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;

import java.util.List;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public interface ITipsView {

    void showData();
    void hideData();
    void setData(Categories categories);
    void showLoading();
    void hideLoading();
    void goToTipList(Category categories);

    void setMessageDataError();
    void setMessageDataEmpty();

}
