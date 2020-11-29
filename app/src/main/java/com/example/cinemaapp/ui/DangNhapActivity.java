package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cinemaapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDangNhap, btnDangKyTaiKhoan, btnsigingg;
    private EditText editTextEmail, editTextPassword;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.sign_in_gg).setOnClickListener(this);


        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
        //actionBar.hide(); ẩn tên app
        //actionBar.setTitle("Đăng nhập");//đặt tên app
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);

        btnDangKyTaiKhoan = (Button) findViewById(R.id.btnDangKyTaiKhoan);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEmail.getText().length() != 0 && editTextPassword.getText().length() != 0) {
                    // if (editTextEmail.getText().toString().equals(editTextTNhapEmail))
                }
            }
        });
        //ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
        //actionBar.hide(); ẩn tên app
        //actionBar.setTitle("Đăng nhập");//đặt tên app
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://R.id.home là mặc định ID của nút mũi tên quay lại
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        super.onStart();
    }

    private void updateUI(GoogleSignInAccount account) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_gg:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            Intent intent = new Intent(this, DangKyActivity.class);
            startActivity(intent);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
            // updateUI(null);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public static void getLastSignedInAccount(Context context) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(new DangKyActivity());
        if (acct != null) {
            String editTextHoTen = acct.getDisplayName();
            String editTextTNhapEmail = acct.getEmail();


        }
    }

    public void chuyensangdangky(View view) {
        Intent intent = new Intent(this, DangKyActivity.class);
        startActivity(intent);
    }
}