package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ui.Urls;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 姜佳妮 on 2016/11/28.
 */
public class SearchMaterialActivity extends Activity {
    private ImageView btn_back;        //返回按钮
    private ImageView ib_search;    //搜索按钮
    private String search;          //显示选中的食材

    private GridView gridView_meat;
    private GridView gridView_vegetable;

    private ArrayList list_meat;
    private ArrayList list_vegetable;

    private MyAdapter_meat adapter_meat;
    private MyAdapter_vegetable adapter_vegetable;

    //用于记录checkbox选中的状态；
    public static HashMap map;
    private EditText textview;

    //把选中的checkbox添加到集合中；
    private ArrayList selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_material);

        getView();
        setListener();

        selectedItem = new ArrayList();

        initData();
        initCheckBox();

        adapter_meat = new MyAdapter_meat();
        adapter_vegetable = new MyAdapter_vegetable();

        gridView_meat.setAdapter(adapter_meat);
        gridView_vegetable.setAdapter(adapter_vegetable);

        gridView_meat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "";
                ViewHolder holder = (ViewHolder) view.getTag();
                //toggle();设置开关。选中则再次点击为不选中。
                holder.cb.toggle();
                //在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
                getIsSelected().put(position, holder.cb.isChecked());
                if (holder.cb.isChecked() == true) {
                    //添加到集合中；
                    selectedItem.add(list_meat.get(position) + "、");
                } else {
                    //从集合中移除元素。
                    selectedItem.remove(list_meat.get(position) + "、");
                }
                //最后遍历集合显示在textview;
                for (int i = 0; i < selectedItem.size(); i++) {
                    s = s + selectedItem.get(i);
                }
                textview.setText(s);
            }
        });

        gridView_vegetable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = "";
                ViewHolder holder = (ViewHolder) view.getTag();
                //toggle();设置开关。选中则再次点击为不选中。
                holder.cb.toggle();
                //在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
                getIsSelected().put(position, holder.cb.isChecked());
                if (holder.cb.isChecked() == true) {
                    //添加到集合中；
                    selectedItem.add(list_vegetable.get(position) + "、");
                } else {
                    //从集合中移除元素。
                    selectedItem.remove(list_vegetable.get(position) + "、");
                }
                //最后遍历集合显示在textview;
                for (int i = 0; i < selectedItem.size(); i++) {
                    s = s + selectedItem.get(i);
                }
                textview.setText(s);
            }
        });
    }

    /**
     * 获取控件
     */
    private void getView(){
        gridView_meat = (GridView)findViewById(R.id.gridview_meat);
        gridView_vegetable = (GridView)findViewById(R.id.gridview_vegetable);

        textview = (EditText) findViewById(R.id.Et_material_search);
        btn_back = (ImageView)findViewById(R.id.Btn_material_back);
        ib_search = (ImageView) findViewById(R.id.Ib_searchmatrial_search);
    }

    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        btn_back.setOnClickListener(listener);
        ib_search.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Btn_material_back:
                    finish();
                    break;

                case R.id.Ib_searchmatrial_search:
                    search = textview.getText().toString();
                    //显示搜索结果
                    if(search != null){
                        Intent intent = new Intent(SearchMaterialActivity.this,SearchListActivity.class);
                        intent.putExtra("title",search);
                        intent.putExtra("Url", Urls.urlSearchFood);
                        intent.putExtra("flag","3");
                        startActivity(intent);
                    }else{
                        Toast.makeText(SearchMaterialActivity.this,"请选择食材",Toast.LENGTH_SHORT).show();
                    }
                    break;

                default:
                    break;
            }
        }
    }


    private void initCheckBox() {
        map = new HashMap();
        for (int i = 0; i < list_meat.size()+list_vegetable.size(); i++) {
            map.put(i, false);
        }
    }

    private void initData(){
        list_meat = new ArrayList();
        list_meat.add("猪肉 ");
        list_meat.add("牛肉 ");
        list_meat.add("羊肉 ");
        list_meat.add("鸡肉 ");
        list_meat.add("鸭肉 ");
        list_meat.add("鱼肉 ");
        list_meat.add("虾 ");
        list_meat.add("贝类 ");
        list_meat.add("蟹 ");

        list_vegetable = new ArrayList();
        list_vegetable.add("白菜");
        list_vegetable.add("百合");
        list_vegetable.add("包心菜 ");
        list_vegetable.add("北瓜 ");
        list_vegetable.add("冬瓜 ");
        list_vegetable.add("豆角 ");
        list_vegetable.add("海带 ");
        list_vegetable.add("黑豆 ");
        list_vegetable.add("红薯 ");
        list_vegetable.add("胡萝卜 ");
        list_vegetable.add("黄豆 ");
        list_vegetable.add("黄瓜 ");
        list_vegetable.add("茴香 ");
        list_vegetable.add("豇豆 ");
        list_vegetable.add("金针菇 ");
        list_vegetable.add("韭菜 ");
        list_vegetable.add("空心菜 ");
        list_vegetable.add("辣椒 ");
        list_vegetable.add("莲子 ");
        list_vegetable.add("凉瓜 ");
        list_vegetable.add("木耳 ");
        list_vegetable.add("南瓜 ");
        list_vegetable.add("蒲菜 ");
        list_vegetable.add("荠菜 ");
        list_vegetable.add("青椒 ");
        list_vegetable.add("秋葵 ");
        list_vegetable.add("丝瓜 ");
        list_vegetable.add("甜椒 ");
        list_vegetable.add("茼蒿 ");
        list_vegetable.add("土豆 ");
        list_vegetable.add("豌豆 ");
        list_vegetable.add("西红柿 ");
        list_vegetable.add("西兰花 ");
        list_vegetable.add("香菇 ");
        list_vegetable.add("小米椒 ");
        list_vegetable.add("杏鲍菇 ");
        list_vegetable.add("薏米 ");
        list_vegetable.add("银耳 ");
        list_vegetable.add("油菜 ");
        list_vegetable.add("紫菜 ");

    }

    class MyAdapter_meat extends BaseAdapter {

        @Override
        public int getCount() {
            return list_meat.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;

            if (convertView == null) {
                vh = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.gridview_item_material, null);
                vh.cb = (CheckBox) convertView.findViewById(R.id.checkbox);
                vh.tv = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv.setText(list_meat.get(position) + "");
            //得到当前map中的选中的状态；默认为没有选中；
            vh.cb.setChecked(getIsSelected().get(position));
            return convertView;
        }
    }

    class MyAdapter_vegetable extends BaseAdapter {

        @Override
        public int getCount() {
            return list_vegetable.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;

            if (convertView == null) {
                vh = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.gridview_item_material, null);
                vh.cb = (CheckBox) convertView.findViewById(R.id.checkbox);
                vh.tv = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv.setText(list_vegetable.get(position) + "");
            //得到当前map中的选中的状态；默认为没有选中；
            vh.cb.setChecked(getIsSelected().get(position));
            return convertView;
        }
    }



    static final class ViewHolder {
        CheckBox cb;
        TextView tv;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return map;
    }
}
