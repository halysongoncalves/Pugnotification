package br.com.goncalves.pugnotification.sample;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class SamplePugNotification extends ActionBarActivity {
    private EditText mEdtTitle, mEdtMessage, mEdtBigText, mEdtUrl;
    private Button mBtnNotifySimple, mBtnNotifyCustom;
    private Context mContext;

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
                        .color(android.R.color.holo_green_dark)
                        .custom()
                        .background(url)
                        .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
                        .build();
            }
        });
    }
}
