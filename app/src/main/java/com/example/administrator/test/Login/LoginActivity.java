package com.example.administrator.test.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.HomeActivity;
import com.example.administrator.test.R;
import com.example.administrator.test.Register.RegisterActivity;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends MvpActivity<LoginView,LoginPresent>implements LoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_et)
    EditText loginEt;
    @BindView(R.id.et_password)
    EditText etPassword;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        bind = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.return1);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @NonNull
    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @OnClick({R.id.login_button, R.id.tv_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                String phone=loginEt.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                getPresenter().login(new User(phone,password));
                break;
            case R.id.tv_regist:
               register(RegisterActivity.class);
               Toast.makeText(LoginActivity.this,"项目",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void register(Class<? extends Activity> clazz) {

    if (LoginActivity.this == null) return;
    Intent intent = new Intent(LoginActivity.this, clazz);
        LoginActivity.this.startActivity(intent);
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

    @Override
    public void intentView() {
        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
