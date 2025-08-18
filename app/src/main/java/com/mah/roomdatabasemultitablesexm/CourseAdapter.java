package com.mah.roomdatabasemultitablesexm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course current = courses.get(position);

        holder.textCourseName.setText(current.getCourseName());
        holder.noOfStudent.setText(String.valueOf(current.getNoOfStudents()));


        // عرض الصورة
//        byte[] imageBytes = current.getImage();
//        if (imageBytes != null) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//            holder.imageCourse.setImageBitmap(bitmap);
//        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView textCourseName, noOfStudent;
        private ImageView imageCourse;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.textCourseName);
            noOfStudent = itemView.findViewById(R.id.noOfStudent);
            //imageCourse = itemView.findViewById(R.id.imageCourse);
        }
    }
}
