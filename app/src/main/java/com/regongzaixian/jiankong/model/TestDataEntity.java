package com.regongzaixian.jiankong.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tony on 2017/4/8.
 */

public class TestDataEntity implements Serializable {

    private List<String> headers;
    private List<List<String>> testdata;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getTestdata() {
        return testdata;
    }

    public void setTestdata(List<List<String>> testdata) {
        this.testdata = testdata;
    }
}
