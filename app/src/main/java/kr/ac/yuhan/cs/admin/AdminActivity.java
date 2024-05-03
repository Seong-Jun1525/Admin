package kr.ac.yuhan.cs.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import kr.ac.yuhan.cs.admin.adapter.AdminAdapter;
import kr.ac.yuhan.cs.admin.adapter.MemberAdapter;
import kr.ac.yuhan.cs.admin.data.AdminData;
import kr.ac.yuhan.cs.admin.data.MemberData;
import kr.ac.yuhan.cs.admin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class AdminActivity extends AppCompatActivity {
    private NeumorphCardView adminListCardView;
    private NeumorphCardView editTextSearchAdminField;
    private NeumorphButton adminSearchBtn;
    private NeumorphImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        LinearLayout adminListPage = (LinearLayout) findViewById(R.id.adminListPage);

        ListView listView = findViewById(R.id.listView);

        // 가짜 데이터 생성
        ArrayList<AdminData> fakeDataList = createFakeData();
        // 어댑터 설정
        AdminAdapter adapter = new AdminAdapter(this, fakeDataList);
        listView.setAdapter(adapter);

        // 현재 mode값 받음
        int modeValue = getIntent().getIntExtra("mode", 1);

        // MainActivity에서 전달된 배경 색상 값을 받음
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));
        // 배경 색상을 설정
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        backBtn = (NeumorphImageView) findViewById(R.id.backBtn);
        adminListCardView = (NeumorphCardView) findViewById(R.id.adminListCardView);
        editTextSearchAdminField = (NeumorphCardView) findViewById(R.id.editTextSearchAdminField);
        adminSearchBtn = (NeumorphButton) findViewById(R.id.adminSearchBtn);

        // 리스트뷰 아이템 클릭 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭된 아이템의 정보를 가져옴
                AdminData selectedItem = fakeDataList.get(position);
                showAdminInfoDialog(selectedItem);
            }
        });

        if(modeValue == 1) {
            ChangeMode.applySubTheme(adminListPage, modeValue);

            // 새 이미지로 바꿔주세요.
            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);

            ChangeMode.setDarkShadowCardView(adminListCardView);
            ChangeMode.setDarkShadowCardView(editTextSearchAdminField);
            ChangeMode.setDarkShadowCardView(adminSearchBtn);
        }
        else {
            adminSearchBtn.setBackgroundColor(Color.rgb(0, 174, 142));
            ChangeMode.setLightShadowCardView(adminSearchBtn);
        }

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
    // 가짜 데이터 생성
    private ArrayList<AdminData> createFakeData() {
        ArrayList<AdminData> dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            // 가짜 데이터 생성 및 리스트에 추가
            AdminData adminData = new AdminData(i, "Admin" + i, "1234", "position" + i);
            dataList.add(adminData);
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
    private void showAdminInfoDialog(AdminData selectedItem) {
        // 다이얼로그를 생성하고 레이아웃을 설정
        Dialog dialog = new Dialog(AdminActivity.this);
        dialog.setContentView(R.layout.dialog_admin_item_info);

        // 다이얼로그 내의 TextView를 가져와서 멤버 정보로 설정
        TextView textViewAdminNum = dialog.findViewById(R.id.textViewAdminNum);
        textViewAdminNum.setText("Num: " + selectedItem.getAdminNum());

        TextView textViewAdminId = dialog.findViewById(R.id.textViewAdminId);
        textViewAdminId.setText("Id: " + selectedItem.getAdminId());

        TextView textViewAdminPosition = dialog.findViewById(R.id.textViewAdminPosition);
        textViewAdminPosition.setText("Postion: " + selectedItem.getAdminPosition());

        // 다이얼로그를 보여줌
        dialog.show();
    }
}