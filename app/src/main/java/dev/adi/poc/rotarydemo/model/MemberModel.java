package dev.adi.poc.rotarydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afollestad.ason.AsonName;

public class MemberModel implements Parcelable {

    public @AsonName(name = "id") String memId;
    public @AsonName(name = "name") String memName;
    public @AsonName(name = "phone") String memPhone;
    public @AsonName(name = "email") String memEmail;
    public @AsonName(name = "dob") String memDob;
    public @AsonName(name = "designation") String memDesignation;
    public @AsonName(name = "photo") String memPhoto;

    public MemberModel() {}

    public MemberModel(String memId, String memName, String memContact, String memEmail, String memDob, String memDesignation, String memPhoto) {
        this.memId = memId;
        this.memName = memName;
        this.memPhone = memContact;
        this.memEmail = memEmail;
        this.memDob = memDob;
        this.memDesignation = memDesignation;
        this.memPhoto = memPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.memId);
        dest.writeString(this.memName);
        dest.writeString(this.memPhone);
        dest.writeString(this.memEmail);
        dest.writeString(this.memDob);
        dest.writeString(this.memDesignation);
        dest.writeString(this.memPhoto);
    }

    protected MemberModel(Parcel in) {
        this.memId = in.readString();
        this.memName = in.readString();
        this.memPhone = in.readString();
        this.memEmail = in.readString();
        this.memDob = in.readString();
        this.memDesignation = in.readString();
        this.memPhoto = in.readString();
    }

    public static final Parcelable.Creator<MemberModel> CREATOR = new Parcelable.Creator<MemberModel>() {
        @Override
        public MemberModel createFromParcel(Parcel source) {
            return new MemberModel(source);
        }

        @Override
        public MemberModel[] newArray(int size) {
            return new MemberModel[size];
        }
    };
}
