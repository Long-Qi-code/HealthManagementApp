package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthmanagementapp.service.UserService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    private void findViews() {
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
    }

//    实现登录
    public void btn_login_onclick(View view) {
        String name=username.getText().toString();
        System.out.println(name);
        String pass=password.getText().toString();
        System.out.println(pass);

        Log.i("TAG",name+"_"+pass);
        UserService uService=new UserService(LoginActivity.this);
        boolean flag=uService.login(name, pass);

        if(flag){
            Log.i("TAG","登录成功");
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
//            startActivity(intent);

            String userId = uService.getUserId(name, pass);

            //登录成功后，先让进程挂起三秒钟，然后再进行传值跳转，跳转到首页
            new Thread(){
                public  void run(){
                    super.run();
                    try {
                        Thread.sleep(3000);
                        //显式Intent，显式Intent就是直接以“类名称”来指定要启动哪一个Activity
                        //其中IndexActivity.class就是要指定启动的activity
                        Intent intent1=new Intent(LoginActivity.this,IndexActivity.class);
                        //添加传递的属性 id,name
                        intent1.putExtra("name",name);
                        intent1.putExtra("userid",userId);
                        //startActivity(Intent intent )显式启动新的Activity
                        startActivity(intent1);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }//输入的用户名或密码为空
        else if((TextUtils.isEmpty(name)||TextUtils.isEmpty(pass))||(TextUtils.isEmpty(name)&&TextUtils.isEmpty(pass))){
            Toast.makeText(LoginActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.i("TAG","用户名或密码错误");
            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
}
        }
//  跳转到 注册界面
    public void btn_rg_onclick(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        }
}

