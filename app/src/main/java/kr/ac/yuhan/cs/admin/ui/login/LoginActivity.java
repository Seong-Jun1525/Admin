package kr.ac.yuhan.cs.admin.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import kr.ac.yuhan.cs.admin.R;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class LoginActivity extends AppCompatActivity {
    private NeumorphButton loginBtn;
    private NeumorphImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LinearLayout loginPage = (LinearLayout) findViewById(R.id.loginPage);

        // 현재 mode값 받음
        int modeValue = getIntent().getIntExtra("mode", 1);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        Drawable darkIdImage = getResources().getDrawable(R.drawable.user);
        Drawable darkPwImage = getResources().getDrawable(R.drawable.lock);

        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);
        loginBtn = (NeumorphButton) findViewById(R.id.loginBtn);

        NeumorphCardView loginCardView = (NeumorphCardView) findViewById(R.id.loginCardView);
        NeumorphCardView editTextIdField = (NeumorphCardView) findViewById(R.id.editTextIdField);
        NeumorphCardView editTextPwField = (NeumorphCardView) findViewById(R.id.editTextPwField);
        EditText input_id = (EditText) findViewById(R.id.input_id);
        EditText input_pw = (EditText) findViewById(R.id.input_pw);

        if(modeValue == 1) {
            ChangeMode.applySubTheme(loginPage, modeValue);

            // 새 이미지로 바꿔주세요.
            darkIdImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            darkPwImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            input_id.setCompoundDrawablesWithIntrinsicBounds(darkIdImage, null, null, null);
            input_pw.setCompoundDrawablesWithIntrinsicBounds(darkPwImage, null, null, null);

            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);

            ChangeMode.setDarkShadowCardView(loginCardView);
            ChangeMode.setDarkShadowCardView(editTextIdField);
            ChangeMode.setDarkShadowCardView(editTextPwField);
            ChangeMode.setDarkShadowCardView(loginBtn);
        }
        else {
            loginBtn.setBackgroundColor(Color.rgb(0, 174, 142));
            ChangeMode.setLightShadowCardView(loginBtn);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setShapeType(1);
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
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtn.setShapeType(1);
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