package com.example.work10_monthly.iprensenter;

import com.example.work10_monthly.imodel.IModelImp;
import com.example.work10_monthly.iview.IView;
import com.example.work10_monthly.until.MyCallBack;

import java.util.Map;

public class IPrensentImp implements IPresenter {
    IView mIView;
    IModelImp mIModelImp;

    public IPrensentImp(IView IView) {
        mIView = IView;
        mIModelImp=new IModelImp();
    }

    @Override
    public void requestData(String path, Class clazz, Map<String,String>map) {
        mIModelImp.startRequest(path, clazz, map, new MyCallBack() {
            @Override
            public void setData(Object o) {
                    mIView.setData(o);
            }
        });
    }
}
