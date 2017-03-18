package dev.adi.poc.rotarydemo.model;

import com.afollestad.ason.AsonName;

public class EventModel {

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
}
