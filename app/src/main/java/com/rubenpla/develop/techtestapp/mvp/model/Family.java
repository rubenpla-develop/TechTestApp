package com.rubenpla.develop.techtestapp.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Family implements Parcelable {

    @SerializedName("family")
    private List<FamilyMember> familyList;

    public Family(List<FamilyMember> list){
        this.familyList = list;
    }

    public List<FamilyMember> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<FamilyMember> familyList) {
        this.familyList = familyList;
    }

    /*
     *  PARCELABLE IMPL
     */

    protected Family(Parcel in) {
    }

    public static final Creator<Family> CREATOR = new Creator<Family>() {
        @Override
        public Family createFromParcel(Parcel in) {
            return new Family(in);
        }

        @Override
        public Family[] newArray(int size) {
            return new Family[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    /*
     * END OF PARCELABLE IMPL
     */
}
