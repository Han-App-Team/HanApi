package com.example.hanapitest.utils;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hanapitest.R;

import java.lang.ref.WeakReference;
import java.util.Objects;


@SuppressWarnings("unused")
public class SimpleDialog extends AppCompatDialogFragment {

    private static final String NONE = "\b\b";
    private static WeakReference<SimpleDialog> sDialogRef;
    private OnBtnClickedListener mOnRightClickedListener;
    private OnBtnClickedListener mOnLeftClickedListener;
    private OnCustomAction mOnCustomAction;
    private OnDismissListener mOnDismissListener;

    /**
     * 创建实例
     *
     * @param title 标题, 0表示无
     * @param msg   内容, 0表示无
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public static SimpleDialog newInstance(@StringRes int title, @StringRes int msg) {
        SimpleDialog dialog = new SimpleDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("msg", msg);
        dialog.setArguments(args);
        return dialog;
    }

    /**
     * 创建实例
     *
     * @param title 标题, null表示无
     * @param msg   内容, null表示无
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public static SimpleDialog newInstance(String title, String msg) {
        SimpleDialog dialog = new SimpleDialog();
        Bundle args = new Bundle();
        args.putString("title_str", title == null ? NONE : title);
        args.putString("msg_str", msg == null ? NONE : msg);
        dialog.setArguments(args);
        return dialog;
    }

    /**
     * 取消显示
     */
    public static void cancel() {
        if (sDialogRef != null && sDialogRef.get() != null) {
            sDialogRef.get().dismiss();
        }
    }

    /**
     * 设置自定义内容
     *
     * @param contentRes 自定义内容, 0表示无
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setCustomView(@LayoutRes int contentRes) {
        Bundle args = getArguments();
        Objects.requireNonNull(args).putInt("contentRes", contentRes);
        return this;
    }

    /**
     * 设置左按钮文字
     *
     * @param btnLeft 左侧按钮, 0表示默认, -1表示无
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setBtnLeft(@StringRes int btnLeft) {
        Bundle args = getArguments();
        Objects.requireNonNull(args).putInt("btnLeft", btnLeft);
        return this;
    }

    /**
     * 设置右按钮文字
     *
     * @param btnRight 右侧按钮, 0表示默认, -1表示无
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setBtnRight(@StringRes int btnRight) {
        Bundle args = getArguments();
        Objects.requireNonNull(args).putInt("btnRight", btnRight);
        return this;
    }

    /**
     * 是否可以点击外侧取消, 默认true
     *
     * @param cancelable 是否可以点击外侧取消
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setCancelableOutside(boolean cancelable) {
        Bundle args = getArguments();
        Objects.requireNonNull(args).putBoolean("cancelable", cancelable);
        return this;
    }

    /**
     * 是否自动退出dialog, 默认: 是
     */
    public SimpleDialog setAutoDismiss(boolean autoDismiss) {
        Bundle args = getArguments();
        Objects.requireNonNull(args).putBoolean("autoDismiss", autoDismiss);
        return this;
    }

    /**
     * 设置左侧按钮点击事件
     *
     * @param onBtnClickedListener 左侧按钮点击事件
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setBtnLeftClickedListener(OnBtnClickedListener onBtnClickedListener) {
        this.mOnLeftClickedListener = onBtnClickedListener;
        return this;
    }

    /**
     * 设置右侧按钮点击事件
     *
     * @param onBtnClickedListener 右侧按钮点击事件
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setBtnRightClickedListener(OnBtnClickedListener onBtnClickedListener) {
        this.mOnRightClickedListener = onBtnClickedListener;
        return this;
    }

    /**
     * 设置自定义内容的相关初始化
     *
     * @param onCustomAction 自定义内容的相关代码
     * @return dialog 实例, 调用 show 方法即可显示
     */
    public SimpleDialog setCustomAction(OnCustomAction onCustomAction) {
        this.mOnCustomAction = onCustomAction;
        return this;
    }

    /**
     * 设置消失监听
     */
    public SimpleDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sDialogRef = new WeakReference<>(this);

        View view = inflater.inflate(R.layout.dialog_simple, container, false);
        final TextView tvTitle = view.findViewById(R.id.title);
        final TextView tvMessage = view.findViewById(R.id.message);
        final Button btn1 = view.findViewById(R.id.button1);
        final Button btn2 = view.findViewById(R.id.button2);

