package com.example.busquery;

import java.util.List;

public class city {

    private String return_code;

    private String error_code;

    private List<returl_list> returl_lists;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<returl_list> getReturl_lists() {
        return returl_lists;
    }

    public void setReturl_lists(List<returl_list> returl_lists) {
        this.returl_lists = returl_lists;
    }
}
