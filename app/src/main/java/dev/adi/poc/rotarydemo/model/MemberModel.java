package dev.adi.poc.rotarydemo.model;

import com.afollestad.ason.AsonName;

public class MemberModel {

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
}
