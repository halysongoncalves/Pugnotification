package br.com.goncalves.pugnotification.notification;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import br.com.goncalves.pugnotification.R;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class Custom extends Basic {
    private static final String TAG = Custom.class.getSimpleName();
    private RemoteViews mRemoteView;
    private String mTitle;
    private String mMessage;
    private String mPath;
    private int mSmallIcon;
    private int mBackgroundResId;

    public Custom(Builder builder, int identifier, String title, String message, int smallIcon) {
        super(builder, identifier);
        this.mRemoteView = new RemoteViews(PugNotification.mSingleton.mContext.getPackageName(), R.layout.pugnotification_custom);
        this.mTitle = title;
        this.mMessage = message;
        this.mSmallIcon = smallIcon;
        this.init();
    }

    private void init() {
        this.setTitle();
        this.setMessage();
        this.setSmallIcon();
    }

    private void setTitle() {
        mRemoteView.setTextViewText(R.id.notification_text_title, mTitle);
    }

    private void setMessage() {
        mRemoteView.setTextViewText(R.id.notification_text_message, mMessage);
    }

    private void setSmallIcon() {
        if (mSmallIcon <= 0) {
            mRemoteView.setImageViewResource(R.id.notification_img_icon, R.drawable.pugnotification_ic_launcher);
        }
        mRemoteView.setImageViewResource(R.id.notification_img_icon, mSmallIcon);
    }

    public Custom background(int resource) {
        if (resource <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (mPath != null) {
            throw new IllegalStateException("Background Already Set!");
        }

        this.mBackgroundResId = resource;
        return this;
    }

    public Custom background(String path) {
        if (mBackgroundResId >= 0) {
            throw new IllegalStateException("Background Already Set!");
        }

        if (mPath != null) {
            throw new IllegalStateException("Background Already Set!");
        }

        if (path == null) {
            throw new IllegalArgumentException("Path Must Not Be Null!");
        }
        if (path.trim().length() == 0) {
            throw new IllegalArgumentException("Path Must Not Be Empty!");
        }

        this.mPath = path;
        return this;
    }

    @Override
    public void build() {
        super.build();
        mNotificaton.bigContentView = mRemoteView;

        loadImageBackground();
    }

    private final void loadImageBackground() {
        if (mPath != null) {
            Picasso.with(mSingleton.mContext).load(mPath).placeholder(R.drawable.pugnotification_ic_placeholder).into(mRemoteView, R.id.notification_img_background, 0, mNotificaton);
        }
        Picasso.with(mSingleton.mContext).load(mBackgroundResId).placeholder(R.drawable.pugnotification_ic_placeholder).into(mRemoteView, R.id.notification_img_background, 0, mNotificaton);
    }
}
