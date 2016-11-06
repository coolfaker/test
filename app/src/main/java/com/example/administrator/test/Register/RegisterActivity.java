package com.example.administrator.test.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.Register.Checked.CheckedView;
import com.example.administrator.test.Register.Checked.RegisterPresenter;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class RegisterActivity extends MvpActivity<CheckedView,RegisterPresenter> implements CheckedView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_checked)
    EditText etChecked;
    @BindView(R.id.button)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    private Unbinder bind;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        bind = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                   phone =etPhone.getText().toString().trim();
                if (phone.length()==11) {
                    getPresenter().checked(phone);
                }
            }
        });
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                button1.setText("重新发送");
                BmobSMS.requestSMSCode(getApplicationContext(), phone, "哈哈哈喝", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e==null){
                           showMessage("信息发送成功");
                        }
                    }
                });
                break;
            case R.id.button2:
                String checkednumber=etChecked.getText().toString().trim();
                BmobSMS.verifySmsCode(getApplicationContext(), phone, checkednumber, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){//短信验证码已验证
                           //TODO 显现注册按钮和界面
                        }else{
                            showMessage("请输入正确的验证码");
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void showMessage(String Msg) {
        Toast.makeText(getApplicationContext(),Msg,Toast.LENGTH_LONG).show();
    }


}
