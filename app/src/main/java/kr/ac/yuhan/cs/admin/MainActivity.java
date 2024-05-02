package kr.ac.yuhan.cs.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Date;

import kr.ac.yuhan.cs.admin.adapter.MemberAdapter;
import kr.ac.yuhan.cs.admin.data.MemberData;
import kr.ac.yuhan.cs.admin.ui.login.LoginActivity;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class MainActivity extends AppCompatActivity {

    private int mode = 0;

    // MainActivity CardView
    private NeumorphCardView mainCardView;
    private NeumorphCardView footer_menu;

    // 하단 Bar 메뉴
    private NeumorphImageView homeBtn;
    private NeumorphImageView userBtn;
    private NeumorphImageView productBtn;
    private NeumorphImageView payHistoryBtn;
    private NeumorphImageView productPushBtn;

    // 회원 메뉴
    private NeumorphCardView input_searchId;
    private NeumorphButton userSearchBtn;
    private NeumorphCardView userListCardView;

    // HOME 메뉴
    private NeumorphCardView adminBtn;
    private NeumorphCardView adminScheduleBtn;
    private NeumorphCardView callBtn;
    private NeumorphCardView login;

    // 상품등록 메뉴
    private NeumorphCardView input_productImage;
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

    // 배경 기본색
    private int backgroundColor;
    private int mainBackgroundColor = Color.rgb(236, 240, 243);
    private final int darkModeBackgroundColor = Color.rgb(97, 97, 97);
    private final int btnColor = Color.rgb(0, 174, 142);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

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

        // MainActivity Header Id
        setting = (NeumorphImageView) findViewById(R.id.setting);
        changeMode = (NeumorphImageView) findViewById(R.id.darkMode);

        // Admin MainPage ImageView Id
        adminList = (ImageView) findViewById(R.id.adminList);
        call = (ImageView) findViewById(R.id.call);
        adminLogin = (ImageView) findViewById(R.id.adminLogin);
        adminSchedule = (ImageView) findViewById(R.id.adminSchedule);

        // MainActivity CardView & Footer Id
        mainCardView = (NeumorphCardView) findViewById(R.id.mainCardView);
        footer_menu = (NeumorphCardView) findViewById(R.id.footer_menu);

        // User Management Page Id
        input_searchId = (NeumorphCardView) findViewById(R.id.input_searchId);
        userSearchBtn = (NeumorphButton) findViewById(R.id.userSearchBtn);
        userListCardView = (NeumorphCardView) findViewById(R.id.userListCardView);

        // Product Register Page Id
        input_productImage = (NeumorphCardView) findViewById(R.id.input_productImage);
        input_productName = (NeumorphCardView) findViewById(R.id.input_productName);
        input_productQuantity = (NeumorphCardView) findViewById(R.id.input_productQuantity);
        input_productCategory = (NeumorphCardView) findViewById(R.id.input_productCategory);
        input_productPrice = (NeumorphCardView) findViewById(R.id.input_productPrice);
        createQRBtn = (NeumorphButton) findViewById(R.id.createQRBtn);
        createProductBtn = (NeumorphButton) findViewById(R.id.createProductBtn);
        userSearchBtn = (NeumorphButton) findViewById(R.id.userSearchBtn);

        // Footer Menu Icon Id
        userBtn = (NeumorphImageView) findViewById(R.id.userBtn);
        productBtn = (NeumorphImageView) findViewById(R.id.productBtn);
        homeBtn = (NeumorphImageView) findViewById(R.id.homeBtn);
        payHistoryBtn = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
        productPushBtn = (NeumorphImageView) findViewById(R.id.productPushBtn);

        // MainActivity Button BackgroundColor Setting
        createQRBtn.setBackgroundColor(btnColor);
        createProductBtn.setBackgroundColor(btnColor);
        userSearchBtn.setBackgroundColor(btnColor);

        login = (NeumorphCardView) findViewById(R.id.login);
        adminBtn = (NeumorphCardView) findViewById(R.id.adminBtn);
        adminScheduleBtn = (NeumorphCardView) findViewById(R.id.adminScheduleBtn);

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
        // 리스트뷰 아이템 클릭 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭된 아이템의 정보를 가져옴
                MemberData selectedItem = fakeDataList.get(position);
                showMemberInfoDialog(selectedItem);
            }
        });
        changeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode.setShapeType(1);
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
                    ChangeMode.applyMainTheme(main, mode);

                    ChangeMode.setDarkShadowCardView(mainCardView);
                    ChangeMode.setDarkShadowCardView(footer_menu);

                    // 회원 관리 페이지 CardView
                    ChangeMode.setDarkShadowCardView(input_searchId);
                    ChangeMode.setDarkShadowCardView(userSearchBtn);
                    ChangeMode.setDarkShadowCardView(userListCardView);

                    // 관리자 메인 페이지 CardView
                    ChangeMode.setDarkShadowCardView(adminBtn);
                    ChangeMode.setDarkShadowCardView(adminScheduleBtn);
                    ChangeMode.setDarkShadowCardView(callBtn);
                    ChangeMode.setDarkShadowCardView(login);

                    // 상품등록 페이지 CardView
                    ChangeMode.setDarkShadowCardView(input_productImage);
                    ChangeMode.setDarkShadowCardView(input_productName);
                    ChangeMode.setDarkShadowCardView(input_productQuantity);
                    ChangeMode.setDarkShadowCardView(input_productCategory);
                    ChangeMode.setDarkShadowCardView(input_productPrice);
                    ChangeMode.setDarkShadowCardView(createQRBtn);
                    ChangeMode.setDarkShadowCardView(createProductBtn);

                    // 이미지 색상 변경
                    ChangeMode.setColorFilterDark(adminList);
                    ChangeMode.setColorFilterDark(call);
                    ChangeMode.setColorFilterDark(adminLogin);
                    ChangeMode.setColorFilterDark(adminSchedule);

                    // 하단 메뉴
                    ChangeMode.setColorFilterDark(userBtn);
                    ChangeMode.setDarkShadowCardView(userBtn);
                    ChangeMode.setColorFilterDark(productBtn);
                    ChangeMode.setDarkShadowCardView(productBtn);
                    ChangeMode.setColorFilterDark(homeBtn);
                    ChangeMode.setDarkShadowCardView(homeBtn);
                    ChangeMode.setColorFilterDark(payHistoryBtn);
                    ChangeMode.setDarkShadowCardView(payHistoryBtn);
                    ChangeMode.setColorFilterDark(productPushBtn);
                    ChangeMode.setDarkShadowCardView(productPushBtn);
                    ChangeMode.setColorFilterDark(setting);
                    ChangeMode.setDarkShadowCardView(setting);

                    // Mode Image 및 색상 변경
                    changeMode.setImageResource(R.drawable.light);
                    ChangeMode.setColorFilterDark(changeMode);
                    ChangeMode.setDarkShadowCardView(changeMode);

                    mode++;
                }
                else if(mode == 1) {
                    // 라이트모드 색상 적용
                    backgroundColor = mainBackgroundColor;
                    main.setBackgroundColor(backgroundColor);
                    // 폰트 색상 변경
//                    ChangeTextColor.changeLightTextColor(main, Color.rgb(0, 105, 97));
                    ChangeMode.applyMainTheme(main, mode);

                    ChangeMode.setLightShadowCardView(footer_menu);
                    ChangeMode.setLightShadowCardView(mainCardView);

                    // 회원 관리 페이지 CardView
                    ChangeMode.setLightShadowCardView(input_searchId);
                    ChangeMode.setLightShadowCardView(userSearchBtn);
                    ChangeMode.setLightShadowCardView(userListCardView);

                    // 관리자 메인 페이지 cardview
                    ChangeMode.setLightShadowCardView(adminBtn);
                    ChangeMode.setLightShadowCardView(adminScheduleBtn);
                    ChangeMode.setLightShadowCardView(callBtn);
                    ChangeMode.setLightShadowCardView(login);

                    // 상품등록 페이지 CardView
                    ChangeMode.setLightShadowCardView(input_productImage);
                    ChangeMode.setLightShadowCardView(input_productName);
                    ChangeMode.setLightShadowCardView(input_productQuantity);
                    ChangeMode.setLightShadowCardView(input_productPrice);
                    ChangeMode.setLightShadowCardView(input_productCategory);
                    ChangeMode.setLightShadowCardView(createQRBtn);
                    ChangeMode.setLightShadowCardView(createProductBtn);

                    // 라이트모드에 맞는 이미지로 변경
                    ChangeMode.setColorFilterLight(adminList);
                    ChangeMode.setColorFilterLight(call);
                    ChangeMode.setColorFilterLight(adminLogin);
                    ChangeMode.setColorFilterLight(adminSchedule);

                    // 하단 메뉴
                    ChangeMode.setLightShadowCardView(userBtn);
                    ChangeMode.setColorFilterLight(userBtn);
                    ChangeMode.setLightShadowCardView(productBtn);
                    ChangeMode.setColorFilterLight(productBtn);
                    ChangeMode.setLightShadowCardView(homeBtn);
                    ChangeMode.setColorFilterLight(homeBtn);
                    ChangeMode.setLightShadowCardView(payHistoryBtn);
                    ChangeMode.setColorFilterLight(payHistoryBtn);
                    ChangeMode.setLightShadowCardView(productPushBtn);
                    ChangeMode.setColorFilterLight(productPushBtn);
                    ChangeMode.setLightShadowCardView(setting);
                    ChangeMode.setColorFilterLight(setting);

                    // Mode Image 및 색상 변경
                    changeMode.setImageResource(R.drawable.dark);
                    ChangeMode.setLightShadowCardView(changeMode);
                    ChangeMode.setColorFilterLight(changeMode);

                    mode--;
                }
                else {
                    showErrorDialog(MainActivity.this, "임성준");
                }
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.setShapeType(1);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {setting.setShapeType(0);}
                }, 200);
                // Setting 페이지로 이동 및 메인페이지 배경색상 전달
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setShapeType(1);
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
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBtn.setShapeType(1);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {userBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(1);
            }
        });

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
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });
    }

    // 가짜 데이터 생성
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
    private void showMemberInfoDialog(MemberData selectedItem) {
        // 다이얼로그를 생성하고 레이아웃을 설정
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_item_info);

        // 다이얼로그 내의 TextView를 가져와서 멤버 정보로 설정
        TextView textViewMemberId = dialog.findViewById(R.id.textViewUserNum);
        textViewMemberId.setText("Num: " + selectedItem.getNumber());

        TextView textViewMemberName = dialog.findViewById(R.id.textViewUserId);
        textViewMemberName.setText("Name: " + selectedItem.getUserId());

        TextView textViewMemberDate = dialog.findViewById(R.id.textViewUserDate);
        textViewMemberDate.setText("Date: " + selectedItem.getJoinDate().toString());

        TextView textViewMemberAmount = dialog.findViewById(R.id.textViewUserPoint);
        textViewMemberAmount.setText("Point: $" + selectedItem.getPoint());

        // 다이얼로그를 보여줌
        dialog.show();
    }
}