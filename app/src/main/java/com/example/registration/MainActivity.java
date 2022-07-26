package com.example.registration;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registration.data.JsonData;
import com.example.registration.model.QuizItem;
import com.example.registration.utility.JsonUtilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    QuizFragment myQuiz = new QuizFragment();
    private ArrayList<String> listOfCourses= new ArrayList<>();

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
//        if(HomeFragment.listOfCourses.isEmpty())
//            HomeFragment.listOfCourses.add(new Course(R.mipmap.ic_dsa_course, "DSA"));
//        Log.d("My Image Resource", Integer.toString(R.mipmap.ic_dsa_course));
        try{
            JsonData.contentJson=new JSONObject(JsonUtilities.loadJSONFromAsset(getAssets().open("contents.json")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject obj = JsonData.contentJson;
            JSONArray m_jArry = obj.getJSONArray("items");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jsonObject = m_jArry.getJSONObject(i);
                String title= jsonObject.getString("title");
                listOfCourses.add(title);
            }
            Log.v("Mera app", "yaha tak thik hai ");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("MEra Application ", "Error aaya hai ");
        }
        for(String s:listOfCourses)
            HomeFragment.listOfCourses.add(new Course(R.drawable.chapter, s, ""));
        bottomNavigationView = findViewById(R.id.btmNvgView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

       bottomNavigationView.setOnItemSelectedListener(item -> {
           switch (item.getItemId()){
               case R.id.home:
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
                   return true;
               case R.id.quiz:
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myQuiz).commit();
                   return true;
               case R.id.profile:
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
                   return true;
           }
           return false;
       });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}