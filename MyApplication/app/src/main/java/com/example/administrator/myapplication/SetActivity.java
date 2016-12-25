package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.domain.DataUser;
import com.example.administrator.ui.CircleImageView;
import com.example.administrator.ui.Urls;
import com.example.administrator.ui.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁爽 on 16.11.24.
 * 说明：“设置”页面的编码
 */
public class SetActivity extends Activity {
    private Button mBtBack;             //返回键
    private RelativeLayout mSetAvatar;  //头像修改
    private RelativeLayout mSetPwd;     //密码修改
    private RelativeLayout mSetName;    //用户名修改
    private RelativeLayout mSetPhone;   //手机号修改
    private RelativeLayout mSetEmail;   //邮箱修改
    private TextView mUname;
    private TextView mPhone;
    private TextView mEmail;
    private Spinner mSpGender;         //性别选择下拉列表
    private ArrayAdapter adapter = null;

    protected static final int CHOOSE_PICTURE = 0;//从相册中选择图片
    private String cropImagePath;
    protected static final int TAKE_PICTURE = 1;//拍照
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private CircleImageView iv_personal_icon;
    private static final String IMAGE_FILE_NAME = "image.jpg";// 头像文件

    private String userId;
    private String str;
    private DataUser data;
    private String urlImage;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private String sex;
    public  boolean isFirstSetSex = true;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1){
                if (str.equals("0")){
                    Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                }else{
                    setViews();
                }
            }else if (msg.what == 2){
                if(!isFirstSetSex) {
                    if (str.equals("0")) {
                        Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();
                    } else if (str.equals("1")) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    } else if (str.equals("2")) {
                        Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    isFirstSetSex = false;
                }
            }

        }
    };

    private void setViews() {
        mUname.setText(data.getUserName());
        mPhone.setText(data.getUserNum());
        mEmail.setText(data.getUserPost());
        if (data.getUserSex().equals("男")){
            mSpGender.setSelection(0,true);
        }else{
            mSpGender.setSelection(1,true);
        }


        urlImage = data.getUserImage();
        if (urlImage.contains("http://")){

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                    .build();//构建完成
            imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
            imageLoader.displayImage(urlImage,iv_personal_icon,options);
        }else{
            iv_personal_icon.setImageResource(R.drawable.head);
        }

    }

    private void setParse() {
        if (str == ""){
            return;
        }else{
            try {
                JSONObject json = new JSONObject(str);
                data = new DataUser();
                data.setUserName(json.getString("userName"));
                data.setUserImage(json.getString("userImage"));
                data.setUserAccount(json.getString("userAccount"));
                data.setUserNum(json.getString("userNum"));
                data.setUserPost(json.getString("userPost"));
                data.setUserSex(json.getString("userSex"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        //获取控件
        getViews();
        //设置监听
        setListener();
        //设置下拉列表--用户性别选择
//        setSpinner();

        mSpGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] array = getResources().getStringArray(R.array.setting_gender);
                sex = array[i];
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        UpdateUserSex();
                        Message m = new Message();
                        handler.sendEmptyMessage(2);
                    }
                };
                thread.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void UpdateUserSex() {
        try {
            str = "";
            URI u = new URI(Urls.urlSetUserSex);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userId",userId);
            NameValuePair pair2 = new BasicNameValuePair("userSex",sex);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = "";

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取控件
     */
    private void getViews(){
        //返回上一页
        mBtBack = (Button)findViewById(R.id.Setting_Bt_Back);
        //修改头像
        mSetAvatar = (RelativeLayout)findViewById(R.id.setting_Rlay_avataredit);
        //修改用户名
        mSetName = (RelativeLayout)findViewById(R.id.setting_Rlay_unameedit);
        //修改电话号码
        mSetPhone = (RelativeLayout)findViewById(R.id.setting_Rlay_phone);
        //修改邮箱
        mSetEmail = (RelativeLayout)findViewById(R.id.setting_Rlay_Email);
        //修改密码
        mSetPwd = (RelativeLayout)findViewById(R.id.setting_Rlay_pwdedit);
        //头像显示
        iv_personal_icon = (CircleImageView)findViewById(R.id.Setting_Iv_avatar);
        //用户名
        mUname = (TextView)findViewById(R.id.setting_Tv_username);
        //电话号码
        mPhone = (TextView)findViewById(R.id.setting_Tv_phone);
        //邮箱
        mEmail = (TextView)findViewById(R.id.setting_Tv_Email);
        //性别下拉列表
        mSpGender = (Spinner)findViewById(R.id.Setting_Sp_Gender);
    }

    /**
     * 设置监听器
     */
    private void setListener(){
        MyListener listener = new MyListener();
        //返回上一页
        mBtBack.setOnClickListener(listener);
        //密码修改
        mSetPwd.setOnClickListener(listener);
        //用户名修改
        mSetName.setOnClickListener(listener);
        //手机号
        mSetPhone.setOnClickListener(listener);
        //邮箱/**
        mSetEmail.setOnClickListener(listener);
        //头像修改
        mSetAvatar.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Setting_Bt_Back:
                    /*返回上一页*/
                    finish();
                    break;

                case R.id.setting_Rlay_avataredit:
                    /*修改头像*/
                    //显示修改头像的对话框
                    showChoosePicDialog();
                    break;
                case R.id.setting_Rlay_unameedit:
                     /*跳转到昵称修改页面*/
                    Intent intent1 = new Intent(SetActivity.this,Personal_setting_UnameEditActivity.class);
                    intent1.putExtra("setting_userName",mUname.getText().toString());
                    startActivity(intent1);
                    break;
                case R.id.setting_Rlay_phone:
                     /*跳转到手机号修改页面*/
                    Intent intent3 = new Intent(SetActivity.this,Personal_setting_PhoneActivity.class);
                    intent3.putExtra("setting_phone",mPhone.getText().toString());
                    startActivity(intent3);
                    break;
                case R.id.setting_Rlay_Email:
                     /*跳转到邮箱修改页面*/
                    Intent intent4 = new Intent(SetActivity.this,Personal_setting_EmailActivity.class);
                    intent4.putExtra("setting_email",mEmail.getText().toString());
                    startActivity(intent4);
                    break;
                case R.id.setting_Rlay_pwdedit:
                     /*跳转到密码修改页面*/
                    Intent intent2 = new Intent(SetActivity.this,Personal_setting_PwdEditActivity.class);
                    startActivity(intent2);
                    break;
                default:
                    break;
            }
        }
    }

    public void getHttpUser() {
        isFirstSetSex = true;
        try {
            str = "";
            URI u = new URI(Urls.urlUser);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair = new BasicNameValuePair("userId",userId);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
            setParse();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    /*从本地相册选取图片作为头像*/
                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");// 设置文件类型
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;

                    /*启动手机相机拍摄照片作为头像*/
                    case TAKE_PICTURE:
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可用，存储照片文件
                        if (hasSdcard()){
                            tempUri = Uri.fromFile(new File(Environment
                                    .getExternalStorageDirectory(), IMAGE_FILE_NAME));
                            // 指定照片保存路径（SD卡），IMAGE_FILE_NAME image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                            startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        }else {
                            Toast.makeText(SetActivity.this, "请插入sd卡",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                //拍照
                case TAKE_PICTURE:
                    if (hasSdcard()){
//                        File tempFile = new File(
//                                Environment.getExternalStorageDirectory(),
//                                IMAGE_FILE_NAME);
                        startPhotoZoom(tempUri); // 对图片进行裁剪处理
                    }else {
                        Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_SHORT)
                                .show();
                    }
                    break;

                //相册
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 对图片进行裁剪处理
                    break;

                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪原始图片方法的实现
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = toRoundBitmap(photo);//将头像设置成圆形
            Drawable drawable = new BitmapDrawable(photo);
            iv_personal_icon.setImageDrawable(drawable);
            //上传至服务器
            uploadPic(cropImagePath);
        }
    }


    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * 把bitmap转成圆形
     * */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int r=0;
        //取最短边做边长
        if(width<height){
            r=width;
        }else{
            r=height;
        }
        //构建一个bitmap

        Bitmap backgroundBm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas=new Canvas(backgroundBm);
        Paint p=new Paint();
        //设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect=new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, p);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);

        return backgroundBm;
    }

    /**
     * 是否存在SD卡
     * @return
     */
    private boolean hasSdcard() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 上传头像
     * @param cropImagePath  文件路径
     */
    private void uploadPic(String cropImagePath ) {
        if (cropImagePath == null) {
           Toast.makeText(SetActivity.this,"文件不存在",Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(cropImagePath);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            try {
//                params.put("userId", userId);
                params.put("img", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String url = "http://" + Urls.mIp + "/http/userHeadSculpture";
            client.post(url, params, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String result = new String(bytes);
                    if (result.equals("1")) {
						Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SetActivity.this,MainActivity.class);
                        Utils.utils = 3;
                        startActivity(intent);
                        finish();
                    } else {
						Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
					Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_LONG).show();
                }
            });
        } else {
			Toast.makeText(getApplicationContext(), "图片不支持", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        if (userId != "") {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    getHttpUser();

                    Message m = new Message();
                    handler.sendEmptyMessage(1);
                }
            };
            thread.start();
        }
    }
}
