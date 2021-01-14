package com.example.cinemaapp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.LichChieuAdapter;
import com.example.cinemaapp.model.Lichchieu;
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.readjson.LichChieuAsync;
import com.example.cinemaapp.ui.SoDoRapActivity;
import com.example.cinemaapp.ui.ThongTinPhimActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import static com.example.cinemaapp.R.array.Adres;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LichChieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LichChieuFragment extends Fragment   {

     RecyclerView recyclerView;
     DatePicker datePicker;
    Spinner spinner;
    Spinner loai;
    View view;
    String mAdress[];
    String dinhdang[];
    int MaPhim =4;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    ImageView imgCover;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String Host = "http://192.168.131.29:8000/";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LichChieuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Lichchieu.
     */
    // TODO: Rename and change types and number of parameters
    public static LichChieuFragment newInstance(String param1, String param2) {
        LichChieuFragment fragment = new LichChieuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_lich_chieu, container, false);
         AnhXa(view);

            recyclerView= view.findViewById(R.id.recyclerViewSuatChieu);
          datePicker =view.findViewById(R.id.lich);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int year =calendar.get(Calendar.YEAR);
            int month= calendar.get(Calendar.MONTH);
            int day= calendar.get(Calendar.DAY_OF_MONTH);

            datePicker.init(year,month,day, new DatePicker.OnDateChangedListener(){

                @Override
                public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                    datePickeChange(datePicker, year,month,dayOfMonth);
                }

                private void datePickeChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    Log.d("Date","year="+year+"month="+(month+1) +"day="+dayOfMonth);
                    String dayt = ""+year+"-"+month+1+"-"+dayOfMonth;
                    try {
                        LinkedList<Lichchieu>  listFull=  readJSON(Host +"api/lich-chieu-phim-ngay?ngay="+dayt+"&maPhim="+MaPhim);




                        //Toast.makeText(getContext(),dayt,Toast.LENGTH_LONG).show();


                            initRecyclerView(listFull);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                private  void showDate(){
                    int year= datePicker.getYear();
                    int month= datePicker.getMonth();
                    int day= datePicker.getDayOfMonth();
                    Toast.makeText(getContext(),"Date"+"-"+(month+1)+"-"+year,Toast.LENGTH_LONG).show();
                }
            });
   //     String jsonTexts=LichChieuAsync.execute("http://192.168.1.3:8000/api/lich-chieu-phim-ngay?ngay=2021-1-13&maPhim=2").get();
//
        LoadlistLoai();
        loadListAddress();
        ThongTinPhimActivity image = new ThongTinPhimActivity();
        imgCover.setImageDrawable(image.thisImg);



//        hinhx = getArguments().getInt("hinh", 0);

        btnClick();

        LoadHinh();

//
//        try {
//            LinkedList<Lichchieu>  listFull=  readJSON(Host +"api/lich-chieu-phim-ngay?ngay=2021-1-14&maPhim=2");
//
//
//
//
//
//            initRecyclerView(listFull);
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return view;
    }




    public boolean ktSuatTrung(LinkedList<Lichchieu> List,Lichchieu lich)
    {
        boolean kq = false;
        int len = List.size();
        for (int i = 0; i<len;i++)
        {
            if ((List.get(i).SuatChieu).equals(lich.SuatChieu))
            {
                kq = true;
                break;
            }

        }
        return kq;
    }
    public  LinkedList<Lichchieu> readJSON(String URL) throws ExecutionException, InterruptedException, JSONException {
        LichChieuAsync lichChieuAsync = new LichChieuAsync();
        String jsonTexts=lichChieuAsync.execute(URL).get();

        LinkedList<Lichchieu> lichs= new LinkedList<Lichchieu>();

        JSONObject jsonObject = new JSONObject(jsonTexts);
        JSONArray jsonArray = jsonObject.getJSONArray("lichChieu");
        int len = jsonArray.length();
        for (int i=0;i<len;i++)
        {
            Lichchieu lichchieu = new Lichchieu();
            lichchieu.MaLichChieu=jsonArray.getJSONObject(i).getString("MaLichChieu");
            lichchieu.TenPhim=jsonArray.getJSONObject(i).getString("TenPhim");
            lichchieu.MaPhim=jsonArray.getJSONObject(i).getString("MaPhim");
            lichchieu.MaRap=jsonArray.getJSONObject(i).getString("MaRap");
            lichchieu.SuatChieu= jsonArray.getJSONObject(i).getString("ThoiGianChieu");
            lichs.addLast(lichchieu);
        }

        return  lichs;

    }

    public  void initRecyclerView(LinkedList<Lichchieu> lstFull)
    {

        LinkedList<Lichchieu> listLoc = new LinkedList<Lichchieu>();
        if (lstFull.size()!=0) {


            listLoc.clear();

            listLoc.addFirst(lstFull.get(0));
            int len = lstFull.size();
            for (int t = 1; t < len; t++) {
                if (!ktSuatTrung(listLoc, lstFull.get(t))) {
                    Lichchieu lichchieu = (lstFull.get(t));
                    listLoc.addLast(lichchieu);
                }
            }
        }

        LichChieuAdapter lichChieuAdapter1 = new LichChieuAdapter(getContext(),lstFull);
        lichChieuAdapter1.mDataLoc=listLoc;


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(lichChieuAdapter1);

    }
   
    public void setLichChieuAdapter(){

    }

    private void showDatePicker(Context context , int year,int month,int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(view.isShown()){}
            }
        }, year,month,day);
        datePickerDialog.show();
    }

    private void LoadHinh() {
        Intent intent= new Intent(getActivity(),ThongTinPhimActivity.class);

    }

    private void btnClick() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoDoRapActivity.class);
                startActivity(intent);
            }
        });


    }

    private void AnhXa(View view) {
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        imgCover = (ImageView) view.findViewById(R.id.detail_movie_cover);

    }

    private void LoadlistLoai() {
        loai=view.findViewById(R.id.spinner_dd);
        dinhdang= getResources().getStringArray(R.array.loai);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,dinhdang);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        loai.setAdapter(adapter);
    }


    public void loadListAddress() {
        spinner = view.findViewById(R.id.spinner_diachi);
        mAdress = getResources().getStringArray(Adres);
        ArrayAdapter<String> arrayAdapter =  new  ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, mAdress);
        //
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }


}