package com.moizest89.jbplandelalaguna.Data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
public class DummyData implements Parcelable {

    private int id = 0;
    private String name = "";
    private String image = "";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    public DummyData() {
    }

    protected DummyData(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Creator<DummyData> CREATOR = new Creator<DummyData>() {
        @Override
        public DummyData createFromParcel(Parcel source) {
            return new DummyData(source);
        }

        @Override
        public DummyData[] newArray(int size) {
            return new DummyData[size];
        }
    };
}
