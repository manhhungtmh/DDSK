package com.example.ddsk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mData;
    private FirebaseAuth mFirebaseAuth;
    private TextInputEditText email;
    private TextInputEditText password;
    private Button login;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLogin();
        mData = FirebaseDatabase.getInstance().getReference("account");
        mFirebaseAuth = FirebaseAuth.getInstance();
        getViews();

    }
    public void getViews()
    {
        email = (TextInputEditText)findViewById(R.id.username);
        password = (TextInputEditText)findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
    public void login(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        if (email.getText().toString().equals("") && email.getText().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập tên tài khoản!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.getText().length() <= 0) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
        // đăng nhập
        mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Có lỗi xảy ra vui lòng thử lại." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công:", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
                            finish();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                });

    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    protected void checkLogin()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
            finish();
        }
        else {
            return;
        }
    }

    public void goToRegisterMember(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterMember.class));
        finish();
    }

    public void goToRegisterHost(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterHost.class));
        finish();
    }
}
