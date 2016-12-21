package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataRecommend;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.Urls;
import com.example.administrator.ui.VerticalTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
            }else {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_left, null);
            }
//
        ImageView Rec_Image = (ImageView)view.findViewById(R.id.Iv_recommend_image);
        Url = mData.get(i).getImage();
        String string = Url.substring(7, Url.indexOf("/", 7));
        Url = Url.replaceAll(string, Urls.mIp);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .showImageOnFail(R.drawable.img_fail)  //设置图片下载失败时显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        imageLoader.displayImage(Url,Rec_Image,options);

        TextView Rec_Name = (TextView)view.findViewById(R.id.Tv_recommend_name);
        Rec_Name.setText(mData.get(i).getName());
        /**
         * 依据字符串长度修改字体大小
         */
        if ((mData.get(i).getName()).length() > 6) {
            Rec_Name.setTextSize(13);
        }else if((mData.get(i).getName()).length() > 5){
            Rec_Name.setTextSize(14);
        }
        else if((mData.get(i).getName()).length() > 4){
            Rec_Name.setTextSize(16);
        }
        VerticalTextView Rec_Intr = (VerticalTextView) view.findViewById(R.id.Tv_recommend_introduction);
        Rec_Intr.setText(mData.get(i).getIntroduction());
        return view;
    }

}
