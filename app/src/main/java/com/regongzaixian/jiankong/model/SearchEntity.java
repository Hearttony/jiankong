package com.regongzaixian.jiankong.model;

import java.io.Serializable;

/**
 * Created by tony on 2017/4/10.
 */

public class SearchEntity implements Serializable {

    private String device_contact;
    private String device_name;
    private String device_model;
    private String device_serialnumber;

    public CreatedAtBean getCreatedAt() {
        if (createdAt == null) {
            return new CreatedAtBean();
        }
        return createdAt;
    }

    public void setCreatedAt(CreatedAtBean createdAt) {
        this.createdAt = createdAt;
    }

    private CreatedAtBean createdAt;

    private String meantemperature;
    private String meanhumidity;

    public String getDevice_contact() {
        return device_contact;
    }

    public void setDevice_contact(String device_contact) {
        this.device_contact = device_contact;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String instrument) {
        this.device_name = instrument;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_serialnumber() {
        return device_serialnumber;
    }

    public void setDevice_serialnumber(String device_serialnumber) {
        this.device_serialnumber = device_serialnumber;
    }


    public String getMeantemperature() {
        return meantemperature;
    }

    public void setMeantemperature(String meantemperature) {
        this.meantemperature = meantemperature;
    }

    public String getMeanhumidity() {
        return meanhumidity;
    }

    public void setMeanhumidity(String meanhumidity) {
        this.meanhumidity = meanhumidity;
    }

    public static class CreatedAtBean implements Serializable {
        private String startTime;
        private String endTime;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
