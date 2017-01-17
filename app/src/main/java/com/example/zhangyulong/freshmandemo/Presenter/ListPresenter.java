package com.example.zhangyulong.freshmandemo.Presenter;

import android.content.Context;

import com.example.zhangyulong.freshmandemo.Artical;
import com.example.zhangyulong.freshmandemo.HomeAdapter;
import com.example.zhangyulong.freshmandemo.Model.IList;
import com.example.zhangyulong.freshmandemo.Model.ListModel;
import com.example.zhangyulong.freshmandemo.View.IShowItem;
import com.example.zhangyulong.freshmandemo.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Created by zhangyulong on 16-11-8.
 */

public class ListPresenter implements IGetListData {
    private IShowItem iShowItem;
    private ListModel listModel;
    private static int tmp=0;
    private HomeAdapter homeAdapter;

    public void setHomeAdapter(HomeAdapter homeAdapter) {
        this.homeAdapter = homeAdapter;
    }

    public ListPresenter(IShowItem iShowItem){
        this.iShowItem=iShowItem;
        this.listModel=new ListModel(this);
    }
    public void showList(int type,int pageNum){

        //iShowItem.showItem(status);
        listModel.getListData(type,pageNum);

    }


    @Override
    public void getData(List<Artical.Data> mDatas) {


            homeAdapter.mDatas.addAll(mDatas);

    }
}
