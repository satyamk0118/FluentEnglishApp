package com.example.registration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {

    private Context mContext;
    private List<Course> courseList=new ArrayList<>();

    public CourseAdapter(@NonNull Context context, ArrayList<Course> courseList) {
        super(context, 0, courseList);
        this.mContext = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, parent, false);
        }
        Course currentCourse = courseList.get(position);

        ImageView imageView = listItem.findViewById(R.id.imageView);
        imageView.setImageResource(currentCourse.getImgResourceId());
        TextView textView = listItem.findViewById(R.id.textView_name);
        textView.setText(currentCourse.getCourseName());

        return listItem;
    }
}
