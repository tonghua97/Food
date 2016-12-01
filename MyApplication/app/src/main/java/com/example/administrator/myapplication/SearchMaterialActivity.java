package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.administrator.adapter.AdapterMaterial;
import com.example.administrator.domain.DataMaterial;
import com.example.administrator.ui.MaterialGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姜佳妮 on 2016/11/28.
 */
public class SearchMaterialActivity extends Activity {

    private AdapterMaterial a_meat,a_vegetable;       //设置肉类
    private List<DataMaterial> ls_meat = new ArrayList<>();
    private List<DataMaterial> ls_vegetavble = new ArrayList<>();
    private SearchView sv_material;     //搜索框
    private Button btn_back,btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_material);

        //获取食材数据
        getMeat();          //肉类
        getVegetable();     //蔬菜类

        //给Adapter设置数据
        a_meat = new AdapterMaterial(this,ls_meat);
        a_vegetable = new AdapterMaterial(this,ls_vegetavble);

        //获取Materialgridview，绑定Adaoter
        MaterialGridView gv_meat = (MaterialGridView)findViewById(R.id.Gv_material_meat);
        gv_meat.setAdapter(a_meat);
        MaterialGridView gv_vegetable = (MaterialGridView)findViewById(R.id.Gv_material_vegetable);
        gv_vegetable.setAdapter(a_vegetable);

        //获取返回、搜索按钮
        btn_back = (Button)findViewById(R.id.Btn_material_back);
        btn_search = (Button)findViewById(R.id.Btn_material_search);

        //设置搜索框属性
        setSearchBar();

        //设置监听
        setListener();



    }

    private void setSearchBar() {
        sv_material = (SearchView)findViewById(R.id.Sv_material);
        //设置搜索框默认是否自动缩小为图标
        sv_material.setIconifiedByDefault(false);
        //设置搜索框显示搜索按钮
        sv_material.setSubmitButtonEnabled(true);
        //设置搜索框内默认显示的提示文本
        sv_material.setQueryHint("搜索食材");
        //为搜索框设置事件监听器
        sv_material.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //单击搜索框按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            //用户输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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

    public void getMeat() {
        ls_meat.add(new DataMaterial(01l,"猪肉"));
        ls_meat.add(new DataMaterial(02l,"牛肉"));
        ls_meat.add(new DataMaterial(03l,"羊肉"));
        ls_meat.add(new DataMaterial(04l,"鸡肉"));
        ls_meat.add(new DataMaterial(05l,"鸭肉"));
        ls_meat.add(new DataMaterial(06l,"鱼"));
    }

    public void getVegetable() {
        ls_vegetavble.add(new DataMaterial(1l,"白菜"));
        ls_vegetavble.add(new DataMaterial(2l,"萝卜"));
        ls_vegetavble.add(new DataMaterial(3l,"葱"));
        ls_vegetavble.add(new DataMaterial(4l,"姜"));
        ls_vegetavble.add(new DataMaterial(5l,"蒜"));
        ls_vegetavble.add(new DataMaterial(6l,"西红柿"));
        ls_vegetavble.add(new DataMaterial(7l,"白菜"));
        ls_vegetavble.add(new DataMaterial(8l,"萝卜"));
        ls_vegetavble.add(new DataMaterial(9l,"葱"));
        ls_vegetavble.add(new DataMaterial(10l,"姜"));
        ls_vegetavble.add(new DataMaterial(11l,"蒜"));
        ls_vegetavble.add(new DataMaterial(12l,"西红柿"));
        ls_vegetavble.add(new DataMaterial(13l,"白菜"));
        ls_vegetavble.add(new DataMaterial(14l,"萝卜"));
        ls_vegetavble.add(new DataMaterial(15l,"葱"));
        ls_vegetavble.add(new DataMaterial(16l,"姜"));
        ls_vegetavble.add(new DataMaterial(17l,"蒜"));
        ls_vegetavble.add(new DataMaterial(18l,"西红柿"));
    }

}
