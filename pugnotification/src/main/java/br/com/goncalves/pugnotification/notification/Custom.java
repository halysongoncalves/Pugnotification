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
    private String mUri;
    private int mSmallIcon;
    private int mBackgroundResId;
    private int mPlaceHolderResourceId;

    public Custom(Builder builder, int identifier, String title, String message, int smallIcon) {
        super(builder, identifier);
        this.mRemoteView = new RemoteViews(PugNotification.mSingleton.mContext.getPackageName(), R.layout.pugnotification_custom);
        this.mTitle = title;
        this.mMessage = message;
        this.mSmallIcon = smallIcon;
        this.mPlaceHolderResourceId = R.drawable.pugnotification_ic_placeholder;
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

        if (mUri != null) {
            throw new IllegalStateException("Background Already Set!");
        }

        this.mBackgroundResId = resource;
        return this;
    }

    public Custom setPlaceholder(int resource) {
        if (resource <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mPlaceHolderResourceId = resource;
        return this;
    }

    public Custom background(String uri) {
        if (mBackgroundResId > 0) {
            throw new IllegalStateException("Background Already Set!");
        }

        if (mUri != null) {
            throw new IllegalStateException("Background Already Set!");
        }

        if (uri == null) {
            throw new IllegalArgumentException("Path Must Not Be Null!");
        }
        if (uri.trim().length() == 0) {
            throw new IllegalArgumentException("Path Must Not Be Empty!");
        }

        this.mUri = uri;
        return this;
    }

    @Override
    public void build() {
        super.build();
        setBigContentView(mRemoteView);
        loadImageBackground();
    }

    private final void loadImageBackground() {
        if (mUri != null) {
            Picasso.with(mSingleton.mContext).load(mUri).placeholder(R.drawable.pugnotification_ic_placeholder)
                    .into(mRemoteView, R.id.notification_img_background, mIdentifier, mNotification);
        } else {
            Picasso.with(mSingleton.mContext).load(mBackgroundResId).placeholder(R.drawable.pugnotification_ic_placeholder)
                    .into(mRemoteView, R.id.notification_img_background, mIdentifier, mNotification);
        }
    }
}
