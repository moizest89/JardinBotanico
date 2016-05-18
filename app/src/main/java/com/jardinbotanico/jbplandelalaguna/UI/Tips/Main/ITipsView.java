package com.jardinbotanico.jbplandelalaguna.UI.Tips.Main;

import com.jardinbotanico.jbplandelalaguna.Data.models.Categories;
import com.jardinbotanico.jbplandelalaguna.Data.models.Category;

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
