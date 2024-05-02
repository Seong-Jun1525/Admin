package kr.ac.yuhan.cs.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.yuhan.cs.admin.func.ChangeTextColor;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class SettingActivity extends AppCompatActivity {
    private NeumorphImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        LinearLayout settingPage = (LinearLayout) findViewById(R.id.settingPage);

        // 현재 mode값 받음
        int modeValue = getIntent().getIntExtra("mode", 1);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        NeumorphCardView adminIDCardView = (NeumorphCardView) findViewById(R.id.adminIDCardView);
        NeumorphCardView addAdminCardView = (NeumorphCardView) findViewById(R.id.addAdminCardView);
        NeumorphCardView adminExitCardView = (NeumorphCardView) findViewById(R.id.adminExitCardView);

        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);

        if(modeValue == 1) {
            // 폰트 색상 변경
            ChangeTextColor.changeDarkTextColor(settingPage, Color.WHITE);

            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);
            ChangeMode.setDarkShadowCardView(adminIDCardView);
            ChangeMode.setDarkShadowCardView(addAdminCardView);
            ChangeMode.setDarkShadowCardView(adminExitCardView);
        }

        adminIDCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CardView를 클릭했을 때 실행할 코드를 여기에 작성합니다.
                // 알림창을 생성하고 설정합니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("관리자 정보"); // 알림창 제목 설정
                builder.setMessage("관리자 ID: [여기에 관리자 ID 입력]\n관리자 직책: [여기에 관리자 직책 입력]"); // 알림창 내용 설정

                // 확인 버튼 추가 및 클릭 이벤트 설정
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // 알림창 닫기
                    }
                });
                // 알림창 보이기
                builder.show();
            }
        });
        addAdminCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setting 페이지로 이동 및 메인페이지 배경색상 전달
                Intent intent = new Intent(getApplicationContext(), AdminFormActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", modeValue);
                startActivity(intent);
            }
        });
        adminExitCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CardView를 클릭했을 때 실행할 코드를 여기에 작성합니다.
                Toast.makeText(SettingActivity.this, "adminExitCardView가 클릭되었습니다!", Toast.LENGTH_SHORT).show();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                backBtn.setShapeType(1);
                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backBtn.setShapeType(0);
                    }
                }, 200);
                finish();
            }
        });
    }
}
