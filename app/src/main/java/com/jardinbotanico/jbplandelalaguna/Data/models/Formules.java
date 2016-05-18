package com.jardinbotanico.jbplandelalaguna.Data.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class Formules {

    @SerializedName("formules")
    @Expose
    private List<Formule> formules = new ArrayList<Formule>();

    /**
     *
     * @return
     * The formules
     */
    public List<Formule> getFormules() {
        return formules;
    }

    /**
     *
     * @param formules
     * The formules
     */
    public void setFormules(List<Formule> formules) {
        this.formules = formules;
    }
}
