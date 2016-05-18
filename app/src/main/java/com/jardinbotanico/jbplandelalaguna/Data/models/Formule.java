package com.jardinbotanico.jbplandelalaguna.Data.models;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formule {

    @SerializedName("formule")
    @Expose
    private String formule;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     *
     * @return
     * The formule
     */
    public String getFormule() {
        return formule;
    }

    /**
     *
     * @param formule
     * The formule
     */
    public void setFormule(String formule) {
        this.formule = formule;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