        final Resources resources = this.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;
        view.setLayoutParams(new LinearLayout.LayoutParams((int) (
                dm.widthPixels * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT));

        final Bundle args = getArguments();
        assert args != null;
        final int titleRes = args.getInt("title");
        final int msgRes = args.getInt("msg");
        final String titleStr = args.getString("title_str", "\b");
        final String msgStr = args.getString("msg_str");
        final int contentRes = args.getInt("contentRes");
        final int btn1Res = args.getInt("btnLeft");
        final int btn2Res = args.getInt("btnRight");
        final boolean cancelable = args.getBoolean("cancelable", true);
        final boolean autoDismiss = args.getBoolean("autoDismiss", true);

        if ("\b".equals(titleStr)) {
            setTitle(tvTitle, titleRes);
            setMessage(tvMessage, msgRes);
        } else {
            setTitle(tvTitle, titleStr);
            setMessage(tvMessage, msgStr);
        }
        setCustomContent(view, contentRes);
        setBtn1Action(view, btn1, btn1Res);
        setBtn2Action(view, btn2, btn2Res, autoDismiss);

        if (mOnCustomAction != null) {
            mOnCustomAction.onAction(this, view);
        }
        setCancelable(cancelable);

        //noinspection ConstantConditions
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog, this.getView());
        }
        super.onDismiss(dialog);
    }

    private void setTitle(TextView tv, int title) {
        tv.setVisibility(title > 0 ? View.VISIBLE : View.GONE);
        if (title > 0) {
            tv.setText(title);
            tv.post(() -> {
                if (tv.getLineCount() > 1) {
                    tv.setGravity(Gravity.START);
                } else {
                    tv.setGravity(Gravity.CENTER);
                }
            });
        }
    }

    private void setMessage(TextView tv, int message) {
        tv.setVisibility(message > 0 ? View.VISIBLE : View.GONE);
        if (message > 0) {
            tv.setText(message);
            tv.post(() -> {
                if (tv.getLineCount() > 1) {
                    tv.setGravity(Gravity.START);
                } else {
                    tv.setGravity(Gravity.CENTER);
                }
            });
        }
    }

    private void setTitle(TextView tv, String title) {
        tv.setVisibility(title == null || title.equals(NONE) ? View.GONE : View.VISIBLE);
        if (title != null) {
            tv.setText(title);
            tv.post(() -> {
                if (tv.getLineCount() > 1) {
                    tv.setGravity(Gravity.START);
                } else {
                    tv.setGravity(Gravity.CENTER);
                }
            });
        }
    }

    private void setMessage(TextView tv, String message) {
        tv.setVisibility(message == null || message.equals(NONE) ? View.GONE : View.VISIBLE);
        if (message != null) {
            tv.setText(message);
            tv.post(() -> {
                if (tv.getLineCount() > 1) {
                    tv.setGravity(Gravity.START);
                } else {
                    tv.setGravity(Gravity.CENTER);
                }
            });
        }
    }

    private void setCustomContent(View view, int contentRes) {
        if (contentRes > 0) {
            FrameLayout flContent = view.findViewById(R.id.fl_content);
            View customContent = getLayoutInflater().inflate(contentRes, flContent, false);
            flContent.addView(customContent);
        }
    }

    private void setBtn1Action(View view, Button btn1, int textRes) {
        btn1.setVisibility(textRes > -1 ? View.VISIBLE : View.GONE);
        if (textRes > -1) {
            btn1.setText(textRes > 0 ? textRes : R.string.cancel);
            btn1.setOnClickListener(v -> {
                if (mOnLeftClickedListener != null) {
                    mOnLeftClickedListener.onClicked(this, view);
                }
                dismiss();
            });
        }
    }

    private void setBtn2Action(View view, Button btn2, int textRes, boolean autoDismiss) {
        btn2.setVisibility(textRes > -1 ? View.VISIBLE : View.GONE);
        if (textRes > -1) {
            btn2.setText(textRes > 0 ? textRes : R.string.confirm);
            btn2.setOnClickListener(v -> {
                if (mOnRightClickedListener != null) {
                    mOnRightClickedListener.onClicked(this, view);
                }
                if (autoDismiss) {
                    dismiss();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        mOnCustomAction = null;
        mOnLeftClickedListener = null;
        mOnRightClickedListener = null;
        if (sDialogRef != null) {
            sDialogRef.clear();
            sDialogRef = null;
        }
        super.onDestroy();
    }

    /**
     * dialog 按钮点击回调
     */
    public interface OnBtnClickedListener {
        /**
         * 按钮点击
         */
        void onClicked(SimpleDialog dialog, View view);
    }

    /**
     * 自定义回调
     */
    public interface OnCustomAction {
        /**
         * 自定义动作
         */
        void onAction(SimpleDialog dialog, View view);
    }

    /**
     * dialog 消失监听回调
     */
    public interface OnDismissListener {
        /**
         * dialog 消失
         */
        void onDismiss(DialogInterface dialog, View view);
    }
}