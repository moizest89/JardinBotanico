package com.moizest89.jbplandelalaguna.UI.Vivarium.Main;

import com.moizest89.jbplandelalaguna.Data.models.DummyData;

import java.util.List;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
public interface IVivariumActivity {

    void showData();
    void hideData();
    void showLoader();
    void hideLoader();
    void setData(List<DummyData> data);
}
