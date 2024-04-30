package kr.ac.yuhan.cs.admin.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import kr.ac.yuhan.cs.admin.MainActivity;
import kr.ac.yuhan.cs.admin.R;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;

public class LoginActivity extends AppCompatActivity {

    private NeumorphButton loginBtn;
    private NeumorphImageView backBtn;
    private EditText input_id;
    private EditText input_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);
        System.out.println(backgroundColor);
        if(backgroundColor == -10395295) {
            // 새 이미지로 바꿔주세요.
            Drawable darkIdImage = getResources().getDrawable(R.drawable.user);
            darkIdImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            Drawable darkPwImage = getResources().getDrawable(R.drawable.lock);
            darkPwImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

            backBtn = (NeumorphImageView) findViewById(R.id.backBtn);
            backBtn.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

            input_id = (EditText) findViewById(R.id.input_id);
            input_id.setCompoundDrawablesWithIntrinsicBounds(darkIdImage, null, null, null);

            input_pw = (EditText) findViewById(R.id.input_pw);
            input_pw.setCompoundDrawablesWithIntrinsicBounds(darkPwImage, null, null, null);
        }

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                loginBtn.setShapeType(1);
                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loginBtn.setShapeType(0);
                    }
                }, 200);

                Toast tMsg = Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT);
                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                        .getDefaultDisplay();
                int xOffset = (int) (Math.random() * display.getWidth()); // x좌표
                int yOffset = (int) (Math.random() * display.getHeight()); // y좌표
                tMsg.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                tMsg.show();

                finish();
            }
        });

        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);
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