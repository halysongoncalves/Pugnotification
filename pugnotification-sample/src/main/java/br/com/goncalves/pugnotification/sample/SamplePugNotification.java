package br.com.goncalves.pugnotification.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.goncalves.pugnotification.interfaces.ImageLoader;
import br.com.goncalves.pugnotification.interfaces.OnImageLoadingCompleted;
import br.com.goncalves.pugnotification.notification.PugNotification;

public class SamplePugNotification extends ActionBarActivity implements ImageLoader {
    private EditText mEdtTitle, mEdtMessage, mEdtBigText, mEdtUrl;
    private Button mBtnNotifySimple, mBtnNotifyCustom;
    private Context mContext;
    // Keep a strong reference to keep it from being garbage collected inside into method
    private Target viewTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.pugnotification_sample_activity);

        loadInfoComponents();
        loadListeners();
    }

    private void loadInfoComponents() {
        this.mEdtTitle = (EditText) findViewById(R.id.edt_title);
        this.mEdtMessage = (EditText) findViewById(R.id.edt_message);
        this.mEdtBigText = (EditText) findViewById(R.id.edt_bigtext);
        this.mEdtUrl = (EditText) findViewById(R.id.edt_url);
        this.mBtnNotifySimple = (Button) findViewById(R.id.btn_notify_simple);
        this.mBtnNotifyCustom = (Button) findViewById(R.id.btn_notify_custom);
    }

    private void loadListeners() {
        mBtnNotifySimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEdtTitle.getText().toString();
                String message = mEdtMessage.getText().toString();
                String bigtext = mEdtBigText.getText().toString();

                PugNotification.with(mContext).load()
                        .smallIcon(R.drawable.pugnotification_ic_launcher)
                        .autoCancel(true)
                        .largeIcon(R.drawable.pugnotification_ic_launcher)
                        .title(title)
                        .message(message)
                        .bigTextStyle(bigtext)
                        .simple()
                        .build();
            }
        });

        mBtnNotifyCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEdtTitle.getText().toString();
                String message = mEdtMessage.getText().toString();
                String bigtext = mEdtBigText.getText().toString();
                String url = mEdtUrl.getText().toString();

                PugNotification.with(mContext).load()
                        .title(title)
                        .message(message)
                        .bigTextStyle(bigtext)
                        .smallIcon(R.drawable.pugnotification_ic_launcher)
                        .largeIcon(R.drawable.pugnotification_ic_launcher)
                        .color(android.R.color.background_dark)
                        .custom()
                        .setImageLoader(SamplePugNotification.this)
                        .background(url)
                        .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
                        .build();
            }
        });
    }

    @Override
    public void load(String uri, final OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(uri).into(viewTarget);
    }

    @Override
    public void load(int imageResId, OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(imageResId).into(viewTarget);
    }

    private static Target getViewTarget(final OnImageLoadingCompleted onCompleted) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                onCompleted.imageLoadingCompleted(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }
}
