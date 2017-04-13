package com.regongzaixian.jiankong.model;

import java.io.Serializable;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:36
 * Description:
 */
public class InstrumentEntity implements Serializable {


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
    private boolean isSelect;

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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
