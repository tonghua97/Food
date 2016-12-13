package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataRecommend;
import com.example.administrator.myapplication.R;
import com.example.administrator.ui.VerticalTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐Adapter
 * Created by 王晨阳 on 2016/11/22.
 */

public class AdapterRecommend extends BaseAdapter{
    private Context mContext;
    private List<DataRecommend> mData = new ArrayList<>();

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
        if (view == null) {
            if (i%2 == 0) {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_right, null);
            }else {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recommend_left, null);
            }
        }
        ImageView Rec_Image = (ImageView)view.findViewById(R.id.Iv_recommend_image);
        String Url = mData.get(i).getImage();
        /*
        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.drawable.loading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(Url, Img_Goods, options);
*/
        TextView Rec_Name = (TextView)view.findViewById(R.id.Tv_recommend_name);
        Rec_Name.setText(mData.get(i).getName());
        VerticalTextView Rec_Intr = (VerticalTextView) view.findViewById(R.id.Tv_recommend_introduction);
        Rec_Intr.setText(mData.get(i).getIntroduction());
        return view;
    }
}
