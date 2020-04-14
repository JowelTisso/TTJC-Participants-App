package com.job.challenge.ttjcParticipantsApp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;

public class ImagePopupX extends androidx.appcompat.widget.AppCompatImageView {
    private Context context;
    private PopupWindow popupWindow;
    View layout;
    private ImageView imageView;

    private int windowHeight = 0;
    private int windowWidth = 0;
    private boolean imageOnClickClose;
    private boolean hideCloseIcon;
    private boolean fullScreen;

    private int backgroundColor = Color.parseColor("#00000000");


    public ImagePopupX(Context context) {
        super(context);
        this.context = context;
    }

    public ImagePopupX(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    private int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    /**
     * Close Options
     **/

    public void setImageOnClickClose(boolean imageOnClickClose) {
        this.imageOnClickClose = imageOnClickClose;
    }


    private boolean isImageOnClickClose() {
        return imageOnClickClose;
    }

    private boolean isHideCloseIcon() {
        return hideCloseIcon;
    }

    public void setHideCloseIcon(boolean hideCloseIcon) {
        this.hideCloseIcon = hideCloseIcon;
    }

    private boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * optimize version
     * @param imageUrl
     */
    public void initiatePopupWithGlide(String imageUrl, int layoutName,int rootLayoutId, int imageViewId) {

        try {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(layoutName, (ViewGroup) findViewById(rootLayoutId));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = layout.findViewById(imageViewId);

            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImagePopup ", e.getMessage());
        }
    }


    public void setLayoutOnTouchListener(OnTouchListener onTouchListener) {
        layout.setOnTouchListener(onTouchListener);
    }

    public void viewPopup(int closeBtnId) {

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);


        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        if(isFullScreen()) {
            popupWindow = new PopupWindow(layout, (width), (height), true);
        }else {
            if (windowHeight != 0 || windowWidth != 0) {
                width = windowWidth;
                height = windowHeight;
                popupWindow = new PopupWindow(layout, (width), (height), true);
            } else {
                popupWindow = new PopupWindow(layout, (int) (width * .8), (int) (height * .6), true);
            }
        }


        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        ImageView closeIcon = layout.findViewById(closeBtnId);

        if (isHideCloseIcon()) {
            closeIcon.setVisibility(View.GONE);
        }
        closeIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        if (isImageOnClickClose()) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }

        dimBehind(popupWindow);

    }


    public static void dimBehind(PopupWindow popupWindow) {
        try {
            View container;
            if (popupWindow.getBackground() == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    container = (View) popupWindow.getContentView().getParent();
                } else {
                    container = popupWindow.getContentView();
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    container = (View) popupWindow.getContentView().getParent().getParent();
                } else {
                    container = (View) popupWindow.getContentView().getParent();
                }
            }
            Context context = popupWindow.getContentView().getContext();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.5f;
            wm.updateViewLayout(container, p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
