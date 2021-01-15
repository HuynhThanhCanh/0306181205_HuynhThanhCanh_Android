package com.example.cinemaapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cinemaapp.R;
import com.example.cinemaapp.Sqlite.Database;
import com.example.cinemaapp.api.APIDangNhap;
import com.example.cinemaapp.model.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class LoginApp extends AppCompatActivity {
    SignInButton signin;
    private EditText editEmail,editPass;
    private Button btnDangNhap;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=1;
    Database database;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database= new Database(this,"cinema.sqlite",null,3);
        //Tạo bảng User gồm User gồm các object và trang thai
        database.QueryData("CREATE TABLE IF NOT EXISTS ThanhVien(User NVARCHAR,TrangThai NVARCHAR)");
        Cursor cursor=database.getData("Select * from ThanhVien");
        cursor.moveToFirst();
        String result="";

        while (!cursor.isAfterLast())
        {
            String User=cursor.getString(0);

            cursor.moveToNext();
            if (cursor.isAfterLast())
            {
                result=User;
            }

        }
        cursor.close();
        setContentView(R.layout.activity_dang_nhap);
        signin=findViewById(R.id.sign_in_gg);
        editEmail= (EditText) findViewById(R.id.editTextEmail);
        editPass=(EditText)findViewById(R.id.editTextPassword);
//
//        editEmail.setText("phamminhnhut@gmail.com");
//        editPass.setText("12345");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.sign_in_gg:
                        signIn();
                    break;   
                }
            }
        });
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DangNhap();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent=new Intent(LoginApp.this,MainActivity.class);
        startActivity(intent);
        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignResult(task);
        }
    }

    private void handleSignResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account=task.getResult(ApiException.class);

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private  void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void DangNhap() throws Exception {
        if(editEmail.length()==0)
        {
            editEmail.setError("Không được bỏ trống!");
            return;
        }
        else if(editPass.length()==0)
        {
            editPass.setError("Không được bỏ trống!");
            return;
        }

        String email=((EditText)findViewById(R.id.editTextEmail)).getText().toString();
        byte[] matkhau=((EditText)findViewById(R.id.editTextPassword)).getText().toString().getBytes();// hash mật khẩu qua md5
        BigInteger md5data=null;
        md5data= new BigInteger(1,md5.encryptMD5(matkhau));
        String matkhauStr=md5data.toString(16);

        Users users= new Users(email,matkhauStr);
        String read= new APIDangNhap().execute(users).get();
        JSONObject object= new JSONObject(read);

        if(object.getString("message").equals("true"))
        {
            String s=object.getString("User");
           // String name=object.getString("HoTenTV");
            JSONObject jsonObject1=new JSONObject(s);
            String nameTV=jsonObject1.getString("HoTenTV");

            String maThanhVien=jsonObject1.getString("MaThanhVien");
            String Email=jsonObject1.getString("Email");
            String Avatar=jsonObject1.getString("Avatar");
            Intent intent= new Intent(LoginApp.this,MainActivity.class);
            //Tạo database cinema

            // Thêm dữ liệu
            database.QueryData("INSERT INTO ThanhVien VALUES('" +s+"',1)");
           // intent.putExtra("name",nameTV);
            //  intent.putExtra("name",name);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Đăng Nhâp không thành công",Toast.LENGTH_LONG).show();
        }
    }

    public void chuyensangdangky(View view) {
        Intent intent= new Intent(LoginApp.this,RegisterApp.class);
        startActivity(intent);
    }
}
