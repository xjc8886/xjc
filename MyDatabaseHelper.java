package com.example.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "shop.db" ;	// 数据库名称
    private static final int DATABASEVERSION = 1 ;
    private static final String TABLE_USERNAME = "userTable" ;

    public MyDatabaseHelper(Context context){
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlString="create table  "+
                TABLE_USERNAME+
                "( userId    integer primary key autoincrement," +
                " userName   varchar(255)," +
                " passWord   varchar(255),"+
                " state      int,"+
                " account    varchar(255))";
        sqLiteDatabase.execSQL(sqlString);

    }

    //生成用户账号
    private String makeAccount(){
        String temp;
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int mi=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);
        temp = Integer.toString(y) + Integer.toString(m) + Integer.toString(d) + Integer.toString(h) + Integer.toString(mi) + Integer.toString(s);
        return temp;
    }

    //注册
    public void Register(String userName,String passWord)
    {
        String account;
        int state=0;
        account=makeAccount();
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="insert into "+TABLE_USERNAME+"(userName,passWord,account,state) values (?,?,?,?)";
        //构造占位符的参数数组
        Object args[]=new Object[]{userName,passWord,account,state};
        db.execSQL(sqlString,args);//此方法执行的sql语句主要有创建、插入、修改、删除等
        db.close();
    }

    //状态置1
    public void SetOne(String userNameSet,int temp){

        String NAME=userNameSet;

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="update "+TABLE_USERNAME+" set "+" state = 1 "+" where userName='"+NAME+"'";
        String sqlString1="update "+TABLE_USERNAME+" set "+" state = 0 "+" where userName='"+NAME+"'";
        if(temp==1){
            db.execSQL(sqlString);
        }
        if(temp==0){
            db.execSQL(sqlString1);
        }



        db.close();
    }

    //查询账号的方法
    public Cursor find()
    {
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from userTable;";
        //执行查询，返回数据给游标
        Cursor result =db.rawQuery(sqlString,null);
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
