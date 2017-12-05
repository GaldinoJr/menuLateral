package com.galdino.examplemenulateral.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by galdino on 05/12/17.
 */

public class HomeSideMenuItem implements Parcelable {
    public static final int FIRST_SCREEN_ID = 1001;
    public static final int SECOND_SCREEN_ID = 1002;
    private int stringResourceTitle;
    private int drawableResourceId;
    private String count = "0";
    // boolean to set visiblity of the counter
    private boolean isCounterVisible = false;
    private int id;

    public HomeSideMenuItem(int id, int title, int drawableResourceId){
        this.stringResourceTitle = title;
        this.id = id;
        this.drawableResourceId = drawableResourceId;
    }

//    public HomeSideMenuItem(String title, int icon, boolean isCounterVisible, String count){
//        this.title = title;
//        this.icon = icon;
//        this.isCounterVisible = isCounterVisible;
//        this.count = count;
//    }

    public int getTitleResourceId(){
        return this.stringResourceTitle;
    }

    public int getIcon(){
        return this.drawableResourceId;
    }

    public String getCount(){
        return this.count;
    }

    public boolean getCounterVisibility(){
        return this.isCounterVisible;
    }

    public void setCount(String count){
        this.count = count;
    }

    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stringResourceTitle);
        dest.writeInt(this.drawableResourceId);
        dest.writeString(this.count);
        dest.writeByte(this.isCounterVisible ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
    }

    protected HomeSideMenuItem(Parcel in) {
        this.stringResourceTitle = in.readInt();
        this.drawableResourceId = in.readInt();
        this.count = in.readString();
        this.isCounterVisible = in.readByte() != 0;
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<HomeSideMenuItem> CREATOR = new Parcelable.Creator<HomeSideMenuItem>() {
        @Override
        public HomeSideMenuItem createFromParcel(Parcel source) {
            return new HomeSideMenuItem(source);
        }

        @Override
        public HomeSideMenuItem[] newArray(int size) {
            return new HomeSideMenuItem[size];
        }
    };
}
