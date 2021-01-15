package com.example.cinemaapp.ui;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Ghe;
import com.example.cinemaapp.model.Lichchieu;
import com.example.cinemaapp.model.Rapphim;
import com.example.cinemaapp.readjson.LichChieuAsync;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class SoDoRapActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSoGhe, txtTongTien;
    Button btnThanhToan;
    public String Host = MainActivity.HostDomain;
    ViewGroup layout;
    Rapphim cinemaModel;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    Integer tongTien, tongSoGhe;

    private Lichchieu lichchieu=new Lichchieu();
    LinkedList<Ghe> seatSelected;
    LinkedList<Ghe> seatSec= new LinkedList<Ghe>();

    final int seatSize = 100;               // size x size of seat
    final int seatPadding = 30;             // padding seat
    final int CONFIG_SEAT_EXISTS = 1;       // config type in json 0-hide, 1-show
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_do_rap);

        AnhXa();
//        firebaseAuth =FirebaseAuth.getInstance();
//        databaseReference= FirebaseDatabase.getInstance().getReference();
        // read file json 'res/raw/so_do_rap.json'
        HttpURLConnection urlConnection= null;
        BufferedReader reader= null;
        String result = null;

        // update call api and replace variable jsonCinema
        Intent bundle = getIntent();
        String lichChieuJSON =bundle.getStringExtra("LichChieu");

        try {
            JSONObject lich =new JSONObject(lichChieuJSON);
            lichchieu.MaLichChieu=lich.getString("MaLichChieu");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            seatSelected= readJSON(MainActivity.HostDomain+"api/so-do-rap?maLichChieu="+lichchieu.MaLichChieu);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




//        String jsonCinema = readRawTextFile(R.raw.so_do_rap);

////        cinemaModel = Rapphim.ParseJson(Rapphim.class, jsonCinema);
//        cinemaModel.col=8;
//        cinemaModel.row=6;

//        try {
//            JSONArray jsonArray= new JSONArray(" [\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0,\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0,\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0,\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0,\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0,\n" +
//                    "    1, 1, 1, 1, 1,1,1,1, 0\n" +
//                    "  ]");
//            cinemaModel.configDraw=jsonArray;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        cinemaModel.listSeats=seatSelected;


        buildLayout();


        XuLyChonGhe();

    }
    public LinkedList<Ghe> readJSON(String URL) throws ExecutionException, InterruptedException, JSONException {
        LichChieuAsync lichChieuAsync = new LichChieuAsync();
        String jsonTexts=lichChieuAsync.execute(URL).get();

        LinkedList<Ghe> ghes= new LinkedList<Ghe>();

        JSONObject jsonObject = new JSONObject(jsonTexts);
        JSONArray jsonArray = jsonObject.getJSONArray("dsGhe");
        int len = jsonArray.length();
        for (int i=0;i<len;i++)
        {
            Ghe ghe = new Ghe();
            ghe.MaGhe=jsonArray.getJSONObject(i).getString("MaGhe");
//            ghe.Gia=jsonArray.getJSONObject(i).getString("Gia");
            ghe.status=Integer.parseInt(jsonArray.getJSONObject(i).getString("TrangThai"));
            ghes.addLast(ghe);
        }

        return  ghes;

    }

    private void XuLyChonGhe() {

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent = new Intent(getApplicationContext(),ThanhToanActivity.class);
                Gson gson = new Gson();
                String s = gson.toJson(lichchieu);
                intent.putExtra("LichChieu" ,s);
                String ghes=gson.toJson(seatSec);
                String Gias =gson.toJson(seatSec);
                intent.putExtra("Ghes",ghes);
                intent.putExtra("gias",Gias);
//                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class).putExtra("tongtien", tongTien)
//                        .putExtra("soghe", tongSoGhe);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                Toast.makeText(getApplicationContext(),ghes,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        btnThanhToan    = (Button) findViewById(R.id.btnThanhToan);
        txtSoGhe        = (TextView) findViewById(R.id.txtSoGhe);
        txtTongTien     = (TextView) findViewById(R.id.txtTongTien);
    }

    public void buildLayout() {
//        int row = cinemaModel.row;
//        int col = cinemaModel.col;
        int row = 6;
        int col = 8;

        // build full layout container seat
        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(seatPadding, 100, seatPadding, seatPadding);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layout = findViewById(R.id.layoutSeat);
        layout.addView(layoutSeat);

        // Cấu hình chi tiết và vị trí ghế ngồi
//        JSONArray configLayout = cinemaModel.configDraw;
        //Iterator seatIterator= cinemaModel.listSeats.iterator();

        int i =0;
        for (int pRow = 0; pRow < row; pRow++) {
            // build layout one row
            final LinearLayout layoutCol = new LinearLayout(this);
            layoutCol.setOrientation(LinearLayout.HORIZONTAL);
            layoutCol.setPadding(0, seatPadding, 0, 0);
            layoutSeat.addView(layoutCol);

            for (int pCol = 0; pCol < col; pCol++) {
                final int status =1;
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                final TextView view = new TextView(this);
                view.setLayoutParams(layoutParams);
                layoutParams.setMargins(pCol == 0 ? 0 : seatPadding, 0, 0, 0);

                if (status == CONFIG_SEAT_EXISTS) {
                    // build seat

                    final Ghe seat = (Ghe) seatSelected.get(i++);
                    view.setGravity(Gravity.CENTER);
                    view.setBackgroundResource(getResourceSeat(seat));
                    view.setTextColor(Color.WHITE);
                    view.setTag(seat);
                    view.setText((seat.MaGhe).substring(5));
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                    view.setOnClickListener(this);

                }

                layoutCol.addView(view);
            }

        }
    }

    public int getResourceSeat(Ghe seat) {
        switch (seat.status) {
            case Ghe.STATUS_AVAILABLE:
                return R.drawable.ic_seats_b;

            case Ghe.STATUS_BOOKED:
                return R.drawable.ic_seats_booked;

            default:
                return R.drawable.ic_seats_reserved;
        }
    }

    public String readRawTextFile(int resId){
        LichChieuAsync lichChieuAsync = new LichChieuAsync();
        String jsonTexts="";
        try {
            jsonTexts=lichChieuAsync.execute( MainActivity.HostDomain+"/api/so-do-rap?maLichChieu=1").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return  jsonTexts;
    }

    @Override
    public void onClick(View view) {
        Ghe seat = (Ghe) view.getTag();
        Integer soGheDaChon = seatSec.size();
        tongSoGhe = soGheDaChon;
        Integer tongGiaTien = soGheDaChon * 50000;
        tongTien = tongGiaTien;
        NumberFormat format = new DecimalFormat("#,###");
        if (seatSec.size()==8)
        {
            Toast.makeText(SoDoRapActivity.this,"Chỉ được chọn tối đa 8 ghế !",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (seat.status) {
            case Ghe.STATUS_AVAILABLE:
                if (seatSec.contains(seat)) {
                    view.setBackgroundResource(R.drawable.ic_seats_b);

                    seatSec.remove(seat);

                    if (seatSec.size()!=0)
                    {
                        for (int i = 0; i < seatSec.size(); i++) {

                            if (i == 0) {
                                txtSoGhe.setText((seatSec.get(i).MaGhe.toString()).substring(5));

                            } else {
                                txtSoGhe.setText(txtSoGhe.getText() + ", " + (seatSec.get(i).MaGhe.toString()).substring(5));

                            }
                        }
                    }else{
                        txtSoGhe.setText("");
                    }


                    txtTongTien.setText(50000*seatSec.size()+" VNĐ");
                } else  {
                    view.setBackgroundResource(R.drawable.ic_seats_selected);
                    seatSec.add(seat);




                    String finalTongTien = format.format(tongGiaTien);

                    txtTongTien.setText(finalTongTien.toString() );
                    txtTongTien.setText(50000*seatSec.size()+" VNĐ");
                    for (int i = 0; i < seatSec.size(); i++) {

                        if (i == 0) {
                            txtSoGhe.setText((seatSec.get(i).MaGhe.toString()).substring(5));

                        } else {
                            txtSoGhe.setText(txtSoGhe.getText() + ", " + (seatSec.get(i).MaGhe.toString()).substring(5));


                        }
                    }

                }
                break;

            case Ghe.STATUS_BOOKED:
                Toast.makeText(this,"Seat"+ view.getId() +"  Ghế đã được đặt",Toast.LENGTH_SHORT ).show();
//              seatSelected.add(view);
                break;

            case Ghe.STATUS_RESERVED:
                Toast.makeText(this,"Seat" + view.getId() +"   Ghế đang được chọn ",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
