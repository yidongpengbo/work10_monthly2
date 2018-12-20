package com.example.work10_monthly.imodel;

import com.example.work10_monthly.bean.LeftBean;
import com.example.work10_monthly.bean.RightBean;
import com.example.work10_monthly.until.MyCallBack;
import com.example.work10_monthly.until.OKHttpUntil;

import java.util.Map;

public class IModelImp implements IModel {
    MyCallBack mMyCallBack;
    @Override
    public void startRequest(String path, Class clazz, Map<String,String>map,MyCallBack myCallBack) {
            mMyCallBack=myCallBack;
        OKHttpUntil.getInstance().postEnque(path, clazz, map, new OKHttpUntil.CallBack() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void success(Object o) {
                if (o instanceof LeftBean){
                    LeftBean leftBean=(LeftBean)o;
                    mMyCallBack.setData(leftBean);
                }else if (o instanceof RightBean){
                    RightBean rightBean=(RightBean)o;
                    mMyCallBack.setData(rightBean);
                }
            }
        });
    }
}
