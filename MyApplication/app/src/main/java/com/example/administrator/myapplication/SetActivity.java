package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 梁爽 on 16.11.24.
 * 说明：“设置”页面的编码
 */

public class SetActivity extends Activity {


    private Button mBtBack;             //返回键
    private RelativeLayout mSetAvatar;  //头像修改
    private RelativeLayout mSetPwd;     //密码修改
    private RelativeLayout mSetName;    //用户名修改
    private TextView mUname;

    private Spinner mSpGender;         //性别选择下拉列表
    private ArrayAdapter adapter = null;

    /*请求识别码*/
    protected static final int CHOOSE_PICTURE = 0;//从相册中选择图片
    protected static final int TAKE_PICTURE = 1;//拍照
    private static final int CROP_SMALL_PICTURE = 2;

    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    private static final String IMAGE_FILE_NAME = "image.jpg";// 头像文件


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);


        //获取控件
        getViews();

        //获取用户名
        getUname();

        //设置监听
        setListener();

        //设置下拉列表--用户性别选择
        setSpinner();
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
        //修改密码
        mSetPwd = (RelativeLayout)findViewById(R.id.setting_Rlay_pwdedit);

        //头像显示
        iv_personal_icon = (ImageView)findViewById(R.id.Setting_Iv_avatar);
        //用户名
        mUname = (TextView)findViewById(R.id.setting_Tv_username);
        //性别下拉列表
        mSpGender = (Spinner)findViewById(R.id.Setting_Sp_Gender);
    }

    /**
     * 获取用户名
     */
    private void getUname(){
        SharedPreferences name = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String Uname = name.getString("UNAME","");
        mUname.setText(Uname);
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
                     /*跳转到用户名修改页面*/
                    Intent intent1 = new Intent(SetActivity.this,Personal_setting_UnameEditActivity.class);
                    startActivity(intent1);
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
                        File tempFile = new File(
                                Environment.getExternalStorageDirectory(),
                                IMAGE_FILE_NAME);
                        startPhotoZoom(tempUri); // 对图片进行裁剪处理
                    }else {
                        Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_SHORT)
                                .show();
                    }
//                    startPhotoZoom(tempUri); // 对图片进行裁剪处理
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

//            //  saveImage();
//            try {
//                SharedPreferences mySharedPreferences=getSharedPreferences("base64", Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor1=mySharedPreferences.edit();
//                ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                //读取和压缩qq.png，并将其压缩结果保存在ByteArrayOutputStream对象中
//
//                BitmapFactory.decodeResource(getResources(),/*图片.jpg*/).compress(Bitmap.CompressFormat.JPEG, 50, baos);
//                //对压缩后的字节进行base64编码
//                String imageBase64=new String(Base64.encode(baos.toByteArray(),Base64.DEFAULT));
//                // String imageBase64=new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
//                //保存转换后的Base64格式字符串
//                editor1.putString("image", imageBase64);
//                editor1.commit();
//                baos.close();
//
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//
//
//            //getImage
//                //下面代码从base64.xml文件中读取以保存的图像，并将其显示在ImageView控件中
//                try {
//                    SharedPreferences mySharedPreference=getSharedPreferences("base64", Activity.MODE_PRIVATE);
//                    //读取Base64格式的图像数据
//                    String image=mySharedPreference.getString("image", "");
//                    //对Base64格式的字符串进行解码，还原成字节数组
//                    byte[] imageBytes=Base64.decode(image.getBytes(), Base64.DEFAULT);
//                    ByteArrayInputStream bais=new ByteArrayInputStream(imageBytes);
//                    ImageView imageView=(ImageView)findViewById(R.id.Setting_Iv_avatar);
//                    imageView.setImageDrawable(Drawable.createFromStream(bais, "image"));
//                    bais.close();
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
            //上传至服务器
            //  uploadPic(photo);
        }
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

    //    private void uploadPic(Bitmap bitmap) {
