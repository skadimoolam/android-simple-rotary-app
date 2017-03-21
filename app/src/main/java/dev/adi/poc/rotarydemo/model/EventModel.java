package dev.adi.poc.rotarydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afollestad.ason.AsonName;

public class EventModel implements Parcelable {

    public @AsonName(name = "name") String eveName;
    public @AsonName(name = "date") String eveDate;
    public @AsonName(name = "category") String eveCategory;
    public @AsonName(name = "description") String eveDescription;
    public @AsonName(name = "id") String eveId;
    public @AsonName(name = "photo") String evePhotoUrl;

    public EventModel() {}

    public EventModel(String eveId, String eveName, String eveCategory, String eveDate, String eveDescription, String evePhotoUrl) {
        this.eveId = eveId;
        this.eveName = eveName;
        this.eveDate = eveDate;
        this.eveDescription = eveDescription;
        this.eveCategory = eveCategory;
        this.evePhotoUrl = evePhotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eveName);
        dest.writeString(this.eveDate);
        dest.writeString(this.eveCategory);
        dest.writeString(this.eveDescription);
        dest.writeString(this.eveId);
        dest.writeString(this.evePhotoUrl);
    }

    protected EventModel(Parcel in) {
        this.eveName = in.readString();
        this.eveDate = in.readString();
        this.eveCategory = in.readString();
        this.eveDescription = in.readString();
        this.eveId = in.readString();
        this.evePhotoUrl = in.readString();
    }

    public static final Parcelable.Creator<EventModel> CREATOR = new Parcelable.Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel source) {
            return new EventModel(source);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };
}
