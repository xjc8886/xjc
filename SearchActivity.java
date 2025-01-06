package com.example.myshop;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myshop.adpter.ShopAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    public static EditText editTextSearchContent;
    ListView listViewSearch;
    ImageView imageViewDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearchContent=findViewById(R.id.content);
        listViewSearch=findViewById(R.id.list_view_search);
        imageViewDo=findViewById(R.id.search_do);

        String s=editTextSearchContent.getText().toString();


        imageViewDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelperShopItem db=new MyDatabaseHelperShopItem(SearchActivity.this);
                ArrayList<Goods> array=db.search(editTextSearchContent.getText().toString());

                if(array.size()==0){
                    Toast.makeText(SearchActivity.this, "未搜索到该商品",Toast.LENGTH_SHORT).show();
                }
                if(array.size()!=0){
                    ShopAdapter adapter=new ShopAdapter(SearchActivity.this,array);
                    listViewSearch.setAdapter(adapter);
                }
            }
        });




    }

}
