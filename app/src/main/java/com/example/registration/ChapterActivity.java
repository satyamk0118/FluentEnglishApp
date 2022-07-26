package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.registration.data.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChapterActivity extends AppCompatActivity {

    ArrayList<Course> listChapter=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        Intent intent = getIntent();
        String titleCourseName = intent.getStringExtra("course");
        int pos = intent.getIntExtra("pos", 0);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        TextView titleTextView = findViewById(R.id.textView);
        titleTextView.setText(titleCourseName);
        ListView listView2= findViewById(R.id.listView_chapter);

        try {
            JSONObject obj = JsonData.contentJson;
            JSONArray m_jArry = obj.getJSONArray("items");

            JSONObject currentObj  = m_jArry.getJSONObject(pos);

            JSONArray jsonArray = currentObj.getJSONArray("content");
            for(int i=0;i<jsonArray.length();i++)
            {
                String title = jsonArray.getJSONObject(i).getString("tag_line");
                String content = jsonArray.getJSONObject(i).getString("details");

                listChapter.add(new Course(R.drawable.chapterlast, title, content));
            }
            Log.v("Mera app", "yaha tak thik hai ");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("MEra Application ", "Error aaya hai ");
        }

        CourseAdapter chapterAdapter = new CourseAdapter(this, listChapter);
        listView2.setAdapter(chapterAdapter);


       listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(ChapterActivity.this, DetailActivity.class);
               intent.putExtra("courseName",listChapter.get(i).getCourseName());
               intent.putExtra("content", listChapter.get(i).getDetails());
               startActivity(intent);
           }
       });
    }
}