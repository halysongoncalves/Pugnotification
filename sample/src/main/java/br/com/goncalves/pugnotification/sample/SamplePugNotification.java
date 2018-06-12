package br.com.goncalves.pugnotification.sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.goncalves.pugnotification.interfaces.ImageLoader;
import br.com.goncalves.pugnotification.interfaces.OnImageLoadingCompleted;
import br.com.goncalves.pugnotification.notification.Load;
import br.com.goncalves.pugnotification.notification.PugNotification;

public class SamplePugNotification extends AppCompatActivity implements ImageLoader {
    private static final String TAG = "SamplePugNotification";
    private static final String CHANNEL_ID = "br.com.goncalves.pugnotification.sample.GENERAL_NOTIFICATIONS";
    private EditText mEdtTitle, mEdtMessage, mEdtBigText, mEdtUrl;
    private Button mBtnNotifySimple, mBtnNotifyCustom;
    private Context mContext;
    // Keep a strong reference to keep it from being garbage collected inside into method
    private Target viewTarget;
    private Spinner mSpnType;
    private RelativeLayout mContentBigText;
    private int mPosSelected = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.pugnotification_sample_activity);

        createNotificationChannel();

        loadInfoComponents();
        loadListeners();
        postTestNotification();
    }

    private void postTestNotification() {
        PugNotification.with(this, CHANNEL_ID)
                .load()
                .identifier(1)
                .tag("tag")
                .title("Test Notification Title")
                .message("Test Notification content")
                .smallIcon(R.drawable.pugnotification_ic_launcher)
                .largeIcon(R.drawable.pugnotification_ic_launcher)
                .autoCancel(true)
                .useSpanForCustomNotification(false)
                .custom()
                .setImageLoader(this)
                .background("https://placeimg.com/1120/412/any")
                .textBackground(Color.parseColor("#607D8B"), true)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "General Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("All notifications");
            channel.enableVibration(true);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager == null) {
                Log.e(TAG, "Failed to create notifications");
                return;
            }
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void loadInfoComponents() {
        this.mEdtTitle = (EditText) findViewById(R.id.edt_title);
        this.mEdtMessage = (EditText) findViewById(R.id.edt_message);
        this.mEdtBigText = (EditText) findViewById(R.id.edt_bigtext);
        this.mEdtUrl = (EditText) findViewById(R.id.edt_url);
        this.mBtnNotifySimple = (Button) findViewById(R.id.btn_notify_simple);
        this.mBtnNotifyCustom = (Button) findViewById(R.id.btn_notify_custom);
        this.mSpnType = (Spinner) findViewById(R.id.spn_notification_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pugnotification_notification_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.mSpnType.setAdapter(adapter);

        this.mContentBigText = (RelativeLayout) findViewById(R.id.content_bigtext);
    }

    private void loadListeners() {
        mSpnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosSelected = position;
                switch (position) {
                    case 0:
                        mContentBigText.setVisibility(View.GONE);
                        break;
                    case 1:
                        mContentBigText.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mContentBigText.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBtnNotifySimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEdtTitle.getText().toString();
                String message = mEdtMessage.getText().toString();
                String bigtext = mEdtBigText.getText().toString();
                if (title.length() > 0 && message.length() > 0) {
                    Load mLoad = PugNotification.with(mContext, CHANNEL_ID).load()
                            .smallIcon(R.drawable.pugnotification_ic_launcher)
                            .autoCancel(true)
                            .largeIcon(R.drawable.pugnotification_ic_launcher)
                            .title(title)
                            .message(message)
                            .flags(Notification.DEFAULT_ALL);

                    switch (mPosSelected) {
                        case 0:
                            mLoad.simple()
                                    .build();
                            break;
                        case 1:
                            if (bigtext.length() > 0) {
                                mLoad.bigTextStyle(bigtext, message)
                                        .simple()
                                        .build();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        R.string.pugnotification_text_empty_fields,
                                        Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 2:
                            mLoad.inboxStyle(getResources().
                                            getStringArray(R.array.pugnotification_notification_inbox_lines),
                                    title,
                                    getResources().getString(R.string.pugnotification_text_summary))
                                    .simple()
                                    .build();
                            break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.pugnotification_text_empty_fields,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnNotifyCustom.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                String title = mEdtTitle.getText().toString();
                String message = mEdtMessage.getText().toString();
                String bigtext = mEdtBigText.getText().toString();
                String url = mEdtUrl.getText().toString();

                if (title.length() > 0 && message.length() > 0) {
                    PugNotification.with(mContext, CHANNEL_ID).load()
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
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.pugnotification_text_empty_fields,
                            Toast.LENGTH_SHORT).show();
                }
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
}
