package kr.ac.yuhan.cs.admin;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class AdminScheduleActivity extends AppCompatActivity {

    private TextView selectedDateTextView;
    private EditText todoEditText;
    private TextView todoTextView;
    private CalendarView calendarView;
    private Button addTodoButton;

    // Map to store tasks for each date
    private Map<String, String> tasks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        selectedDateTextView = findViewById(R.id.selectedDateTextView);
        todoEditText = findViewById(R.id.todoEditText);
        todoTextView = findViewById(R.id.todoTextView);
        calendarView = findViewById(R.id.calendarView);
        addTodoButton = findViewById(R.id.addTodoButton);

        // CalendarView에서 날짜가 선택되었을 때 이벤트 처리
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 선택된 날짜를 TextView에 표시
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                selectedDateTextView.setText("선택된 날짜 : " + selectedDate);

                // 선택된 날짜에 해당하는 할일을 표시
                String task = tasks.get(selectedDate);
                if (task != null) {
                    todoTextView.setText("선택된 날짜의 할일 목록\n" + task + "\n");
                } else {
                    todoTextView.setText("선택된 날짜의 할일이 없습니다.");
                }
            }
        });

        // 할일 추가 버튼 클릭 이벤트 처리
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = selectedDateTextView.getText().toString().replace("Selected Date: ", "");
                String task = todoEditText.getText().toString();

                // 선택된 날짜에 해당하는 할일을 저장
                tasks.put(selectedDate, task);
                todoTextView.setText("Tasks for selected date: " + task);
                todoEditText.getText().clear();
            }
        });
    }

}
