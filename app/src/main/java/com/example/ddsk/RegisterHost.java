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

public class RegisterHost extends AppCompatActivity {
    TextInputEditText txtTenNguoiToChuc;
    TextInputEditText txtDonViToChuc;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText repassword;
    protected FirebaseAuth mFirebaseAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Button register;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_host);
        txtTenNguoiToChuc = (TextInputEditText)findViewById(R.id.txtHoTen);
        txtDonViToChuc = (TextInputEditText)findViewById(R.id.txtTenToChuc);
        email = (TextInputEditText)findViewById(R.id.registerUserName);
        register = (Button)findViewById(R.id.register);
        password = (TextInputEditText)findViewById(R.id.registerPassword);
        repassword = (TextInputEditText)findViewById(R.id.registerRePassword);
        mData = FirebaseDatabase.getInstance().getReference("account");
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    public void goToRegisterMember(View view) {
        startActivity(new Intent(RegisterHost.this, RegisterMember.class));
        finish();
    }

    public void goToLogin(View view)
    {
        startActivity(new Intent(RegisterHost.this, LoginActivity.class));
        finish();
    }

    public void registerHost(View view) {
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
                .addOnCompleteListener(RegisterHost.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterHost.this, "C?? l???i x???y ra vui l??ng th??? l???i." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            if(user != null) {
                                String uid = user.getUid();
                                Host host = new Host(txtTenNguoiToChuc.getText().toString(), txtDonViToChuc.getText().toString(), email.getText().toString());
                                mData.child("host").setValue(host);
                                mData.child("host").addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        Toast.makeText(RegisterHost.this, "????ng k?? t??i kho???n th??nh c??ng", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterHost.this, LoginActivity.class));
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
                                        Toast.makeText(RegisterHost.this, "C?? l???i x???y ra. Vui l??ng th??? l???i !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            startActivity(new Intent(RegisterHost.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
    }
}