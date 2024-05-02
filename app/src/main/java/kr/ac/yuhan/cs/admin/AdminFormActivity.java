package kr.ac.yuhan.cs.admin;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.yuhan.cs.admin.func.ChangeTextColor;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class AdminFormActivity extends AppCompatActivity {
    private NeumorphImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);
        LinearLayout adminFormPage = (LinearLayout) findViewById(R.id.adminFormPage);

        // 현재 mode값 받음
        int modeValue = getIntent().getIntExtra("mode", 1);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        NeumorphCardView adminAddCardView = (NeumorphCardView) findViewById(R.id.adminAddCardView);
        NeumorphCardView input_adminId = (NeumorphCardView) findViewById(R.id.input_adminId);
        NeumorphCardView input_adminPW = (NeumorphCardView) findViewById(R.id.input_adminPW);
        NeumorphCardView input_adminPosition = (NeumorphCardView) findViewById(R.id.input_adminPosition);
        NeumorphButton adminAddBtn = (NeumorphButton) findViewById(R.id.adminAddBtn);

        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);

        if(modeValue == 1) {
            ChangeMode.applySubTheme(adminFormPage, modeValue);

            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);
            ChangeMode.setDarkShadowCardView(adminAddCardView);
            ChangeMode.setDarkShadowCardView(input_adminId);
            ChangeMode.setDarkShadowCardView(input_adminPW);
            ChangeMode.setDarkShadowCardView(input_adminPosition);
            ChangeMode.setDarkShadowCardView(adminAddBtn);
        }
        else {
            adminAddBtn.setBackgroundColor(Color.rgb(0, 174, 142));
            ChangeMode.setLightShadowCardView(adminAddBtn);
        }
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
