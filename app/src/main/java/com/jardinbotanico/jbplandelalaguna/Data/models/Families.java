package com.jardinbotanico.jbplandelalaguna.Data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public class Families {
    @SerializedName("families")
    @Expose
    private List<Family> families = new ArrayList<Family>();

    /**
     *
     * @return
     * The families
     */
    public List<Family> getFamilies() {
        return families;
    }

    /**
     *
     * @param families
     * The families
     */
    public void setFamilies(List<Family> families) {
        this.families = families;
    }

}
