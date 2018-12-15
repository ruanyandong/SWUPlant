package com.example.ai.swuplant.login.listener;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.util.LinkedList;
import java.util.List;

/**
 * 我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。
 *
 * OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
 */
public class KeyboardWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardOpened(int keyboardHeightInPx);
        void onSoftKeyboardClosed();
    }

    private final List<SoftKeyboardStateListener> listeners = new LinkedList<SoftKeyboardStateListener>();
    private final View activityRootView;
    private int        lastSoftKeyboardHeightInPx;
    private boolean    isSoftKeyboardOpened;
    private int statusBarHeight = -1;

    public KeyboardWatcher(View activityRootView) {
        this(activityRootView, false);
    }



    public KeyboardWatcher(View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView     = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        //获取status_bar_height资源的ID
        int resourceId = activityRootView.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = activityRootView.getContext().getResources().getDimensionPixelSize(resourceId);
        }
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN)==WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    public void onGlobalLayout() {
        final Rect r = new Rect();
        //r will be populated with the coordinates of your view that area still visible.
        activityRootView.getWindowVisibleDisplayFrame(r);

        final int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
        if (!isSoftKeyboardOpened && heightDiff > activityRootView.getRootView().getHeight()/4) {
            isSoftKeyboardOpened = true;
            if ((activityRootView.getContext() instanceof  Activity)
                    && !isFullScreen((Activity) activityRootView.getContext())){
                notifyOnSoftKeyboardOpened(heightDiff-statusBarHeight);
            }else {
                notifyOnSoftKeyboardOpened(heightDiff);
            }

        } else if (isSoftKeyboardOpened && heightDiff < activityRootView.getRootView().getHeight()/4) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    public void setIsSoftKeyboardOpened(boolean isSoftKeyboardOpened) {
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
    }

    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }

    /**
     * Default value is zero {@code 0}.
     *
     * @return last saved keyboard height in px
     */
    public int getLastSoftKeyboardHeightInPx() {
        return lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.add(listener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSoftKeyboardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardOpened(keyboardHeightInPx);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardClosed();
            }
        }
    }

}