package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataRanklist;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.Urls;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophia on 2016/11/24.
 */
public class AdapterRanklist extends BaseAdapter {
    private Context context;
    private List<DataRanklist> lrl = new ArrayList<>();
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private String Url;

    public AdapterRanklist(List<DataRanklist> lrl,Context context){
        this.lrl = lrl;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lrl.size();
    }

    @Override
    public Object getItem(int position) {
        return lrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lrl.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_ranklist,null);
        }
        //食谱收藏排行
        TextView Tv_rank_id = (TextView)convertView.findViewById(R.id.Tv_rank_id);
        Tv_rank_id.setText("No." + lrl.get(position).getRank());
        //获取排行待解决 2016/11/23 ginger lrl.get(position).getRank()

        //食谱收藏数 Tv_ranklist_collect
        TextView Tv_ranklist_collect = (TextView)convertView.findViewById(R.id.Tv_ranklist_collect);
        Tv_ranklist_collect.setText(lrl.get(position).getNum() + "收藏");

        //食谱图片 Iv_ranklist_recipeimg
        ImageView Iv_ranklist_recipeimg = (ImageView)convertView.findViewById((R.id.Iv_ranklist_recipeimg));

        Url = lrl.get(position).getImage();
        String string = Url.substring(7, Url.indexOf("/", 7));
        Url = Url.replaceAll(string, Urls.mIp);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .showImageOnFail(R.drawable.img_fail)  //设置图片下载失败时显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(Url,Iv_ranklist_recipeimg,options);

        //食谱名 Tv_ranklist_recipename
        TextView Tv_pickfun_title = (TextView) convertView.findViewById(R.id.Tv_ranklist_recipename);
        Tv_pickfun_title.setText(lrl.get(position).getName());
        /**
         * 依据字符串长度修改字体大小
         */
        if ((lrl.get(position).getName()).length() > 6) {
            Tv_pickfun_title.setTextSize(14);
        }else if((lrl.get(position).getName()).length() > 5){
            Tv_pickfun_title.setTextSize(16);
        }
        else if((lrl.get(position).getName()).length() > 4){
            Tv_pickfun_title.setTextSize(18);
        }

        return convertView;
    }
}
