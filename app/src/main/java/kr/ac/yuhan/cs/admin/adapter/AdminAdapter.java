package kr.ac.yuhan.cs.admin.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.yuhan.cs.admin.R;
import kr.ac.yuhan.cs.admin.data.AdminData;

public class AdminAdapter extends BaseAdapter {
    private ArrayList<AdminData> adminList;
    private LayoutInflater inflater;
    private Context context; // Context 추가

    public AdminAdapter(Context context, ArrayList<AdminData> adminList) {
        this.context = context;
        this.adminList = adminList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return adminList.size();
    }

    @Override
    public Object getItem(int position) {
        return adminList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdminAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            // 뷰홀더 초기화
            convertView = inflater.inflate(R.layout.admin_list, parent, false);
            viewHolder = new AdminAdapter.ViewHolder();
            viewHolder.numberTextView = convertView.findViewById(R.id.number);
            viewHolder.adminIdTextView = convertView.findViewById(R.id.adminId);
            viewHolder.userPostitionTextView = convertView.findViewById(R.id.adminPosition);
            viewHolder.outBtn = convertView.findViewById(R.id.outBtn); // outBtn 초기화
            convertView.setTag(viewHolder);
        } else {
            // 뷰홀더 재사용
            viewHolder = (AdminAdapter.ViewHolder) convertView.getTag();
        }

        AdminData adminData = adminList.get(position);
        viewHolder.numberTextView.setText(String.valueOf(adminData.getAdminNum()));
        viewHolder.adminIdTextView.setText(adminData.getAdminId());
        viewHolder.userPostitionTextView.setText(String.valueOf(adminData.getAdminPosition()));

        // 삭제 버튼 클릭 이벤트 리스너 설정
        viewHolder.outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("해당 데이터를 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // "예"를 선택했을 때의 동작
                        removeMember(position); // 데이터 삭제
                    }
                });
                builder.setNegativeButton("아니오", null); // "아니오"를 선택했을 때의 동작은 아무것도 하지 않음
                builder.show(); // AlertDialog 표시
            }
        });

        return convertView;
    }

    // 데이터 삭제 메서드
    public void removeMember(int position) {
        adminList.remove(position);
        notifyDataSetChanged(); // 변경된 데이터셋을 알려 ListView를 갱신
    }

    static class ViewHolder {
        TextView numberTextView;
        TextView adminIdTextView;
        TextView userPostitionTextView;
        ImageView outBtn; // outBtn 추가
    }
}
