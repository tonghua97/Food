package com.example.administrator.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by wcyhp on 2016/12/26.
 */

public class GifDialog extends Dialog{

    public GifDialog(Context context) {
        super(context);
    }

    public GifDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        private Context context;
        private int drawable;
        private GifImageView gifImageView;

        private DialogInterface.OnClickListener positiveButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setImges(int drawable) {
            this.drawable = drawable;
            return this;
        }

        public GifDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final GifDialog dialog = new GifDialog(context, R.style.gif_dialog);
            View layout = inflater.inflate(R.layout.gif_dialog_view, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            gifImageView = (GifImageView) layout.findViewById(R.id.Gv_gif);
            gifImageView.setImageResource(drawable);
            gifImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // dialog.dismiss();
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
