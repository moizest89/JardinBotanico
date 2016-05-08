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
public class Zone implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("families")
    @Expose
    private List<Family> families = new ArrayList<Family>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     *
     * @param banner
     * The banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.banner);
        dest.writeTypedList(families);
    }

    public Zone() {
    }

    protected Zone(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.banner = in.readString();
        this.families = in.createTypedArrayList(Family.CREATOR);
    }

    public static final Parcelable.Creator<Zone> CREATOR = new Parcelable.Creator<Zone>() {
        @Override
        public Zone createFromParcel(Parcel source) {
            return new Zone(source);
        }

        @Override
        public Zone[] newArray(int size) {
            return new Zone[size];
        }
    };
}
