package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.administrator.adapter.AdapterMaterial;
import com.example.administrator.domain.DataMaterial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姜佳妮 on 2016/11/28.
 */
public class SearchMaterialActivity extends Activity {
    private ArrayAdapter<String> adapter;
    private List<DataMaterial> list_meat = new ArrayList<DataMaterial>();
    private List<DataMaterial> list_vegetable = new ArrayList<DataMaterial>();
    private List<DataMaterial> list_flavour = new ArrayList<DataMaterial>();
    private AdapterMaterial adapter_material_meat;
    private AdapterMaterial adapter_material_vegetable;
    private AdapterMaterial adapter_material_flavour;
    private GridView Gv_meat,Gv_vegetable,Gv_flavour;

    private Button btn_back,btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_material);

        //设置每一类食材的布局
        setAdapterView();
        //获取视图
        findview();
        //设置监听
        setListener();
        //实现搜索功能

    }

    private void findview() {
        btn_back = (Button)findViewById(R.id.Btn_material_back);
        btn_search = (Button)findViewById(R.id.Btn_material_search);
    }

    private void setListener() {
        /*
        点击返回按钮，跳转回首页
         */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(SearchMaterialActivity.this,MainActivity.class);
                startActivity(intent_back);
            }
        });
    }
    /*
    设置食材选项布局
     */
    private void setAdapterView() {
        /*获取食材名称*/
        getMeatData();      //肉类
        getVegetableData(); //蔬菜类
        getFlavourData();   //调料品类

        adapter_material_meat = new AdapterMaterial(this,list_meat);
        adapter_material_vegetable = new AdapterMaterial(this,list_vegetable);
        adapter_material_flavour = new AdapterMaterial(this,list_flavour);
        /*
        获取肉类、蔬菜类、调料类视图
         */
        Gv_meat = (GridView)findViewById(R.id.Gv_material_meat);
        Gv_vegetable = (GridView)findViewById(R.id.Gv_material_vegetable);
        Gv_flavour = (GridView)findViewById(R.id.Gv_material_flavour);
        /*
        分别设置adapter
         */
        Gv_meat.setAdapter(adapter_material_meat);
        Gv_vegetable.setAdapter(adapter_material_vegetable);
        Gv_flavour.setAdapter(adapter_material_flavour);
    }

    public void getMeatData() {
        list_meat.add(new DataMaterial(01L,"鸡肉"));
        list_meat.add(new DataMaterial(02L,"鸭肉"));
        list_meat.add(new DataMaterial(03L,"猪肉"));
        list_meat.add(new DataMaterial(04L,"牛肉"));
        list_meat.add(new DataMaterial(05L,"羊肉"));
        list_meat.add(new DataMaterial(06L,"鱼"));
        list_meat.add(new DataMaterial(07L,"香肠"));
    }

    public void getVegetableData() {
        list_meat.add(new DataMaterial(01L,"白菜"));
        list_meat.add(new DataMaterial(02L,"葱"));
        list_meat.add(new DataMaterial(03L,"姜"));
        list_meat.add(new DataMaterial(04L,"蒜"));
        list_meat.add(new DataMaterial(05L,"豆腐"));
        list_meat.add(new DataMaterial(06L,"香菜"));
        list_meat.add(new DataMaterial(07L,"萝卜"));
    }

    public void getFlavourData() {
        list_meat.add(new DataMaterial(01L,"酱油"));
        list_meat.add(new DataMaterial(02L,"醋"));
        list_meat.add(new DataMaterial(03L,"糖"));
        list_meat.add(new DataMaterial(04L,"盐"));
        list_meat.add(new DataMaterial(05L,"花椒"));
        list_meat.add(new DataMaterial(06L,"胡椒"));
        list_meat.add(new DataMaterial(07L,"小苏打"));
    }
}
