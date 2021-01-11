package com.example.cinemaapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.api.APIDangNhap;
import com.example.cinemaapp.api.APIThemThanhVien;
import com.example.cinemaapp.model.User;
import com.example.cinemaapp.model.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginApp extends AppCompatActivity {
    SignInButton signin;
    private EditText editEmail,editPass;
    private Button btnDangNhap;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        signin=findViewById(R.id.sign_in_gg);
        editEmail= (EditText) findViewById(R.id.editTextEmail);
        editPass=(EditText)findViewById(R.id.editTextPassword);
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
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

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

    private void DangNhap() throws ExecutionException, InterruptedException, JSONException {
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
        String matkhau=((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        Users users= new Users(email,matkhau);
        String read= new APIDangNhap().execute(users).get();
        JSONObject object= new JSONObject(read);

        if(object.getString("message").equals("true"))
        {
            String s=object.getString("User");
           // String name=object.getString("HoTenTV");
            JSONObject jsonObject1=new JSONObject(s);
            String nameTV=jsonObject1.getString("HoTenTV");

            Intent intent= new Intent(LoginApp.this,MainActivity.class);
           // intent.putExtra("name",nameTV);
            //  intent.putExtra("name",name);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Đăng Nhâp không thành công",Toast.LENGTH_LONG).show();
        }
    }
}
