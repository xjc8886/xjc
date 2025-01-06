package com.example.myshop;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogonActivity extends AppCompatActivity implements View.OnClickListener {

    private Button butonLogon,butonBack;
    private EditText editTextUserName,editTextPwd1,editTextPwd2;

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        butonLogon=findViewById(R.id.register_but);
        butonBack=findViewById(R.id.back);

        editTextUserName=findViewById(R.id.register_name);
        editTextPwd1=findViewById(R.id.register_pwd);
        editTextPwd2=findViewById(R.id.register_pwd2);

        myDatabaseHelper=new MyDatabaseHelper(this);

        butonLogon.setOnClickListener(LogonActivity.this);
        butonBack.setOnClickListener(LogonActivity.this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if( id == R.id.register_but){
            String nameInput=editTextUserName.getText().toString();
            String pwdInput1=editTextPwd1.getText().toString();
            String pwdInput2=editTextPwd2.getText().toString();
            Cursor result= myDatabaseHelper.find();
            int a=0;
            if(!nameInput.equals(null)){
                if(nameInput.length()<6){
                    Toast.makeText(LogonActivity.this, "账号的长度不能少于6位", Toast.LENGTH_SHORT).show();
                }else if(pwdInput1.length()<6){
                    Toast.makeText(LogonActivity.this, "密码的长度不能少于6位", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
                    {
                        //判断数据库是否存在此对象
                        String accountName = result.getString(result.getColumnIndexOrThrow("userName"));
                        if(nameInput.equals(accountName))
                        {
                            a=1;
                        }
                    }
                    result.close();
                    if(!pwdInput1.equals(null) && !pwdInput2.equals(null))
                    {
                        if ((pwdInput1.equals(pwdInput2) && a==0))
                        {
                            myDatabaseHelper.Register(nameInput,pwdInput1);
                            Intent intent = new Intent(LogonActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LogonActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                        }
                        else if (a==1)
                            Toast.makeText(getApplicationContext(),"账号已存在,请重新输入!",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),"两次输入的密码不一致!",Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(),"密码不能为空！",Toast.LENGTH_SHORT).show();
                }

            }

        }else if( id == R.id.back) {
            Intent it=new Intent(LogonActivity.this,LoginActivity.class);
            startActivity(it);
            finish();
        }
    }
}
