package com.mah.roomdatabasemultitablesexm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import android.widget.EditText;

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
    Button insert , insertSingle;
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

        insertSingle = findViewById(R.id.insertSingle);

        insertSingle.setOnClickListener(v -> {
            String[] options = {"Add Student", "Add Course"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("what do u want?");
            builder.setItems(options, (dialog, which) -> {
                MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

                if (which == 0) {
                    AlertDialog.Builder studentDialog = new AlertDialog.Builder(this);
                    studentDialog.setTitle("student Information");

                    EditText studentName = new EditText(this);
                    studentName.setHint("student Name");

                    EditText studentDept = new EditText(this);
                    studentDept.setHint("major");

                    LinearLayout layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.addView(studentName);
                    layout.addView(studentDept);

                    studentDialog.setView(layout);

                    studentDialog.setPositiveButton("Add", (d, i) -> {
                        if (studentName.getText().length() > 0 && studentDept.getText().length() > 0) {
                            Student student = new Student(
                                    studentName.getText().toString(),
                                    currentPhoto != null ? currentPhoto : new byte[0],
                                    new Date(),
                                    studentDept.getText().toString(),
                                    1
                            );
                            myViewModel.insertStudent(student);
                            currentPhoto = null;
                            Toast.makeText(this, " Student added", Toast.LENGTH_SHORT).show();
                        }
                    });
                    studentDialog.setNegativeButton("Cancel", null);
                    studentDialog.show();

                } else {
                    AlertDialog.Builder courseDialog = new AlertDialog.Builder(this);
                    courseDialog.setTitle("course details");

                    EditText courseName = new EditText(this);
                    courseName.setHint("Course Name");

                    EditText courseHours = new EditText(this);
                    courseHours.setHint("Hours?");

                    LinearLayout layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.addView(courseName);
                    layout.addView(courseHours);

                    courseDialog.setView(layout);

                    courseDialog.setPositiveButton("Add", (d, i) -> {
                        if (courseName.getText().length() > 0 && courseHours.getText().length() > 0) {
                            int hours = Integer.parseInt(courseHours.getText().toString());
                            Course course = new Course(courseName.getText().toString(), hours);
                            myViewModel.insertCourse(course);
                            Toast.makeText(this, "Course adeed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    courseDialog.setNegativeButton("Cancel", null);
                    courseDialog.show();
                }
            });
            builder.show();
        });



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
                Toast.makeText(this, "Photo Saved", Toast.LENGTH_SHORT).show();


                currentPhoto = null;
            } else {
                Toast.makeText(this, "Please Take Photoً", Toast.LENGTH_SHORT).show();
            }
        });

    }
}