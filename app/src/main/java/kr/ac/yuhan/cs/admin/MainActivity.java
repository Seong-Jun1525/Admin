package kr.ac.yuhan.cs.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kr.ac.yuhan.cs.admin.adapter.MemberAdapter;
import kr.ac.yuhan.cs.admin.data.MemberData;
import kr.ac.yuhan.cs.admin.ui.login.LoginActivity;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class MainActivity extends AppCompatActivity {
    
    // 하단 Bar 메뉴
    private NeumorphImageView homeBtn;
    private NeumorphImageView memberBtn;
    private NeumorphImageView productBtn;
    private NeumorphImageView payHistoryBtn;
    private NeumorphImageView productPushBtn;

    // HOME 메뉴
    private NeumorphCardView adminBtn;
    private NeumorphCardView adminScheduleBtn;
    private NeumorphCardView callBtn;
    private NeumorphCardView login;

    // 상단 버튼 이미지
    private NeumorphImageView setting;
    private NeumorphImageView darkMode;
    private NeumorphImageView lightMode;


    // HOME 메뉴 이미지
    private ImageView adminSchedule;
    private ImageView adminList;
    private ImageView adminLogin;
    private ImageView call;

    // 하단 메뉴 아이콘
    private NeumorphImageView usersIcon;
    private NeumorphImageView productIcon;
    private NeumorphImageView homeIcon;
    private NeumorphImageView paymentIcon;
    private NeumorphImageView productRegisterIcon;

    // 배경 기본색
    private int backgroundColor;
    private int mainBackgroundColor = Color.rgb(236, 240, 243);
    private int darkModeBackgroundColor = Color.rgb(97, 97, 97);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewFlipper vFlipper;
        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        LinearLayout main = findViewById(R.id.main);
        // 기본 배경색 지정
        backgroundColor = mainBackgroundColor;
        main.setBackgroundColor(backgroundColor);
        
        Drawable backgroundDrawable = main.getBackground();
        mainBackgroundColor = ((ColorDrawable) backgroundDrawable).getColor();

        callBtn = (NeumorphCardView) findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                callBtn.setShapeType(1);

                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {callBtn.setShapeType(0);}
                }, 200);

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/010-1234-1234"));
                startActivity(intent);
            }
        });

        darkMode = (NeumorphImageView) findViewById(R.id.darkMode);
        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                darkMode.setShapeType(1);

                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {darkMode.setShapeType(0);}
                }, 200);

                // 다크모드 색상 적용
                backgroundColor = darkModeBackgroundColor;
                main.setBackgroundColor(backgroundColor);

                // 폰트 색상 변경
                // 루트 뷰에서 모든 TextView를 찾아서 색상을 변경
                changeDarkTextColor(main, Color.WHITE);

                // 이미지 색상 변경
                adminList = (ImageView) findViewById(R.id.adminList);
                adminList.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                call = (ImageView) findViewById(R.id.call);
                call.setImageResource(R.drawable.phone);
                call.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                adminLogin = (ImageView) findViewById(R.id.adminLogin);
                adminLogin.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                adminSchedule = (ImageView) findViewById(R.id.adminSchedule);
                adminSchedule.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                usersIcon = (NeumorphImageView)  findViewById(R.id.memberBtn);
                usersIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                productIcon = (NeumorphImageView) findViewById(R.id.productBtn);
                productIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                homeIcon = (NeumorphImageView) findViewById(R.id.homeBtn);
                homeIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                paymentIcon = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
                paymentIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                productRegisterIcon = (NeumorphImageView) findViewById(R.id.productPushBtn);
                productRegisterIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                setting = (NeumorphImageView) findViewById(R.id.setting);
                setting.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                // dark 이미지로 변경
                darkMode.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                // dark 이미지로 변경
                lightMode.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }
        });

        lightMode = (NeumorphImageView) findViewById(R.id.lightMode);
        lightMode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                lightMode.setShapeType(1);

                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {lightMode.setShapeType(0);}
                }, 200);

                // 라이트모드 색상 적용
                backgroundColor = mainBackgroundColor;
                main.setBackgroundColor(backgroundColor);

                // 폰트 색상 변경
                changeLightTextColor(main, Color.rgb(0, 105, 97));

                // 라이트모드에 맞는 이미지로 변경
                adminList = (ImageView) findViewById(R.id.adminList);
                adminList.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                call = (ImageView) findViewById(R.id.call);
                call.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                adminLogin = (ImageView) findViewById(R.id.adminLogin);
                adminLogin.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                adminSchedule = (ImageView) findViewById(R.id.adminSchedule);
                adminSchedule.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                usersIcon = (NeumorphImageView)  findViewById(R.id.memberBtn);
                usersIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                productIcon = (NeumorphImageView) findViewById(R.id.productBtn);
                productIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                homeIcon = (NeumorphImageView) findViewById(R.id.homeBtn);
                homeIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                paymentIcon = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
                paymentIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                productRegisterIcon = (NeumorphImageView) findViewById(R.id.productPushBtn);
                productRegisterIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                setting = (NeumorphImageView) findViewById(R.id.setting);
                setting.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                // dark 이미지로 변경
                darkMode.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                // dark 이미지로 변경
                lightMode.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }
        });

        login = (NeumorphCardView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 클릭될 때 ShapeType을 'pressed'로 변경
                login.setShapeType(1);

                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {login.setShapeType(0);}
                }, 200);

                // Login 페이지로 이동 및 메인페이지 배경색상 전달
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("background_color", backgroundColor);
                startActivity(intent);
            }
        });

        memberBtn = (NeumorphImageView) findViewById(R.id.memberBtn);
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {memberBtn.setShapeType(0);}
                }, 200);

                vFlipper.setDisplayedChild(1);
            }
        });

        homeBtn = (NeumorphImageView) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {homeBtn.setShapeType(0);}
                }, 200);

                vFlipper.setDisplayedChild(0);
            }
        });

        productBtn = (NeumorphImageView) findViewById(R.id.productBtn);
        productBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {productBtn.setShapeType(0);}
                }, 200);

                vFlipper.setDisplayedChild(2);
            }
        });

        adminBtn = (NeumorphCardView) findViewById(R.id.adminBtn);
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {adminBtn.setShapeType(0);}
                }, 200);
            }
        });

        payHistoryBtn = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
        payHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payHistoryBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {payHistoryBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(3);
            }
        });

        productPushBtn = (NeumorphImageView) findViewById(R.id.productPushBtn);
        productPushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPushBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {productPushBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(4);
            }
        });
        adminScheduleBtn = (NeumorphCardView) findViewById(R.id.adminScheduleBtn);
        adminScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminScheduleBtn.setShapeType(1);

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {adminScheduleBtn.setShapeType(0);}
                }, 200);

                // 스케쥴 관리 페이지로 이동 및 메인페이지 배경색상 전달
                Intent intent = new Intent(getApplicationContext(), AdminScheduleActivity.class);
                intent.putExtra("background_color", backgroundColor);
                startActivity(intent);
            }
        });
    }

