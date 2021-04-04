package com.example.ddsk;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ddsk.Adapter.EventAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ManageEventActivity extends AppCompatActivity {
    ListView lvEvent;
    public List<Event> lstEvent;
    private DatabaseReference data;
    private FirebaseUser user;
    private EventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(ManageEventActivity.this);
        //Yes Button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Yes button Clicked", Toast.LENGTH_LONG).show();
                Log.i("Code2care ", "Yes button Clicked!");
                dialog.dismiss();
            }
        });

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.custom_dialog, null);

        builder.setView(dialoglayout);
        builder.show();
        lstEvent = new ArrayList<Event>();
        setAdapterListView();

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.add_Lop);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ManageEventActivity.this);
                View mViewEvent = (View)getLayoutInflater().inflate(R.layout.dialog_add_su_kien,null);
                final TextView txtTenSuKien = (TextView)mViewEvent.findViewById(R.id.txtTenSuKien);
                final TextView txtThoiGianBatDau = (TextView)mViewEvent.findViewById(R.id.txtThoiGianBatDau);
                final TextView txtSoTinChi = (TextView)mViewEvent.findViewById(R.id.txtThoiGianKetThuc);

                Button btnAdd = (Button)mViewEvent.findViewById(R.id.btnAdd);
                btnAdd.setText("Thêm");
                Button btnCancel = (Button)mViewEvent.findViewById(R.id.btnCancel);
                alert.setView(mViewEvent);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // add data to firebase
//                        Event event = new Event(Long.parseLong(txtTenSuKien.getText().toString()),txtTenSuKien.getText().toString(),txtThoiGianBatDau.getText().toString());
//                        dataLop.child("Lop").child(user.getUid()).child(hocKy).child(txtMaLopAdd.getText().toString()).setValue(qllop);
//                        lstLop.add(qllop);
                        adapter.notifyDataSetChanged();
//                        dataLop.child("Lop").child(user.getUid()).child(hocKy).child(txtMaLopAdd.getText().toString()).addChildEventListener(new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                                Toast.makeText(QuanLyLopActivity.this, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
//                            }

//                            @Override
//                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

//                            }

//                            @Override
//                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                                Toast.makeText(QuanLyLopActivity.this, "Xóa lớp thành công", Toast.LENGTH_SHORT).show();
//                            }

//                            @Override
//                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

//                            }

//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                Toast.makeText(QuanLyLopActivity.this, "Có lỗi xảy ra. Vui lòng thử lại !", Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        //dataHocKy.child(user.getUid()).child(txtNamHoc.getText().toString()+"-"+txtHocKy.getText().toString()).setValue(txtNamHoc.getText().toString()+"-"+txtHocKy.getText().toString());
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void setAdapterListView() {
        lvEvent = (ListView) findViewById(R.id.listSuKien);
        adapter = new EventAdapter(ManageEventActivity.this,lstEvent);
        lvEvent.setAdapter(adapter);
    }


    public void xemChiTiet(View v)
    {
        View parentRow = (View) v.getParent();
        final int position = lvEvent.getPositionForView(parentRow);
        Toast.makeText(this, "Xem chi tiết"+ lstEvent.get(position).getTenSuKien(), Toast.LENGTH_SHORT).show();
    }
    public void xemThongKe(View v)
    {
        Toast.makeText(this, "Thống kê", Toast.LENGTH_SHORT).show();
    }

    public void chinhSuaLop(View v)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(ManageEventActivity.this);
        View mViewQuanLyLop = (View)getLayoutInflater().inflate(R.layout.dialog_add_su_kien,null);
        View view = (View)v.getParent();
        final int position = lvEvent.getPositionForView(view);
        Toast.makeText(this, lstEvent.get(position).getTenSuKien().toString(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, txtCurrentMaLop.getText().toString(), Toast.LENGTH_SHORT).show();
//        final EditText txtEditMaLop = (EditText)mViewQuanLyLop.findViewById(R.id.txtAddMaLop);
//        final EditText txtEditTenLop = (EditText)mViewQuanLyLop.findViewById(R.id.txtAddTenLop);
//        final EditText txtEditSoTinChi = (EditText)mViewQuanLyLop.findViewById(R.id.txtAddSoTinChi);

        // SET DATA EDIT TO EDIT_TEXT
//        txtEditMaLop.setText(lstLop.get(position).getMaLop());
//        txtEditTenLop.setText(lstLop.get(position).getTenLop());
//        txtEditSoTinChi.setText(lstLop.get(position).getSoTinChi().toString());

        // GET BUTTON IN DIALOG
//        Button btnAdd = (Button)mViewQuanLyLop.findViewById(R.id.btnAddClass);
//        btnAdd.setText("Sửa");
//        Button btnCancel = (Button)mViewQuanLyLop.findViewById(R.id.btnCancel);

        // SET VIEW TO DIALO18008579
        alert.setView(mViewQuanLyLop);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        // CATCH EVENT CLICK BUTTON CANCEL AND EDIT
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

                // REMOVE DATA CURRENT IN FIREBASE
//                dataLop.child("Lop").child(user.getUid()).child(hocKy).child(lstLop.get(position).getMaLop()).removeValue();
//                lstLop.remove(lstLop.get(position));
                // ADD NEW DATA TO FIREBASE

//                QuanLyLop qllop = new QuanLyLop(Long.parseLong(txtEditSoTinChi.getText().toString()),txtEditTenLop.getText().toString(),txtEditMaLop.getText().toString());
//                lstLop.add(qllop);

//                dataLop.child("Lop").child(user.getUid()).child(hocKy).child(txtEditMaLop.getText().toString()).setValue(qllop);
//                adapter.notifyDataSetChanged();

//                dataLop.child("Lop").child(user.getUid()).child(hocKy).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //listHocKy.add(txtNamHoc.getText().toString()+txtHocKy.getText().toString());
//                        Toast.makeText(QuanLyLopActivity.this, "Sửa học kỳ thành công", Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(QuanLyLopActivity.this, "Sửa học kỳ thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                });

//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();

    }
    public void xoaLop(View v)
    {
//        View view = (View)v.getParent();
//        final int position = lstClassRoom.getPositionForView(view);
//        dataLop.child("Lop").child(user.getUid()).child(hocKy).child(lstLop.get(position).getMaLop()).removeValue();
//        lstLop.remove(lstLop.get(position));
//        adapter.notifyDataSetChanged();
//        Toast.makeText(this, "Xóa lớp", Toast.LENGTH_SHORT).show();
    }
}

