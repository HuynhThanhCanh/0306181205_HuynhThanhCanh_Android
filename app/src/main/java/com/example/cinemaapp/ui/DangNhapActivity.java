package com.example.cinemaapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDangNhap, btnDangKyTaiKhoan, btnsigingg;
    private EditText editTextEmail, editTextPassword;
    private TextView textView,tvtest;
    private String tv_name, id;

    private ImageView imageView;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 001;
    private LoginButton loginFB;
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //textView = findViewById(R.id.tvtest);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);

        //đăng nhập bằng gg
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.sign_in_gg).setOnClickListener(this);

        //đăng nhập bằng facebook
        loginFB =findViewById(R.id.login_fb);
         callbackManager = CallbackManager.Factory.create();
        loginFB.setPermissions(Arrays.asList("user_gender, user_birthday, user_age_range, "));


        loginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { //tab là tự ra
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(DangNhapActivity.this, TrangCaNhanActivity.class);
                GraphRequest graphRequest= GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                             String name = object.getString("name");
                             String id = object.getString("id");
                             //textView.setText(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fileds", "gender, name, id, first_name, last_name");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();

                //intent.putExtra("tvtest",tvtest);

                //startActivityForResult(intent,RC_SIGN_IN);
                //Toast.makeText(getApplicationContext(), tv_name, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), (CharSequence) tvtest, Toast.LENGTH_SHORT).show();
            }
            AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    if (currentAccessToken == null){
                        LoginManager.getInstance().logOut();
                        //textView.setText("");
                    }
                }
            };


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });





//        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
//        //actionBar.hide(); //ẩn tên app
//        actionBar.setTitle("Đăng nhập");//đặt tên app
//        actionBar.setDisplayHomeAsUpEnabled(true);//dấu mũi tên
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));


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
        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);


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