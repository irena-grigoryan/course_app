package com.itproger.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itproger.e_commerce.models.Course;
import com.itproger.e_commerce.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.cart);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        ListView ordersList = findViewById(R.id.ordersList);

        List<String> coursesTitle = new ArrayList<>();
        for (Course c : MainActivity.fullCoursesList){
            if(Order.itemsId.contains(c.getId()))
                coursesTitle.add(c.getTitle());
        }

        ordersList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursesTitle));
    }

    public void orderNow(View view){
        ListView ordersList = findViewById(R.id.ordersList);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) ordersList.getAdapter();
        adapter.clear();
        Order.itemsId.clear();
        adapter.notifyDataSetChanged();

    }
}