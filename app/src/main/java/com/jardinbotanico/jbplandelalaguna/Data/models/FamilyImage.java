package com.jardinbotanico.jbplandelalaguna.Data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public class FamilyImage implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.url);
    }

    public FamilyImage() {
    }

    protected FamilyImage(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
    }

    public static final Parcelable.Creator<FamilyImage> CREATOR = new Parcelable.Creator<FamilyImage>() {
        @Override
        public FamilyImage createFromParcel(Parcel source) {
            return new FamilyImage(source);
        }

        @Override
        public FamilyImage[] newArray(int size) {
            return new FamilyImage[size];
        }
    };
}
