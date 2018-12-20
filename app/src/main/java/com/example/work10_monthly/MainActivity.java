package com.example.work10_monthly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.work10_monthly.bean.LeftBean;
import com.example.work10_monthly.bean.RightBean;
import com.example.work10_monthly.iprensenter.IPrensentImp;
import com.example.work10_monthly.iview.IView;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IView {
    //商品
    String path = "http://www.zhaoapi.cn/product/getCatagory";
    //子商品
    String path1 = "http://www.zhaoapi.cn/product/getProductCatagory";
    private RecyclerView mRecyclerLeft;
    private RecyclerView mRecyclerRight;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    IPrensentImp mIPrensentImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLeft();
        initRight();
    }

    private void initRight() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerRight.setLayoutManager(manager);
        mRightAdapter=new RightAdapter(this);
        mRecyclerRight.setAdapter(mRightAdapter);
        initRightData();
    }
        int CID;
    private void initRightData() {
        HashMap <String, String> map = new HashMap <>();
        map.put("cid",CID+"");
        mIPrensentImp.requestData(path1,RightBean.class,map);
    }

    private void initLeft() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerLeft.setLayoutManager(manager);
        mLeftAdapter=new LeftAdapter(this);
        mRecyclerLeft.setAdapter(mLeftAdapter);
        initLeftData();

    }

    private void initLeftData() {
        HashMap <String, String> leftmap = new HashMap <>();
        leftmap.put("cid",1+"");
        mIPrensentImp.requestData(path,LeftBean.class,leftmap);
    }

    /**
     * 获取资源ID
     */
    private void initView() {
        mRecyclerLeft = (RecyclerView) findViewById(R.id.Recycler_left);
        mRecyclerRight = (RecyclerView) findViewById(R.id.Recycler_Right);
        mIPrensentImp=new IPrensentImp(this);
    }

    @Override
    public void setData(Object o) {
            if (o instanceof LeftBean){
                final LeftBean leftBean=(LeftBean)o;
                mLeftAdapter.setMjihe(leftBean.getData());
                mLeftAdapter.setLeftCallBack(new LeftAdapter.LeftCallBack() {
                    @Override
                    public void leftback(int i) {
                        int cid = leftBean.getData().get(i).getCid();
                        CID=cid;
                        Toast.makeText(MainActivity.this,"CID="+CID,Toast.LENGTH_LONG).show();
                            initRightData();
                    }
                });
            }else if (o instanceof RightBean){
                RightBean rightBean=(RightBean)o;
                List <RightBean.DataBean> data = rightBean.getData();
                for (int i = 0; i <data.size() ; i++) {
                    Log.i("TAG","i="+i);
                           mRightAdapter.setMjihe( data.get(i).getList());
                }

            }
    }
}
