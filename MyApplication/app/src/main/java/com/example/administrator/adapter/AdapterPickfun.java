package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataPickfun;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.Urls;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13173 on 2016/11/22.
 */

public class AdapterPickfun extends BaseAdapter {
    private Context context;
    private List<DataPickfun> ldp = new ArrayList<>();
    private String Url;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    public AdapterPickfun(List<DataPickfun> ld, Context context){
        ldp = ld;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldp.size();
    }

    @Override
    public Object getItem(int position) {
        return ldp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldp.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_pickfun,null);
        }
        TextView Tv_pickfun_title = (TextView) view.findViewById(R.id.Tv_pickfun_title);
        Tv_pickfun_title.setText(ldp.get(position).getmPickfun_Name());
        /**
         * 依据字符串长度修改字体大小
         */
        if ((ldp.get(position).getmPickfun_Name()).length() > 8) {
            Tv_pickfun_title.setTextSize(16);
        }else if((ldp.get(position).getmPickfun_Name()).length() > 7){
            Tv_pickfun_title.setTextSize(18);
        }else if((ldp.get(position).getmPickfun_Name()).length() > 6){
            Tv_pickfun_title.setTextSize(20);
        }

        ImageView Iv_pickfun = (ImageView) view.findViewById(R.id.Iv_pickfun_image);
        Url = ldp.get(position).getUrl();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .showImageOnFail(R.drawable.img_fail)  //设置图片下载失败时显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(Url,Iv_pickfun,options);

        return view;
    }
}
