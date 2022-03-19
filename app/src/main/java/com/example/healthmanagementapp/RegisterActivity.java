package com.example.healthmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.healthmanagementapp.service.User;
import com.example.healthmanagementapp.service.UserService;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText phone_num;
    EditText age;
    ImageButton btn_return;
    RadioGroup sex;
    EditText confirm;
    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String phone=phone_num.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String conf=confirm.getText().toString().trim();
                String agestr=age.getText().toString().trim();
                String sexstr=((RadioButton)RegisterActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
                Log.i("TAG",name+"_"+phone+"_"+pass+"_"+agestr+"_"+sexstr);
                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();


                //用户名或密码不能包含空格
                if (name.contains(" ") || pass.contains(" ")) {
                    System.out.println(name+"\n"+password);
                    Toast.makeText(RegisterActivity.this, "输入的用户名或密码不能包含空格", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.length()==0||name.length()==0){
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!conf.equals(pass)){
                    Toast.makeText(RegisterActivity.this, "两次密码不一致，请确认再注册", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(phone.length()!=11 || phone.contains(" ")){
                    Toast.makeText(RegisterActivity.this, "电话号码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println("name：" + name);
                //login()方法判断数据库中是否已经有该用户名
                if(uService.isUser(name)){
                    System.out.println(name+"\n"+password);
                    Toast.makeText(RegisterActivity.this, "该用户名已注册", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setUsername(name);
                user.setPhone(phone);
                user.setPassword(pass);
                user.setAge(Integer.parseInt(agestr));
                user.setSex(sexstr);
                uService.register(user);

                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.reg_username);
        password=(EditText) findViewById(R.id.reg_pwd);
        age=(EditText) findViewById(R.id.reg_age);
        phone_num=(EditText)findViewById(R.id.reg_phone);
        sex=(RadioGroup) findViewById(R.id.reg_sex);
        register=(Button) findViewById(R.id.btn_reg);
        confirm=(EditText)findViewById(R.id.confirm);
        btn_return=(ImageButton) findViewById(R.id.reg_returnbnt);
    }

}
