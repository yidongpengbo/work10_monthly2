package com.example.work10_monthly.imodel;

import com.example.work10_monthly.until.MyCallBack;

import java.util.Map;

public interface IModel {
    void startRequest(String path, Class clazz, Map<String,String> map, MyCallBack myCallBack);
}
