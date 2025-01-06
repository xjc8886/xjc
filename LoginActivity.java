package com.example.myshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkBoxRemenber;
    private Button buttonToApp, buttonToRegister;
    private EditText editTextUserName,editTextPwd;
    private ImageView imageViewTiaoGuo;

    private MyDatabaseHelper myDatabaseHelper;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        init();

        buttonToApp.setOnClickListener(LoginActivity.this);
        buttonToRegister.setOnClickListener(LoginActivity.this);
        imageViewTiaoGuo.setOnClickListener(LoginActivity.this);

        myDatabaseHelper=new MyDatabaseHelper(this);
        myDatabaseHelper.getWritableDatabase();//只读
    }

    public void findView(){
        checkBoxRemenber=findViewById(R.id.remenbre_user);
        buttonToApp=findViewById(R.id.login);
        buttonToRegister =findViewById(R.id.register);
        editTextUserName=findViewById(R.id.UserName);
        editTextPwd=findViewById(R.id.Pwd);
        imageViewTiaoGuo=findViewById(R.id.lg_img_tiaoguo);


        checkBoxRemenber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences=getSharedPreferences("remenberuser", Context.MODE_PRIVATE);

                if(b){
                    String from_re_name=sharedPreferences.getString("name","0");
                    String from_re_pwd=sharedPreferences.getString("pwd","0");
                    if(!from_re_name.equals("0")){
                        editTextUserName.setText(from_re_name);
                        editTextPwd.setText(from_re_pwd);
                    }

                }

            }
        });
    }

    public void init(){
        sharedPreferences=getSharedPreferences("remenberuser", Context.MODE_PRIVATE);
        String from_re_name=sharedPreferences.getString("name","0");
        if(!from_re_name.equals("0")){
            checkBoxRemenber.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        String pwdEmpty=editTextPwd.getText().toString();
        if( id == R.id.login){
            String nameFromUser=editTextUserName.getText().toString();
            String pwdFromUser=editTextPwd.getText().toString();
            Cursor result=myDatabaseHelper.find();
            int a=0,b=0;
            for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
            {
                String nameFromData=result.getString(result.getColumnIndexOrThrow("userName"));
                String pwdFromData=result.getString(result.getColumnIndexOrThrow("passWord"));
                if (nameFromUser.equals(nameFromData) && pwdFromUser.equals(pwdFromData))
                    a=1;
                if (nameFromUser.equals(nameFromData))
                    b=1;
            }
            if (b==1)
            {
                if (!nameFromUser.equals(null) && !pwdFromUser.equals(null))
                {
                    if(a==1)
                    {
                        myDatabaseHelper.SetOne(nameFromUser,1);
                        sharedPreferences=getSharedPreferences("remenberuser", Context.MODE_PRIVATE);
                        //获取Editor对象的引用
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if(!checkBoxRemenber.isChecked()){

                            editor.clear();
                            editor.commit();
                        }
                        if(checkBoxRemenber.isChecked()){
                            String re_name = editTextUserName.getText().toString();
                            String re_pwd = editTextPwd.getText().toString();

                            editor.putString("name", re_name);
                            editor.putString("pwd", re_pwd);
                            editor.commit();
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"密码错误！", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            }else if(pwdEmpty.equals(null)){
                Toast.makeText(getApplicationContext(),"密码不能为空！", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(LoginActivity.this, "账号不存在！", Toast.LENGTH_SHORT).show();
            //关闭游标
            result.close();


        }else if( id == R.id.register){
            Intent it=new Intent(LoginActivity.this,LogonActivity.class);
            startActivity(it);
            finish();
        }else if( id == R.id.lg_img_tiaoguo){
            Intent it=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(it);
            finish();
        }
    }

    /*@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Intent it2=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(it2);
        finish();
        return super.onKeyUp(keyCode, event);
    }*/
}
