package com.noahliu.nestedrecyclerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**製作資料*/
        List<MyData> data = makeData();
        /**設置RecyclerView*/
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //設置Adapter以及點擊回饋
        myAdapter = new MyAdapter(data, new MyAdapter.OnItemClick() {
            @Override
            public void onItemClick(MyData.NestedData data, MyData myData) {
                Toast.makeText(MainActivity.this
                        , "選擇了"+myData.getTitle()+"的 "+data.getNesTitle()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(myAdapter);

    }

    private List<MyData> makeData(){
        List<MyData> data = new ArrayList<>();
        String[] title = new String[]{"週一","週二","週三","週四","週五"};
        String[] nesTitle = new String[]{"清燉牛肉","番茄炒蛋","炸雞腿","糖醋魚","焗烤燉飯","爆炒蝦仁","紅燒鮭魚"};

        for (int i = 0; i < title.length; i++) {
            int r = (int)(Math.random()*7);
            List<MyData.NestedData> nesArray = new ArrayList<>();
            for (int j = 0; j < r+1; j++) {
                nesArray.add(new MyData.NestedData(nesTitle[(int)(Math.random()*7)]));
            }
            data.add(new MyData(title[i],nesArray));
        }
        return data;
    }
}