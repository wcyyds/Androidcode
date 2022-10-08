package com.example.weather.db;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.Date;

public class Province extends LitePalSupport {
    private int id;
    private String provinceName;
    private  int ProvinceCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        ProvinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceCode() {
        return ProvinceCode;
    }
}
