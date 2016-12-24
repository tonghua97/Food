package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataCollectionPickfun;
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

public class AdapterCollectionPickfun extends BaseAdapter {
    private Context context;
    private List<DataCollectionPickfun> ldcp = new ArrayList<>();
    private String urlImage;
    public ImageLoader imageLoader = ImageLoader.getInstance();
//    private String Url;
//    public ImageLoader imageLoader = ImageLoader.getInstance();

    public AdapterCollectionPickfun(List<DataCollectionPickfun> ld ,Context c){
        ldcp = ld;
        context =c;
    }
    @Override
    public int getCount() {
        return ldcp.size();
    }

    @Override
    public Object getItem(int position) {
        return ldcp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_collection_pickfun,null);
        }
//        TextView Tv_pickfun_title = (TextView) view.findViewById(R.id.Tv_pickfun_title);
//        Tv_pickfun_title.setText(ldcp.get(position).getmPickfun_Name());
//        /**
//         * 依据字符串长度修改字体大小
//         */
//        if ((ldcp.get(position).getmPickfun_Name()).length() > 8) {
//            Tv_pickfun_title.setTextSize(16);
//        }else if((ldcp.get(position).getmPickfun_Name()).length() > 7){
//            Tv_pickfun_title.setTextSize(18);
//        }else if((ldcp.get(position).getmPickfun_Name()).length() > 6){
//            Tv_pickfun_title.setTextSize(20);
//        }
//
//        ImageView Iv_pickfun = (ImageView) view.findViewById(R.id.Iv_pickfun_image);
//        Url = ldcp.get(position).getUrl();
//        String string = Url.substring(7, Url.indexOf("/", 7));
//        Url = Url.replaceAll(string, Urls.mIp);
//
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
//                .showImageOnFail(R.drawable.img_fail)  //设置图片下载失败时显示的图片
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
//                .build();//构建完成
//        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
//        imageLoader.displayImage(Url,Iv_pickfun,options);
        ImageView Iv_Cpickfun = (ImageView)view.findViewById(R.id.Iv_collection_pickfun);
        urlImage = ldcp.get(position).getImage();
        String string = urlImage.substring(7, urlImage.indexOf("/", 7));
        urlImage = urlImage.replaceAll(string, Urls.mIp);
        Log.e(urlImage,"String");

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(urlImage,Iv_Cpickfun,options);

        TextView Tv_Cpname = (TextView)view.findViewById(R.id.Tv_collection_name);
        Tv_Cpname.setText(ldcp.get(position).getName());
        TextView Tv_Cpintroduction = (TextView)view.findViewById(R.id.Tv_collection_introduction);
        Tv_Cpintroduction.setText(ldcp.get(position).getIntroduction());
        return view;
    }
}
