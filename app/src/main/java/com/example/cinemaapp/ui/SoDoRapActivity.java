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
import com.example.cinemaapp.model.Rapphim;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
public class SoDoRapActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSoGhe, txtTongTien;
    Button btnThanhToan;

    ViewGroup layout;
    Rapphim cinemaModel;

    Double tongGiaTien = 50000.0;

    ArrayList<Ghe> seatSelected = new ArrayList<>();

    final int seatSize = 100;               // size x size of seat
    final int seatPadding = 30;             // padding seat
    final int CONFIG_SEAT_EXISTS = 1;       // config type in json 0-hide, 1-show
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_do_rap);

        AnhXa();

        // read file json 'res/raw/so_do_rap.json'
        // update call api and replace variable jsonCinema
        String jsonCinema = readRawTextFile(R.raw.so_do_rap);
        cinemaModel = Rapphim.ParseJson(Rapphim.class, jsonCinema);
        try {
            buildLayout();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        XuLyChonGhe();

    }

    private void XuLyChonGhe() {

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        btnThanhToan    = (Button) findViewById(R.id.btnThanhToan);
        txtSoGhe        = (TextView) findViewById(R.id.txtSoGhe);
        txtTongTien     = (TextView) findViewById(R.id.txtTongTien);
    }

    public void buildLayout() throws JSONException {
        int row = cinemaModel.row;
        int col = cinemaModel.col;

        // build full layout container seat
        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(seatPadding, 100, seatPadding, seatPadding);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layout = findViewById(R.id.layoutSeat);
        layout.addView(layoutSeat);

        // Cấu hình chi tiết và vị trí ghế ngồi
        JSONArray configLayout = cinemaModel.configDraw;
        Iterator seatIterator= cinemaModel.listSeats.iterator();

        for (int pRow = 0; pRow < row; pRow++) {
            // build layout one row
            final LinearLayout layoutCol = new LinearLayout(this);
            layoutCol.setOrientation(LinearLayout.HORIZONTAL);
            layoutCol.setPadding(0, seatPadding, 0, 0);
            layoutSeat.addView(layoutCol);

            for (int pCol = 0; pCol < col; pCol++) {
                final int status = configLayout.getInt(pRow * col + pCol);
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                final TextView view = new TextView(this);
                view.setLayoutParams(layoutParams);
                layoutParams.setMargins(pCol == 0 ? 0 : seatPadding, 0, 0, 0);

                if (status == CONFIG_SEAT_EXISTS) {
                    // build seat
                    final Ghe seat = (Ghe) seatIterator.next();
                    view.setGravity(Gravity.CENTER);
                    view.setBackgroundResource(getResourceSeat(seat));
                    view.setTextColor(Color.WHITE);
                    view.setTag(seat);
                    view.setText(seat.MaGhe);
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

    public String readRawTextFile(int resId)
    {
        InputStream inputStream = this.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }


    @Override
    public void onClick(View view) {
        Ghe seat = (Ghe) view.getTag();
        switch (seat.status) {
            case Ghe.STATUS_AVAILABLE:
                if (seatSelected.contains(seat)) {
                    view.setBackgroundResource(R.drawable.ic_seats_b);
                    seatSelected.remove(seat);
                } else  {
                    view.setBackgroundResource(R.drawable.ic_seats_selected);
                    seatSelected.add(seat);

                    Integer soGheDaChon = seatSelected.size();
                    Integer tongGiaTien = soGheDaChon * 50000;

                    NumberFormat format = new DecimalFormat("#,###");
                    String finalTongTien = format.format(tongGiaTien);

                    txtTongTien.setText(finalTongTien.toString() + "VNĐ");

                    for (int i = 0; i < seatSelected.size(); i++) {

                        if (i == 0) {
                            txtSoGhe.setText(seatSelected.get(i).MaGhe.toString());
                        } else {
                            txtSoGhe.setText(txtSoGhe.getText() + ", " + seatSelected.get(i).MaGhe.toString());
                        }
                    }
                }
                break;

            case Ghe.STATUS_BOOKED:
                Toast.makeText(this,"Seat"+ view.getId() +"  Ghế đã được đặt",Toast.LENGTH_SHORT ).show();
//                seatSelected.add(view);
                break;

            case Ghe.STATUS_RESERVED:
                Toast.makeText(this,"Seat" + view.getId() +"   Ghế đang được chọn ",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
