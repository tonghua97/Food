package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 姜佳妮 on 2016/11/28.
 */
public class SearchMaterialActivity extends Activity {
    private Button btn_back,btn_search;

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
        setContentView(R.layout.activity_main);

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
                    System.out.println("选中" + position);
                    //添加到集合中；
                    selectedItem.add(list_meat.get(position));
                } else {
                    System.out.println("取消选中" + position);
                    //从集合中移除元素。
                    selectedItem.remove(list_meat.get(position));
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
                    System.out.println("选中" + position);
                    //添加到集合中；
                    selectedItem.add(list_vegetable.get(position));
                } else {
                    System.out.println("取消选中" + position);
                    //从集合中移除元素。
                    selectedItem.remove(list_vegetable.get(position));
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
        gridView_meat = (GridView)findViewById(R.id.gridview1);
        gridView_vegetable = (GridView)findViewById(R.id.gridview2);

        textview = (EditText) findViewById(R.id.Et_material_search);
        btn_back = (Button)findViewById(R.id.Btn_material_back);
        btn_search = (Button)findViewById(R.id.Btn_material_search);
    }

    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        btn_back.setOnClickListener(listener);
        btn_search.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Btn_material_back:
                    finish();
                    break;

                case R.id.Iv_searchmatrial_search:
                    //显示搜索结果
                    break;

                default:
                    break;
            }
        }
    }


    private void initCheckBox() {
        map = new HashMap();
        for (int i = 0; i < list_meat.size(); i++) {
            map.put(i, false);
        }
    }

    private void initData(){
        list_meat = new ArrayList();
        list_meat.add("猪肉");
        list_meat.add("牛肉");
        list_meat.add("羊肉");
        list_meat.add("鸡肉");
        list_meat.add("鸭肉");
        list_meat.add("鱼肉");

        list_vegetable = new ArrayList();
        list_vegetable.add("白菜");
        list_vegetable.add("菠菜");
        list_vegetable.add("油菜");
        list_vegetable.add("胡萝卜");
        list_vegetable.add("黄瓜");
        list_vegetable.add("西红柿");
        list_vegetable.add("葱");
        list_vegetable.add("蒜");
        list_vegetable.add("姜");
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

//    private AdapterMaterial a_meat,a_vegetable;       //设置肉类
//    private List<DataMaterial> ls_meat = new ArrayList<>();
//    private List<DataMaterial> ls_vegetavble = new ArrayList<>();
//    private SearchView sv_material;     //搜索框
//    private Button btn_back,btn_search;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_material);
//
//        //获取食材数据
//        getMeat();          //肉类
//        getVegetable();     //蔬菜类
//
//        //给Adapter设置数据
//        a_meat = new AdapterMaterial(this,ls_meat);
//        a_vegetable = new AdapterMaterial(this,ls_vegetavble);
//
//        //获取Materialgridview，绑定Adaoter
//        MaterialGridView gv_meat = (MaterialGridView)findViewById(R.id.Gv_material_meat);
//        gv_meat.setAdapter(a_meat);
//        MaterialGridView gv_vegetable = (MaterialGridView)findViewById(R.id.Gv_material_vegetable);
//        gv_vegetable.setAdapter(a_vegetable);
//
//        //获取返回、搜索按钮
//        btn_back = (Button)findViewById(R.id.Btn_material_back);
//        btn_search = (Button)findViewById(R.id.Btn_material_search);
//
//        //设置搜索框属性
//        setSearchBar();
//
//        //设置监听
//        setListener();
//
//
//
//    }
//
//    private void setSearchBar() {
//        sv_material = (SearchView)findViewById(R.id.Sv_material);
//        //设置搜索框默认是否自动缩小为图标
//        sv_material.setIconifiedByDefault(false);
//        //设置搜索框显示搜索按钮
//        sv_material.setSubmitButtonEnabled(true);
//        //设置搜索框内默认显示的提示文本
//        sv_material.setQueryHint("搜索食材");
//        //为搜索框设置事件监听器
//        sv_material.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            //单击搜索框按钮时激发该方法
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            //用户输入字符时激发该方法
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
//
//    private void setListener() {
//        /*
//        点击返回按钮，跳转回首页
//         */
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    public void getMeat() {
//        ls_meat.add(new DataMaterial(01l,"猪肉"));
//        ls_meat.add(new DataMaterial(02l,"牛肉"));
//        ls_meat.add(new DataMaterial(03l,"羊肉"));
//        ls_meat.add(new DataMaterial(04l,"鸡肉"));
//        ls_meat.add(new DataMaterial(05l,"鸭肉"));
//        ls_meat.add(new DataMaterial(06l,"鱼"));
//    }
//
//    public void getVegetable() {
//        ls_vegetavble.add(new DataMaterial(1l,"白菜"));
//        ls_vegetavble.add(new DataMaterial(2l,"萝卜"));
//        ls_vegetavble.add(new DataMaterial(3l,"葱"));
//        ls_vegetavble.add(new DataMaterial(4l,"姜"));
//        ls_vegetavble.add(new DataMaterial(5l,"蒜"));
//        ls_vegetavble.add(new DataMaterial(6l,"西红柿"));
//        ls_vegetavble.add(new DataMaterial(7l,"白菜"));
//        ls_vegetavble.add(new DataMaterial(8l,"萝卜"));
//        ls_vegetavble.add(new DataMaterial(9l,"葱"));
//        ls_vegetavble.add(new DataMaterial(10l,"姜"));
//        ls_vegetavble.add(new DataMaterial(11l,"蒜"));
//        ls_vegetavble.add(new DataMaterial(12l,"西红柿"));
//        ls_vegetavble.add(new DataMaterial(13l,"白菜"));
//        ls_vegetavble.add(new DataMaterial(14l,"萝卜"));
//        ls_vegetavble.add(new DataMaterial(15l,"葱"));
//        ls_vegetavble.add(new DataMaterial(16l,"姜"));
//        ls_vegetavble.add(new DataMaterial(17l,"蒜"));
//        ls_vegetavble.add(new DataMaterial(18l,"西红柿"));
//    }



}
