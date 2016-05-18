package com.jardinbotanico.jbplandelalaguna.Data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/30/16.
 */
public class Tips implements Parcelable {

    @SerializedName("tips")
    @Expose
    private List<Tip> tips = new ArrayList<Tip>();

    /**
     *
     * @return
     * The tips
     */
    public List<Tip> getTips() {
        return tips;
    }

    /**
     *
     * @param tips
     * The tips
     */
    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(tips);
    }

    public Tips() {
    }

    protected Tips(Parcel in) {
        this.tips = in.createTypedArrayList(Tip.CREATOR);
    }

    public static final Parcelable.Creator<Tips> CREATOR = new Parcelable.Creator<Tips>() {
        @Override
        public Tips createFromParcel(Parcel source) {
            return new Tips(source);
        }

        @Override
        public Tips[] newArray(int size) {
            return new Tips[size];
        }
    };
}
