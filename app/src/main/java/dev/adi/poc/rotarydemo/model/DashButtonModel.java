package dev.adi.poc.rotarydemo.model;

public class DashButtonModel {

    public int btnImg;
    public String btnTitle;
    public Object activityName;

    public DashButtonModel(int btnImg, String btnTitle, Object activityName) {
        this.btnImg = btnImg;
        this.btnTitle = btnTitle;
        this.activityName = activityName;
    }

    public DashButtonModel(int btnImg, Object activityName) {
        this.btnImg = btnImg;
        this.activityName = activityName;
    }

    public DashButtonModel(int btnImg) {
        this.btnImg = btnImg;
    }
}
