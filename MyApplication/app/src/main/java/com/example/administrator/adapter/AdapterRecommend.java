package com.example.administrator.adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.domain.DataRecommend;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.Urls;
import com.example.administrator.ui.VerticalTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐Adapter
 * Created by 王晨阳 on 2016/11/22.
 */

public class AdapterRecommend extends BaseAdapter{
    private Context mContext;
    private List<DataRecommend> mData = new ArrayList<>();
    private Bitmap bitmap;
    private String Url;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * 带参构造函数
     * @param mContext
     * @param mData
     */
    public AdapterRecommend(Context mContext, List<DataRecommend> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
//        return 3;
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null) {
            if (i%2 == 0) {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_right, null);
                Toast.makeText(mContext,i+"",Toast.LENGTH_SHORT).show();
            }else {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_left, null);
                Toast.makeText(mContext,i+"",Toast.LENGTH_SHORT).show();
            }
//        }
        //if (view == null) {
            if (i%2 == 0) {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_right, null);
                Log.e("=======", i+"+right" );
            }else {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_left, null);
                Log.e("=======", i+"+left" );
            }
        //}
        ImageView Rec_Image = (ImageView)view.findViewById(R.id.Iv_recommend_image);
        Url = mData.get(i).getImage();
        String string = Url.substring(7, Url.indexOf("/", 7));
        Url = Url.replaceAll(string, Urls.mIp);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        imageLoader.displayImage(Url,Rec_Image,options);

        TextView Rec_Name = (TextView)view.findViewById(R.id.Tv_recommend_name);
        Rec_Name.setText(mData.get(i).getName());
        VerticalTextView Rec_Intr = (VerticalTextView) view.findViewById(R.id.Tv_recommend_introduction);
        Rec_Intr.setText(mData.get(i).getIntroduction());
        return view;
    }

}
