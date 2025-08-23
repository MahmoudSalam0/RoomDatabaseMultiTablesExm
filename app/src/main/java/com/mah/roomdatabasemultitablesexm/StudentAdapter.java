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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student current = students.get(position);


        holder.studentName.setText(current.getName());
        holder.studentBirthDate.setText(current.getBirthDate().toString());

        byte[] imageBytes = current.getPhoto();
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = Converter.byteAsBitMap(imageBytes);
            holder.studentImage.setImageBitmap(bitmap);
        } else {
            holder.studentImage.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName, studentBirthDate;
        private ImageView studentImage;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            studentBirthDate = itemView.findViewById(R.id.studentDate);
            studentImage = itemView.findViewById(R.id.studentImage);
        }
    }
}
