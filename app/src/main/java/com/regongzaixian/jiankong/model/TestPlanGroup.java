package com.regongzaixian.jiankong.model;

import java.io.Serializable;
import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:36
 * Description:
 */
public class TestPlanGroup implements Serializable {


    /**
     * createdAt : 2017-4-2
     * Ids : [1]
     * startTime : ["2017-04-01T16:00:00.000Z"]
     * devices : [{"name":"模拟测试设备1","contact":"模拟联系人1","address":"北京市海淀区上庄路135号","model":"SIM-TEST1","serialnumber":"55648812347789215","manufacture":"模拟制造商1","remark":"备注信息1","lang":"zh_CN","template":"default","id_ref":null,"id":2,"createdAt":"2017-04-02T03:00:36.000Z","updatedAt":"2017-04-02T12:14:08.000Z","createdBy":1,"updatedBy":1}]
     * instruments : [{"name":"测试测试设备","model":"SIM-INSTRUMENT","serialnumber":"51657921165874","certnumber":"55277","techInfo":"测试技术信息","validdate":"2018-05-14T16:00:00.000Z","manufacture":"测试研究所","remark":"测试备注","lang":"zh_CN","template":"default","id_ref":null,"id":1,"createdAt":"2017-04-02T03:24:25.000Z","updatedAt":"2017-04-02T03:26:21.000Z","createdBy":1,"updatedBy":1,"owner":1}]
     * status : ["notstart"]
     */

    private String createdAt;
    private List<Integer> Ids;
    private List<String> startTime;
    private List<Device> devices;
    private List<Instrument> instruments;
    private List<String> status;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getIds() {
        return Ids;
    }

    public void setIds(List<Integer> Ids) {
        this.Ids = Ids;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public void setStartTime(List<String> startTime) {
        this.startTime = startTime;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public static class Device implements Serializable {
        /**
         * name : 模拟测试设备1
         * contact : 模拟联系人1
         * address : 北京市海淀区上庄路135号
         * model : SIM-TEST1
         * serialnumber : 55648812347789215
         * manufacture : 模拟制造商1
         * remark : 备注信息1
         * lang : zh_CN
         * template : default
         * id_ref : null
         * id : 2
         * createdAt : 2017-04-02T03:00:36.000Z
         * updatedAt : 2017-04-02T12:14:08.000Z
         * createdBy : 1
         * updatedBy : 1
         */

        private String name;
        private String contact;
        private String address;
        private String model;
        private String serialnumber;
        private String manufacture;
        private String remark;
        private String lang;
        private String template;
        private Object id_ref;
        private int id;
        private String createdAt;
        private String updatedAt;
        private int createdBy;
        private int updatedBy;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSerialnumber() {
            return serialnumber;
        }

        public void setSerialnumber(String serialnumber) {
            this.serialnumber = serialnumber;
        }

        public String getManufacture() {
            return manufacture;
        }

        public void setManufacture(String manufacture) {
            this.manufacture = manufacture;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public Object getId_ref() {
            return id_ref;
        }

        public void setId_ref(Object id_ref) {
            this.id_ref = id_ref;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }

        public int getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
        }
    }

    public static class Instrument implements Serializable {
        /**
         * name : 测试测试设备
         * model : SIM-INSTRUMENT
         * serialnumber : 51657921165874
         * certnumber : 55277
         * techInfo : 测试技术信息
         * validdate : 2018-05-14T16:00:00.000Z
         * manufacture : 测试研究所
         * remark : 测试备注
         * lang : zh_CN
         * template : default
         * id_ref : null
         * id : 1
         * createdAt : 2017-04-02T03:24:25.000Z
         * updatedAt : 2017-04-02T03:26:21.000Z
         * createdBy : 1
         * updatedBy : 1
         * owner : 1
         */

        private String name;
        private String model;
        private String serialnumber;
        private String certnumber;
        private String techInfo;
        private String validdate;
        private String manufacture;
        private String remark;
        private String lang;
        private String template;
        private Object id_ref;
        private int id;
        private String createdAt;
        private String updatedAt;
        private int createdBy;
        private int updatedBy;
        private int owner;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSerialnumber() {
            return serialnumber;
        }

        public void setSerialnumber(String serialnumber) {
            this.serialnumber = serialnumber;
        }

        public String getCertnumber() {
            return certnumber;
        }

        public void setCertnumber(String certnumber) {
            this.certnumber = certnumber;
        }

        public String getTechInfo() {
            return techInfo;
        }

        public void setTechInfo(String techInfo) {
            this.techInfo = techInfo;
        }

        public String getValiddate() {
            return validdate;
        }

        public void setValiddate(String validdate) {
            this.validdate = validdate;
        }

        public String getManufacture() {
            return manufacture;
        }

        public void setManufacture(String manufacture) {
            this.manufacture = manufacture;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public Object getId_ref() {
            return id_ref;
        }

        public void setId_ref(Object id_ref) {
            this.id_ref = id_ref;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }

        public int getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }
    }
}
