package com.example.myshop;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class MyDatabaseHelperShopItem extends SQLiteOpenHelper {

    private static final String DATABASENAME = "shop.db" ;	// 数据库名称
    private static final int DATABASEVERSION = 1 ;
    private static final String TABLE_USERNAME = "shopTable" ;


    public MyDatabaseHelperShopItem(Context context){
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlString="create table "+
                TABLE_USERNAME+
                "(shopId     integer primary key autoincrement," +
                "shopName    varchar(255)," +
                "shopPrice   varchar(255),"+
                "shopFenLei  varchar(255),"+
                "shopInCart     int)";         //1,在购物车；0，不在购物车

        sqLiteDatabase.execSQL(sqlString);
        sqLiteDatabase.execSQL("create table  "+
                " userTable "+
                "( userId    integer primary key autoincrement," +
                " userName   varchar(255)," +
                " passWord   varchar(255),"+
                " state      int,"+
                " account    varchar(255))");

    }



    public void add(Goods s){

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="insert into "+TABLE_USERNAME+"(shopName,shopPrice,shopFenLei,shopInCart) values (?,?,?,?)";
        String name=s.getShopName();
        String price=s.getShopPrice();
        String kind=s.getShopFenLei();
        int InCart=s.getShopInCart();
        //构造占位符的参数数组
        Object args[]=new Object[]{name,price,kind,InCart};
        db.execSQL(sqlString,args);//此方法执行的sql语句主要有创建、插入、修改、删除等
        db.close();
    }


   public ArrayList<Goods> getAll(){
        ArrayList<Goods> array=new ArrayList();

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from shopTable;";
        //执行查询，返回数据给游标
        Cursor result =db.rawQuery(sqlString,null);

        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            String name=result.getString(result.getColumnIndexOrThrow("shopName"));
            String price=result.getString(result.getColumnIndexOrThrow("shopPrice"));
            String kind=result.getString(result.getColumnIndexOrThrow("shopFenLei"));
            int InCart=result.getInt(result.getColumnIndexOrThrow("shopInCart"));
            Goods s=new Goods(name,price,kind,InCart);
            array.add(s);
        }
        result.close();
        return array;
    }


    public ArrayList<Goods> getInCartAll(){
        ArrayList<Goods> array=new ArrayList();

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from shopTable;";
        //执行查询，返回数据给游标
        Cursor result =db.rawQuery(sqlString,null);

        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            String name=result.getString(result.getColumnIndexOrThrow("shopName"));
            String price=result.getString(result.getColumnIndexOrThrow("shopPrice"));
            String kind=result.getString(result.getColumnIndexOrThrow("shopFenLei"));
            int InCart=result.getInt(result.getColumnIndexOrThrow("shopInCart"));
            if(InCart==1){
                Goods s=new Goods(name,price,kind,InCart);
                array.add(s);
            }

        }
        result.close();
        return array;
    }

    public void addCart(String shopName,int state){
        String NAME=shopName;

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="update "+TABLE_USERNAME+" set "+" shopInCart = 1 "+" where shopName='"+NAME+"'";
        String sqlString1="update "+TABLE_USERNAME+" set "+" shopInCart = 0 "+" where shopName='"+NAME+"'";
        if(state==1){
            db.execSQL(sqlString);
        }
        if(state==0){
            db.execSQL(sqlString1);
        }
    }

    public void jieSuan(){
        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="update "+TABLE_USERNAME+" set "+" shopInCart = 0 ";
        db.execSQL(sqlString);
    }

    public ArrayList<Goods> search(String searchName){
        ArrayList<Goods> array=new ArrayList();

        SQLiteDatabase db=super.getWritableDatabase();
        String sqlString="select * from shopTable;";

        //执行查询，返回数据给游标
        Cursor result =db.rawQuery(sqlString,null);
        for (result.moveToFirst();!result.isAfterLast();result.moveToNext())
        {
            String name=result.getString(result.getColumnIndexOrThrow("shopName"));
            String price=result.getString(result.getColumnIndexOrThrow("shopPrice"));
            String kind=result.getString(result.getColumnIndexOrThrow("shopFenLei"));
            int InCart=result.getInt(result.getColumnIndexOrThrow("shopInCart"));
            Goods s=new Goods(name,price,kind,InCart);
            if(name.equals(searchName)){
                array.add(s);
            }

        }
        result.close();
        return array;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
