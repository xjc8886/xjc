package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    private long firstTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        bottomNavigation=findViewById(R.id.bottom_navigation);
        //获取navController
        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment_container);

        //通过setupWithNavController将底部导航和导航控制器进行绑定
        NavigationUI.setupWithNavController(bottomNavigation,navController);


    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }



        return super.onKeyUp(keyCode, event);
    }



    public void init(){
        MyDatabaseHelperShopItem db=new MyDatabaseHelperShopItem(this);

        Goods s=new Goods("笔记本","15","文具",0);
        db.add(s);
        s=new Goods("羽绒服","310","服装",0);
        db.add(s);
        s=new Goods("计算器","23","文具",0);
        db.add(s);
        s=new Goods("拖鞋","42","鞋袜",0);
        db.add(s);
        s=new Goods("竹蜻蜓","8","玩具",0);
        db.add(s);
        s=new Goods("橡皮","1","文具",0);
        db.add(s);
        s=new Goods("圆珠笔","5","文具",0);
        db.add(s);
        s=new Goods("长袜","25","鞋袜",0);
        db.add(s);
        s=new Goods("小象公仔","130","玩具",0);
       db.add(s);
       db.close();
   }
}