package br.com.goncalves.pugnotification.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class SamplePugNotification extends Activity {
    private EditText mEdtTitle, mEdtMessage;
    private Button mBtnNotify;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pugnotification_sample_activity);

        loadInfoComponents();
        loadListeners();
    }

    private void loadInfoComponents() {
        this.mContext = getApplicationContext();
        this.mEdtTitle = (EditText) findViewById(R.id.edt_title);
        this.mEdtMessage = (EditText) findViewById(R.id.edt_message);
        this.mBtnNotify = (Button) findViewById(R.id.btn_notify);
    }

    private void loadListeners() {
        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEdtTitle.getText().toString();
                String message = mEdtMessage.getText().toString();

                PugNotification.with(mContext).load().title(title).message(message).simple().build();
            }
        });
    }
}