//        // 上传至服务器
//        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
//        // 注意这里得到的图片已经是圆形图片了
//        // bitmap是没有做个圆形处理的，但已经被裁剪了
//       String imagePath = Utils.savePhoto(bitmap, Environment
//                .getExternalStorageDirectory().getAbsolutePath(), String
//                .valueOf(System.currentTimeMillis()));
//        Log.e("imagePath", imagePath + "");
//        if (imagePath != null) {
//            // 拿着imagePath上传了
//            // ...
//            Thread thread = new Thread(runnable);
//            thread.start();
//        }
//    }
//
//    /* 上传文件至Server的方法 */
//    private void uploadFile() {
//        String end = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//        try {
//
//            URL url = new URL(actionUrl);//服务器地址
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            /* 允许Input、Output，不使用Cache */
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.setUseCaches(false);
//            /* 设置传送的method=POST */
//            con.setRequestMethod("POST");
//
//            /* setRequestProperty *///设置请求属性
//            con.setRequestProperty("Connection", "Keep-Alive");
//            con.setRequestProperty("Charset", "UTF-8");
//            con.setRequestProperty("Content-Type",
//                    "multipart/form-data;boundary=" + boundary);
//            /* 设置DataOutputStream *///数据输出流
//            //heading为服务器接收的键
//            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
//            ds.writeBytes(twoHyphens + boundary + end);
//            ds.writeBytes("Content-Disposition: form-data; "
//                    + "name=\"headimg\";filename=\"" + newName + "\"" + end);
//            ds.writeBytes(end);
//            /* 取得文件的FileInputStream */ //文件输入流
//            fStream = new FileInputStream(imagePath);//要上传的图片路径，
//            /* 设置每次写入1024bytes */
//            int bufferSize = 1024;
//            byte[] buffer = new byte[bufferSize];
//            int length = -1;
//            /* 从文件读取数据至缓冲区 */
//            while ((length = fStream.read(buffer)) != -1) {
//                /* 将资料写入DataOutputStream中 */
//                ds.write(buffer, 0, length);
//            }
//            ds.writeBytes(end);
//            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
//            /* close streams */
//            fStream.close();
//            ds.flush();
//            /* 取得Response内容 */
//            InputStream is = con.getInputStream();
//            int ch;
//            StringBuffer b = new StringBuffer();
//            while ((ch = is.read()) != -1) {
//                b.append((char) ch);
//            }
//            /* 将Response显示于Dialog */
//            showDialog("上传结果" + b.toString().trim());
//            Log.e("文件传输结果", b.toString().trim());
//            /* 关闭DataOutputStream */
//            ds.close();
//        } catch (Exception e) {
//            showDialog("上传失败" + e);
//            Log.i("败", "错误原因：" + e);
//        }
//    }
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            Looper.prepare();// 创建消息循环
//
//            uploadFile();
//            Message msg = new Message();
////          data.putString("image",  fStream);
//            handler.sendMessage(msg);
//            Looper.loop();// 从消息队列取消
//        }
//    };
//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//        };
//    };


    /**
     * 下拉列表的设置
     */
    private void setSpinner(){
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.setting_gender);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mSpGender .setAdapter(adapter);

        //保存下列列表的选择
        commitSpinnerSelection();

        //读取存在SharedPreferences里的gender值
        getSpinnerSelection();
    }
    /**
     * 保存下列列表的选择
     */
    private void commitSpinnerSelection(){
        SharedPreferences spf = getSharedPreferences("GENDER_SETTING",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = spf.edit();
        //添加事件Spinner事件监听
        mSpGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("SelectedPosition",position);
                //  editor.putString("SelectedGender",parent.getItemAtPosition(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do Nothing
            }
        });
    }
    /**
     * 读取存在SharedPreferences里的gender值
     */
    private void getSpinnerSelection(){
        SharedPreferences spf_gender = getSharedPreferences("GENDER_SETTING",Context.MODE_PRIVATE);
        //String gender = spf_gender.getString("SelectedGender","");
        int position = spf_gender.getInt("SelectedPosition", 0 );

        //设置spinner的值，让其当前选择项是与以前的一样
        mSpGender.setSelection(position);
    }
}
