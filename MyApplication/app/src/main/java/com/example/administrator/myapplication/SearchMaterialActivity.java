package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_material);
        getMeatData();
        getVegetableData();
        getFlavourData();
        adapter_material_meat = new AdapterMaterial(this,list_meat);
        adapter_material_vegetable = new AdapterMaterial(this,list_vegetable);
        adapter_material_flavour = new AdapterMaterial(this,list_flavour);

        Gv_meat = (GridView)findViewById(R.id.Gv_material_meat);
        Gv_vegetable = (GridView)findViewById(R.id.Gv_material_vegetable);
        Gv_flavour = (GridView)findViewById(R.id.Gv_material_flavour);

        Gv_meat.setAdapter(adapter_material_meat);
        Gv_vegetable.setAdapter(adapter_material_vegetable);
        Gv_flavour.setAdapter(adapter_material_flavour);

    }

    public void getMeatData() {
        list_meat.add(new DataMaterial(01L,"鸡肉"));
        list_meat.add(new DataMaterial(02L,"鸡肉"));
        list_meat.add(new DataMaterial(03L,"鸡肉"));
        list_meat.add(new DataMaterial(04L,"鸡肉"));
        list_meat.add(new DataMaterial(05L,"鸡肉"));
        list_meat.add(new DataMaterial(06L,"鸡肉"));
        list_meat.add(new DataMaterial(07L,"鸡肉"));
    }

    public void getVegetableData() {
        list_meat.add(new DataMaterial(01L,"白菜"));
        list_meat.add(new DataMaterial(02L,"白菜"));
        list_meat.add(new DataMaterial(03L,"白菜"));
        list_meat.add(new DataMaterial(04L,"白菜"));
        list_meat.add(new DataMaterial(05L,"白菜"));
        list_meat.add(new DataMaterial(06L,"白菜"));
        list_meat.add(new DataMaterial(07L,"白菜"));
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
