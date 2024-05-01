package kr.ac.yuhan.cs.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import kr.ac.yuhan.cs.admin.func.ChangeTextColor;
import kr.ac.yuhan.cs.admin.ui.login.LoginActivity;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class MainActivity extends AppCompatActivity {

    private int mode = 0;

    // body
    private NeumorphCardView mainCardView;
    private NeumorphCardView footer_menu;
    // 하단 Bar 메뉴
    private NeumorphImageView homeBtn;
    private NeumorphImageView memberBtn;
    private NeumorphImageView productBtn;
    private NeumorphImageView payHistoryBtn;
    private NeumorphImageView productPushBtn;

    // 회원 메뉴
    private NeumorphButton userSearchBtn;

    // HOME 메뉴
    private NeumorphCardView adminBtn;
    private NeumorphCardView adminScheduleBtn;
    private NeumorphCardView callBtn;
    private NeumorphCardView login;

    // 상품등록 메뉴
    private NeumorphCardView input_productName;
    private NeumorphCardView input_productQuantity;
    private NeumorphCardView input_productCategory;
    private NeumorphCardView input_productPrice;
    private NeumorphButton createQRBtn;
    private NeumorphButton createProductBtn;

    // 상단 버튼 이미지
    private NeumorphImageView setting;
    private NeumorphImageView changeMode;

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
    private int btnColor = Color.rgb(0, 174, 142);

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // 가짜 데이터 생성
        ArrayList<MemberData> fakeDataList = createFakeData();

        // 어댑터 설정
        MemberAdapter adapter = new MemberAdapter(this, fakeDataList);
        listView.setAdapter(adapter);

        final ViewFlipper vFlipper;
        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        LinearLayout main = findViewById(R.id.main);
        // 기본 배경색 지정
        backgroundColor = mainBackgroundColor;
        main.setBackgroundColor(backgroundColor);
        
        Drawable backgroundDrawable = main.getBackground();
        mainBackgroundColor = ((ColorDrawable) backgroundDrawable).getColor();

        createQRBtn = (NeumorphButton) findViewById(R.id.createQRBtn);
        createProductBtn = (NeumorphButton) findViewById(R.id.createProductBtn);
        userSearchBtn = (NeumorphButton) findViewById(R.id.userSearchBtn);

        createQRBtn.setBackgroundColor(btnColor);
        createProductBtn.setBackgroundColor(btnColor);
        userSearchBtn.setBackgroundColor(btnColor);

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

        changeMode = (NeumorphImageView) findViewById(R.id.darkMode);
        changeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 클릭될 때 ShapeType을 'pressed'로 변경
                changeMode.setShapeType(1);

                // 클릭된 후에는 다시 FLAT으로 변경
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {changeMode.setShapeType(0);}
                }, 200);

                if(mode == 0) {
                    // 다크모드 색상 적용
                    backgroundColor = darkModeBackgroundColor;
                    main.setBackgroundColor(backgroundColor);

                    // 폰트 색상 변경
                    // 루트 뷰에서 모든 TextView를 찾아서 색상을 변경
//                    ChangeTextColor.changeDarkTextColor(main, Color.WHITE);
                    ChangeMode.applyTheme(main, mode);

                    footer_menu = (NeumorphCardView) findViewById(R.id.footer_menu);
                    footer_menu.setShadowColorDark(Color.BLACK);
                    footer_menu.setShadowColorLight(Color.GRAY);

                    mainCardView = (NeumorphCardView) findViewById(R.id.mainCardView);
                    mainCardView.setShadowColorDark(Color.BLACK);
                    mainCardView.setShadowColorLight(Color.GRAY);

                    // 관리자 메인 페이지 CardView
                    adminBtn.setShadowColorDark(Color.BLACK);
                    adminBtn.setShadowColorLight(Color.GRAY);

                    adminScheduleBtn.setShadowColorDark(Color.BLACK);
                    adminScheduleBtn.setShadowColorLight(Color.GRAY);

                    callBtn.setShadowColorDark(Color.BLACK);
                    callBtn.setShadowColorLight(Color.GRAY);

                    login.setShadowColorDark(Color.BLACK);
                    login.setShadowColorLight(Color.GRAY);

                    // 상품등록 페이지 CardView
                    input_productName = (NeumorphCardView) findViewById(R.id.input_productName);
                    input_productName.setShadowColorDark(Color.BLACK);
                    input_productName.setShadowColorLight(Color.GRAY);

                    input_productQuantity = (NeumorphCardView) findViewById(R.id.input_productQuantity);
                    input_productQuantity.setShadowColorDark(Color.BLACK);
                    input_productQuantity.setShadowColorLight(Color.GRAY);

                    input_productCategory = (NeumorphCardView) findViewById(R.id.input_productCategory);
                    input_productCategory.setShadowColorDark(Color.BLACK);
                    input_productCategory.setShadowColorLight(Color.GRAY);

                    input_productPrice = (NeumorphCardView) findViewById(R.id.input_productPrice);
                    input_productPrice.setShadowColorDark(Color.BLACK);
                    input_productPrice.setShadowColorLight(Color.GRAY);

                    createQRBtn = (NeumorphButton) findViewById(R.id.createQRBtn);
                    createQRBtn.setShadowColorDark(Color.BLACK);
                    createQRBtn.setShadowColorLight(Color.GRAY);

                    createProductBtn = (NeumorphButton) findViewById(R.id.createProductBtn);
                    createProductBtn.setShadowColorDark(Color.BLACK);
                    createProductBtn.setShadowColorLight(Color.GRAY);

                    // 이미지 색상 변경
                    adminList = (ImageView) findViewById(R.id.adminList);
                    adminList.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                    call = (ImageView) findViewById(R.id.call);
                    call.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                    adminLogin = (ImageView) findViewById(R.id.adminLogin);
                    adminLogin.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                    adminSchedule = (ImageView) findViewById(R.id.adminSchedule);
                    adminSchedule.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                    // 하단 메뉴
                    usersIcon = (NeumorphImageView)  findViewById(R.id.memberBtn);
                    usersIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    usersIcon.setShadowColorDark(Color.BLACK);
                    usersIcon.setShadowColorLight(Color.GRAY);

                    productIcon = (NeumorphImageView) findViewById(R.id.productBtn);
                    productIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    productIcon.setShadowColorDark(Color.BLACK);
                    productIcon.setShadowColorLight(Color.GRAY);

                    homeIcon = (NeumorphImageView) findViewById(R.id.homeBtn);
                    homeIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    homeIcon.setShadowColorDark(Color.BLACK);
                    homeIcon.setShadowColorLight(Color.GRAY);

                    paymentIcon = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
                    paymentIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    paymentIcon.setShadowColorDark(Color.BLACK);
                    paymentIcon.setShadowColorLight(Color.GRAY);

                    productRegisterIcon = (NeumorphImageView) findViewById(R.id.productPushBtn);
                    productRegisterIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    productRegisterIcon.setShadowColorDark(Color.BLACK);
                    productRegisterIcon.setShadowColorLight(Color.GRAY);

                    setting = (NeumorphImageView) findViewById(R.id.setting);
                    setting.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    setting.setShadowColorDark(Color.BLACK);
                    setting.setShadowColorLight(Color.GRAY);

                    // Mode Image 및 색상 변경
                    changeMode.setImageResource(R.drawable.light);
                    changeMode.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    changeMode.setShadowColorDark(Color.BLACK);
                    changeMode.setShadowColorLight(Color.GRAY);

                    mode++;
                }
                else if(mode == 1) {
                    // 라이트모드 색상 적용
                    backgroundColor = mainBackgroundColor;
                    main.setBackgroundColor(backgroundColor);
                    // 폰트 색상 변경
//                    ChangeTextColor.changeLightTextColor(main, Color.rgb(0, 105, 97));
                    ChangeMode.applyTheme(main, mode);

                    footer_menu = (NeumorphCardView) findViewById(R.id.footer_menu);
                    footer_menu.setShadowColorDark(Color.rgb(217, 217, 217));
                    footer_menu.setShadowColorLight(Color.WHITE);

                    mainCardView = (NeumorphCardView) findViewById(R.id.mainCardView);
                    mainCardView.setShadowColorDark(Color.rgb(217, 217, 217));
                    mainCardView.setShadowColorLight(Color.WHITE);

                    // 관리자 메인 페이지 cardview
                    adminBtn.setShadowColorDark(Color.rgb(217, 217, 217));
                    adminBtn.setShadowColorLight(Color.WHITE);

                    adminScheduleBtn.setShadowColorDark(Color.rgb(217, 217, 217));
                    adminScheduleBtn.setShadowColorLight(Color.WHITE);

                    callBtn.setShadowColorDark(Color.rgb(217, 217, 217));
                    callBtn.setShadowColorLight(Color.WHITE);

                    login.setShadowColorDark(Color.rgb(217, 217, 217));
                    login.setShadowColorLight(Color.WHITE);

                    // 상품등록 페이지 CardView
                    input_productName = (NeumorphCardView) findViewById(R.id.input_productName);
                    input_productName.setShadowColorDark(Color.rgb(217, 217, 217));
                    input_productName.setShadowColorLight(Color.WHITE);

                    input_productQuantity = (NeumorphCardView) findViewById(R.id.input_productQuantity);
                    input_productQuantity.setShadowColorDark(Color.rgb(217, 217, 217));
                    input_productQuantity.setShadowColorLight(Color.WHITE);

                    input_productCategory = (NeumorphCardView) findViewById(R.id.input_productCategory);
                    input_productCategory.setShadowColorDark(Color.rgb(217, 217, 217));
                    input_productCategory.setShadowColorLight(Color.WHITE);

                    input_productPrice = (NeumorphCardView) findViewById(R.id.input_productPrice);
                    input_productPrice.setShadowColorDark(Color.rgb(217, 217, 217));
                    input_productPrice.setShadowColorLight(Color.WHITE);

                    createQRBtn = (NeumorphButton) findViewById(R.id.createQRBtn);
                    createQRBtn.setBackgroundColor(Color.rgb(0, 174, 142));
                    createQRBtn.setShadowColorDark(Color.rgb(217, 217, 217));
                    createQRBtn.setShadowColorLight(Color.WHITE);

                    createProductBtn = (NeumorphButton) findViewById(R.id.createProductBtn);
                    createProductBtn.setBackgroundColor(Color.rgb(0, 174, 142));
                    createProductBtn.setShadowColorDark(Color.rgb(217, 217, 217));
                    createProductBtn.setShadowColorLight(Color.WHITE);

                    // 라이트모드에 맞는 이미지로 변경
                    adminList = (ImageView) findViewById(R.id.adminList);
                    adminList.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                    call = (ImageView) findViewById(R.id.call);
                    call.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                    adminLogin = (ImageView) findViewById(R.id.adminLogin);
                    adminLogin.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                    adminSchedule = (ImageView) findViewById(R.id.adminSchedule);
                    adminSchedule.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

                    // 하단 메뉴
                    usersIcon = (NeumorphImageView)  findViewById(R.id.memberBtn);
                    usersIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    usersIcon.setShadowColorDark(Color.rgb(217, 217, 217));
                    usersIcon.setShadowColorLight(Color.WHITE);

                    productIcon = (NeumorphImageView) findViewById(R.id.productBtn);
                    productIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    productIcon.setShadowColorDark(Color.rgb(217, 217, 217));
                    productIcon.setShadowColorLight(Color.WHITE);

                    homeIcon = (NeumorphImageView) findViewById(R.id.homeBtn);
                    homeIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    homeIcon.setShadowColorDark(Color.rgb(217, 217, 217));
                    homeIcon.setShadowColorLight(Color.WHITE);

                    paymentIcon = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
                    paymentIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    paymentIcon.setShadowColorDark(Color.rgb(217, 217, 217));
                    paymentIcon.setShadowColorLight(Color.WHITE);

                    productRegisterIcon = (NeumorphImageView) findViewById(R.id.productPushBtn);
                    productRegisterIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    productRegisterIcon.setShadowColorDark(Color.rgb(217, 217, 217));
                    productRegisterIcon.setShadowColorLight(Color.WHITE);

                    setting = (NeumorphImageView) findViewById(R.id.setting);
                    setting.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    setting.setShadowColorDark(Color.rgb(217, 217, 217));
                    setting.setShadowColorLight(Color.WHITE);

                    // Mode Image 및 색상 변경
                    changeMode.setImageResource(R.drawable.dark);
                    changeMode.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    changeMode.setShadowColorDark(Color.rgb(217, 217, 217));
                    changeMode.setShadowColorLight(Color.WHITE);

                    mode--;
                }
                else {
                    showErrorDialog(MainActivity.this, "임성준");
                }
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
                intent.putExtra("mode", mode);
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

    private ArrayList<MemberData> createFakeData() {
        ArrayList<MemberData> dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            // 가짜 데이터 생성 및 리스트에 추가
            MemberData memberData = new MemberData(i, "User" + i, new Date(), i * 100);
            dataList.add(memberData);
        }
        return dataList;
    }

    public static void showErrorDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("오류 발생")
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 확인 버튼을 눌렀을 때 처리할 내용
                        dialog.dismiss(); // 다이얼로그를 닫습니다.
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