//    public void changeColor() {
//
//    }


    private void changeDarkTextColor(View view, int color) {
        if (view instanceof TextView) {
            // 만약 뷰가 TextView라면 색상 변경
            ((TextView) view).setTextColor(color);
        } else if (view instanceof ViewGroup) {
            // 만약 뷰가 ViewGroup이라면 해당 ViewGroup 안에 있는 모든 뷰에 대해 재귀적으로 색상 변경
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeDarkTextColor(viewGroup.getChildAt(i), color);
            }
        }
    }
    private void changeLightTextColor(View view, int color) {
        if (view instanceof TextView) {
            // 만약 뷰가 TextView라면 색상 변경
            ((TextView) view).setTextColor(color);
        } else if (view instanceof ViewGroup) {
            // 만약 뷰가 ViewGroup이라면 해당 ViewGroup 안에 있는 모든 뷰에 대해 재귀적으로 색상 변경
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeLightTextColor(viewGroup.getChildAt(i), color);
            }
        }
    }
    public static List<MemberData> generateFakeData(int count) {
        List<MemberData> members = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            // 가짜 사용자 아이디 생성 (예: user1, user2, ...)
            String userId = "user" + i;

            // 가짜 가입일 생성 (현재 날짜 기준으로 무작위로 설정)
            Date joinDate = generateRandomJoinDate();

            // 가짜 포인트 생성 (0부터 2000 사이의 무작위 값 설정)
            int point = generateRandomPoint();

            // MemberData 객체 생성 및 리스트에 추가
            members.add(new MemberData(i, userId, joinDate, point));
        }
        return members;
    }
    private static Date generateRandomJoinDate() {
        // 현재 날짜 기준으로 30일 이내의 무작위 날짜 생성
        long currentTimeMillis = System.currentTimeMillis();
        long randomTimeOffset = new Random().nextInt(30 * 24 * 60 * 60 * 1000); // 30일을 밀리초로 변환
        return new Date(currentTimeMillis - randomTimeOffset);
    }

    private static int generateRandomPoint() {
        // 0부터 2000 사이의 무작위 포인트 생성
        return new Random().nextInt(50);
    }
}
