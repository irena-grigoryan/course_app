package com.itproger.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.itproger.e_commerce.adapters.CategoryAdapter;
import com.itproger.e_commerce.adapters.CourseAdapter;
import com.itproger.e_commerce.models.Category;
import com.itproger.e_commerce.models.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), OrderPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });


        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Games"));
        categoryList.add(new Category(2, "WebSites"));
        categoryList.add(new Category(3, "Languages"));
        categoryList.add(new Category(4, "Other"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1, 3, "java", "Profession Java\n developer", "1 june", "beginner", "#424345", "For the program, you will learn how to build graphical applications for a PC, develop websites based on Java Spring Boot, learn how to build full-fledged Android applications and learn the Java language itself!"));
        courseList.add(new Course(2, 3, "python", "Profession Python\n developer","5 june","beginner", "#9FA52D", "Discover the power of Python programming with our comprehensive course. Learn the fundamentals, write clean and efficient code, and build practical applications. Perfect for beginners. Enroll now and embark on your journey to becoming a skilled Python developer!"));
        courseList.add(new Course(3, 1,"c_sharp", "Profession C#\n developer","2 june","medium", "#332E2F", "Master C# programming essentials and build practical applications with our comprehensive course. Learn key concepts, write efficient code, and gain hands-on experience through interactive exercises. Start your journey to becoming a skilled C# developer today!"));
        courseList.add(new Course(4, 2, "front_end", "Profession Front-End\n developer","10 june","beginner", "#B63827", "Unlock the world of front-end development with our course. Learn HTML, CSS, and JavaScript to create stunning and interactive websites. Gain practical skills through hands-on projects and master the fundamentals of web design.  Get started on your front-end journey now!"));
        courseList.add(new Course(5, 2, "back_end", "Profession Back-End\n developer","16 june","beginner", "#1D4AA2", "Master back-end development with our course. Learn server-side languages and frameworks, build powerful web applications, handle data storage, and implement secure authentication. Ideal for aspiring back-end developers. Enroll now and level up your coding skills!"));
        courseList.add(new Course(6, 2,"full_stack", "Profession Full Stack\n developer","8 june","medium", "#FEB600", "Unlock full stack development with our course. Learn front-end, back-end, and everything in between. Master essential programming languages, frameworks, and build complete web applications. Perfect for aspiring full stack developers. Enroll now and expand your coding horizons!"));

        fullCoursesList.addAll(courseList);

        setCourseRecycler(courseList);
    }

    public void showAllCategories(View view){
        courseList.clear();
        courseList.addAll(fullCoursesList);
        courseAdapter.notifyDataSetChanged();
    }

    public void openShoppingCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCoursesList);

        List<Course> filterCourses = new ArrayList<>();

        for (Course c : courseList){
            if(c.getCategory() == category)
                filterCourses.add(c);
        }
        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();
    }

}