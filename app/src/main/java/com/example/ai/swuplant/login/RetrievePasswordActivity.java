package com.example.ai.swuplant.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.login.component.DrawableTextView;
import com.example.ai.swuplant.login.listener.KeyboardWatcher;
import com.example.ai.swuplant.net.bean.UpdatePasswordBackResult;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.ai.swuplant.net.utils.netUtil;
import com.example.ai.swuplant.utils.ToastUtils;
import com.example.customdialog.SweetAlertDialog;
import com.orhanobut.logger.Logger;

public class RetrievePasswordActivity extends FragmentActivity implements View.OnClickListener, KeyboardWatcher.SoftKeyboardStateListener {

    private DrawableTextView logo;
    private EditText et_mobile;
    private EditText et_new_password;
    private EditText et_affirm_new_password;
    private ImageView iv_clean_phone;
    private ImageView clean_new_password;
    private ImageView iv_show_new_pwd;
    private ImageView clean_affirm_new_password;
    private ImageView iv_show_affirm_new_password;
    private Button btn_submit;

    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例
    private View service, body;
    private KeyboardWatcher keyboardWatcher;

    private View root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        initView();
        initListener();

        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
    }



    private void initView() {
        logo = (DrawableTextView) findViewById(R.id.logo);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        et_affirm_new_password = (EditText) findViewById(R.id.et_affirm_new_password);
        iv_clean_phone = (ImageView) findViewById(R.id.iv_clean_phone);
        clean_new_password = (ImageView) findViewById(R.id.clean_new_password);
        iv_show_new_pwd = (ImageView) findViewById(R.id.iv_show_new_pwd);
        clean_affirm_new_password = (ImageView) findViewById(R.id.clean_affirm_new_password);
        iv_show_affirm_new_password = (ImageView) findViewById(R.id.iv_show_affirm_new_pwd);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        service = findViewById(R.id.service);
        body = findViewById(R.id.body);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        root = findViewById(R.id.root);
        findViewById(R.id.close).setOnClickListener(this);

    }

    private void initListener() {
        iv_clean_phone.setOnClickListener(this);
        clean_new_password.setOnClickListener(this);
        iv_show_new_pwd.setOnClickListener(this);
        clean_affirm_new_password.setOnClickListener(this);
        iv_show_affirm_new_password.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && iv_clean_phone.getVisibility() == View.GONE) {
                    iv_clean_phone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    iv_clean_phone.setVisibility(View.GONE);
                }
            }
        });
        et_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && clean_new_password.getVisibility() == View.GONE) {
                    clean_new_password.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    clean_new_password.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    ToastUtils.showToast(RetrievePasswordActivity.this,getResources().getString(R.string.please_input_limit_pwd));
                    s.delete(temp.length() - 1, temp.length());
                    et_new_password.setSelection(s.length());
                }
            }
        });
        et_affirm_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && clean_affirm_new_password.getVisibility() == View.GONE) {
                    clean_affirm_new_password.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    clean_affirm_new_password.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    ToastUtils.showToast(RetrievePasswordActivity.this,getResources().getString(R.string.please_input_limit_pwd));
                    s.delete(temp.length() - 1, temp.length());
                    et_affirm_new_password.setSelection(s.length());
                }
            }
        });

    }

    /**
     * 缩小
     *
     * @param view
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);

        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }

    /**
     * f放大
     *
     * @param view
     */
    public void zoomOut(final View view) {
        if (view.getTranslationY()==0){
            return;
        }
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();

    }
    private boolean flag_new_password = false;
    private boolean flag_affirm_new_password = false;

    private SweetAlertDialog loadingDialog = null;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_clean_phone:
                et_mobile.setText("");
                break;
            case R.id.clean_new_password:
                et_new_password.setText("");
                break;
            case R.id.close:
                finish();
                overridePendingTransition(0,R.anim.activity_slide_out_down);
                break;
            case R.id.iv_show_new_pwd:
                if(flag_new_password == true){
                    et_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    iv_show_new_pwd.setImageResource(R.drawable.pass_gone);
                    flag_new_password = false;
                }else{
                    et_new_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    iv_show_new_pwd.setImageResource(R.drawable.pass_visuable);
                    flag_new_password = true;
                }
                String pwd = et_new_password.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    et_new_password.setSelection(pwd.length());
                break;
            case R.id.clean_affirm_new_password:
                et_affirm_new_password.setText("");
                break;
            case R.id.iv_show_affirm_new_pwd:
                if(flag_affirm_new_password == true){
                    et_affirm_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    iv_show_affirm_new_password.setImageResource(R.drawable.pass_gone);
                    flag_affirm_new_password = false;
                }else{
                    et_affirm_new_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    iv_show_affirm_new_password.setImageResource(R.drawable.pass_visuable);
                    flag_affirm_new_password = true;
                }
                String password = et_affirm_new_password.getText().toString();
                if (!TextUtils.isEmpty(password))
                    et_new_password.setSelection(password.length());
                break;
            case R.id.btn_submit:
                if (netUtil.isNetworkAvailable(this)){
                    String username = et_mobile.getText().toString();
                    String newPassword = et_new_password.getText().toString();
                    String affirmPassword = et_affirm_new_password.getText().toString();
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(affirmPassword)){
                        ToastUtils.showToast(this,"用户名或密码为空");
                        return;
                    }else if (!newPassword.equals(affirmPassword)){
                        ToastUtils.showToast(this,"两次输入密码不一致");
                        return;
                    }
                    loadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Loading");
                    loadingDialog.show();
                    loadingDialog.setCancelable(false);
                    updateUserPassword(username,newPassword);
                }else {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("糟糕...")
                            .setContentText("网络无连接！")
                            .setCancelText("取消")
                            .setConfirmText("设置网络")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                                }
                            })
                            .show();
                }
                break;
        }
    }



    public void updateUserPassword(String username,String newPassword){
        ApiServiceExecutor.getInstance().updateUserPassword(username, newPassword, new HttpCallBack() {
            @Override
            public void onSuccess(Object response) {
                if (response != null){
                    UpdatePasswordBackResult result = (UpdatePasswordBackResult) response;
                    resultCallBack(result);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                Logger.d("TAG", "onFailure: "+e.getMessage()+" "+e.getLocalizedMessage()+" "+e.toString());
                e.printStackTrace();
                loadingDialog.setTitleText("糟糕...")
                        .setContentText("出现异常错误!")
                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
            }
        });
    }

    private void resultCallBack(UpdatePasswordBackResult result){
        if (result.getCode() == 404){
            loadingDialog.setTitleText("遗憾...")
                    .setContentText("该账号不存在！")
                    .setConfirmText("确定")
                    .setCancelText("取消")
                    .changeAlertType(SweetAlertDialog.WARNING_TYPE);
        }else if (result.getCode() == 200){
            loadingDialog.setTitleText("修改成功！")
                    .setConfirmText("去登陆")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            loadingDialog.dismiss();
                            finish();
                            overridePendingTransition(0,R.anim.activity_slide_out_down);
                        }
                    })
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        }else if (result.getCode() == 300){
            loadingDialog.setTitleText("糟糕...")
                    .setContentText("密码修改失败!")
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }else if (result.getCode() == 500){
            loadingDialog.setTitleText("糟糕...")
                    .setContentText("出现异常错误!")
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        keyboardWatcher.removeSoftKeyboardStateListener(this);
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        Log.e("wenzhihao", "----->show" + keyboardSize);
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        Log.e("wenzhihao","y = "+y+",x="+x);
        int bottom = screenHeight - (y+body.getHeight()) ;
        Log.e("wenzhihao","bottom = "+bottom);
        Log.e("wenzhihao","con-h = "+body.getHeight());
        if (keyboardSize > bottom){
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            zoomIn(logo, keyboardSize - bottom);

        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        Log.e("wenzhihao", "----->hide");
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        zoomOut(logo);
    }

}
