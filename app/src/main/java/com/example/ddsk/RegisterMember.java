package com.example.ddsk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterMember extends AppCompatActivity {
    DatabaseReference mData;
    TextInputEditText txtTenTV;
    TextInputEditText txtNgaySinh;
    TextInputEditText txtSoCMND;

    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText repassword;
    Button register;
    protected FirebaseAuth mFirebaseAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member);

        mData = FirebaseDatabase.getInstance().getReference("account");
        mFirebaseAuth = FirebaseAuth.getInstance();
        txtTenTV = (TextInputEditText)findViewById(R.id.txtHoTen);
        txtNgaySinh = (TextInputEditText)findViewById(R.id.txtNgaySinh);
        txtSoCMND= (TextInputEditText)findViewById(R.id.txtCMND);
        email = (TextInputEditText)findViewById(R.id.registerUserName);
        password = (TextInputEditText)findViewById(R.id.registerPassword);
        repassword = (TextInputEditText)findViewById(R.id.registerRePassword);
        register = (Button)findViewById(R.id.register);
    }

    public void goToLogin(View view)
    {
        startActivity(new Intent(RegisterMember.this, LoginActivity.class));
        finish();
    }

    public void goToRegisterHost(View view) {
        startActivity(new Intent(RegisterMember.this, RegisterHost.class));
        finish();
    }

    public void registerMember(View view) {
        if (email.getText().toString().equals("") && email.getText().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Vui l??ng nh???p t??n t??i kho???n!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.getText().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Vui l??ng nh???p m???t kh???u!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.getText().length() < 6) {
            Toast.makeText(getApplicationContext(), "M???t kh???u qu?? ng???n!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(password.getText().toString()).equals(repassword.getText().toString()))
        {
            Toast.makeText(this, "M???t kh???u nh???p nh???p l???i kh??ng kh???p", Toast.LENGTH_SHORT).show();
            return;
        }
        // ?????ng k??
        mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(RegisterMember.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterMember.this, "C?? l???i x???y ra vui l??ng th??? l???i." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            if(user != null) {
                                String uid = user.getUid();
                                Member mb = new Member(txtTenTV.getText().toString(), txtNgaySinh.getText().toString(), txtSoCMND.getText().toString(), email.getText().toString());
                                mData.child("member").setValue(mb);
                                mData.child("member").addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        Toast.makeText(RegisterMember.this, "????ng k?? t??i kho???n th??nh c??ng", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterMember.this, LoginActivity.class));
                                        finish();
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(RegisterMember.this, "C?? l???i x???y ra. Vui l??ng th??? l???i !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            startActivity(new Intent(RegisterMember.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
    }
}
