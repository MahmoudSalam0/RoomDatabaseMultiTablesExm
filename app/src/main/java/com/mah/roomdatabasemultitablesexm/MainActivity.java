package com.mah.roomdatabasemultitablesexm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button camera;
    Button insert;
    private byte[] currentPhoto;

    RecyclerView recyclerCourses, recyclerStudents;
    CourseAdapter courseAdapter;
    StudentAdapter studentAdapter;


    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bundle extras = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data"); // الصورة المصغرة

                    // نحول Bitmap -> byte[] باستخدام Converter
                    currentPhoto = Converter.bitAsByteArray(bitmap);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerCourses = findViewById(R.id.recyclerCourses);
        recyclerStudents = findViewById(R.id.recyclerStudents);

        recyclerCourses.setLayoutManager(new LinearLayoutManager(this));
        recyclerStudents.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter();
        studentAdapter = new StudentAdapter();

        recyclerCourses.setAdapter(courseAdapter);
        recyclerStudents.setAdapter(studentAdapter);


        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getAllCourses().observe(this, courses -> {
            courseAdapter.setCourses(courses);
        });

        myViewModel.getAllStudent().observe(this, students -> {
            studentAdapter.setStudents(students);
        });

        camera=findViewById(R.id.camera);
        insert=findViewById(R.id.insert);

        camera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraActivityResultLauncher.launch(intent);
        });

        insert.setOnClickListener(v -> {

            myViewModel.insertCourse(new Course("Java",35));
            myViewModel.insertCourse(new Course("Game Development",25));

            if (currentPhoto != null) {
                Student student = new Student(
                        "Ali",
                        currentPhoto,
                        new Date(),
                        "CS",
                        1
                );
                myViewModel.insertStudent(student);
                Toast.makeText(this, "تم حفظ الطالب بالصورة ✅", Toast.LENGTH_SHORT).show();


                currentPhoto = null;
            } else {
                Toast.makeText(this, "الرجاء التقاط صورة أولاً", Toast.LENGTH_SHORT).show();
            }
        });

    }
}