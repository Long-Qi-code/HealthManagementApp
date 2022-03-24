package com.example.healthmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;

public class IndexActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        /*--------------------获取id----------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        /*--------------------工具栏----------------*/
        setSupportActionBar(toolbar);
        /*--------------------导航抽屉菜单----------------*/

        //登录时显示个人信息和退出的item，隐藏login; 退出时个人信息和退出item被隐藏,显示login

        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);
//        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(false);

        //点击item时变色
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //默认启动时，导航栏选中首页
        navigationView.setCheckedItem(R.id.nav_index);
    }

    //导航按钮拖动打开
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            //默认选中首页
            case R.id.nav_index:
                break;
                //选中个人信息，跳转到个人信息页面
            case R.id.nav_profile:
                Intent intent = new Intent(IndexActivity.this,UsermsgActivity.class);
                startActivity(intent);
                break;
        }
        //选中某一个item将关闭导航栏
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
