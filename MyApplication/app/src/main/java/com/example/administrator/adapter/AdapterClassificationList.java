package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataClassificationList;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.Urls;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/24.
 */

public class AdapterClassificationList extends BaseAdapter {
    private Context context;
    private List<DataClassificationList> ldcla = new ArrayList<>();
    private String Url;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    public AdapterClassificationList(List<DataClassificationList> ldc,Context context){
        ldcla = ldc;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldcla.size();
    }

    @Override
    public Object getItem(int position) {
        return ldcla.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldcla.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_classificationlist,null);
        }
        TextView Tv_classification_time = (TextView)view.findViewById(R.id.Tv_classification_time);
        Tv_classification_time.setText(ldcla.get(position).getTime() + "");
        TextView Tv_classification_number = (TextView)view.findViewById(R.id.Tv_classification_collection);
        Tv_classification_number.setText(ldcla.get(position).getNumber() + "");
        TextView Tv_classification_name = (TextView)view.findViewById(R.id.Tv_classification_name);
        Tv_classification_name.setText(ldcla.get(position).getName());
        TextView Tv_classification_material = (TextView) view.findViewById(R.id.Tv_classification_material);
        Tv_classification_material.setText(ldcla.get(position).getIntro());
        ImageView Im_classification_image = (ImageView) view.findViewById(R.id.Iv_classification);
        Url = ldcla.get(position).getImage();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(Url,Im_classification_image,options);
        return view;
    }
}
