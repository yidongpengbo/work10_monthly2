package com.example.work10_monthly.iprensenter;

import java.util.Map;

public interface IPresenter {
    void requestData(String path,Class clazz,Map<String,String>map);
}
