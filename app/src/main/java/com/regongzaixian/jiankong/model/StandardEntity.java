package com.regongzaixian.jiankong.model;

import java.io.Serializable;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:36
 * Description:
 */
public class StandardEntity implements Serializable {

    /**
     * name : 模拟测试标准
     * standardcode : SIMSTANDAR1
     * description : 模拟测试依据技术条件
     * lang : zh_CN
     * template : default
     * id_ref : null
     * id : 1
     * createdAt : 2017-04-02T03:38:32.000Z
     * updatedAt : 2017-04-02T03:39:27.000Z
     */

    private String name;
    private String standardcode;
    private String description;
    private String lang;
    private String template;
    private String id_ref;
    private int id;
    private String createdAt;
    private String updatedAt;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandardcode() {
        return standardcode;
    }

    public void setStandardcode(String standardcode) {
        this.standardcode = standardcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getId_ref() {
        return id_ref;
    }

    public void setId_ref(String id_ref) {
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
}
