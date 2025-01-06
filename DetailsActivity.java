package com.example.myshop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    MyDatabaseHelperShopItem myDatabaseHelperShopItemDetail;

    private String name;

    private ImageView imageViewpic,imageViewBack;

    private TextView textViewName,textViewPrice,textViewAddCart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageViewpic=findViewById(R.id.shop_pic_detail);
        imageViewBack=findViewById(R.id.detail_back);
        textViewName=findViewById(R.id.detail_shop_name);
        textViewPrice=findViewById(R.id.detail_shop_price);
        textViewAddCart=findViewById(R.id.detail_add_to_cart);

        myDatabaseHelperShopItemDetail=new MyDatabaseHelperShopItem(DetailsActivity.this);
        name=getIntent().getStringExtra("name");
        ArrayList<Goods> array=myDatabaseHelperShopItemDetail.search(name);

        Goods detailShop=array.get(0);

        Drawable d= ContextCompat.getDrawable(DetailsActivity.this,detailShop.getPic());
        imageViewpic.setImageDrawable(d);
        textViewName.setText(detailShop.getShopName());
        textViewPrice.setText(detailShop.getShopPrice());

        onClick();

    }

    public void onClick(){
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelperShopItemDetail.addCart(textViewName.getText().toString(),1);
                Toast.makeText(DetailsActivity.this, "已将"+textViewName.getText().toString()+"加入购物车",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
