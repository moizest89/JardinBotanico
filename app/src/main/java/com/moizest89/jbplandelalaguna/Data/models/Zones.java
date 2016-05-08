package com.moizest89.jbplandelalaguna.Data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public class Zones implements Parcelable {

    @SerializedName("zones")
    @Expose
    private List<Zone> zones = new ArrayList<Zone>();

    /**
     *
     * @return
     * The zones
     */
    public List<Zone> getZones() {
        return zones;
    }

    /**
     *
     * @param zones
     * The zones
     */
    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(zones);
    }

    public Zones() {
    }

    protected Zones(Parcel in) {
        this.zones = in.createTypedArrayList(Zone.CREATOR);
    }

    public static final Parcelable.Creator<Zones> CREATOR = new Parcelable.Creator<Zones>() {
        @Override
        public Zones createFromParcel(Parcel source) {
            return new Zones(source);
        }

        @Override
        public Zones[] newArray(int size) {
            return new Zones[size];
        }
    };
}
