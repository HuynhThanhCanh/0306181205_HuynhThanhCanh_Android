package com.example.cinemaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.ui.ThongTinPhimActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhGiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhGiaFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DanhGiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Danhgia.
     */
    // TODO: Rename and change types and number of parameters
    public static DanhGiaFragment newInstance(String param1, String param2) {
        DanhGiaFragment fragment = new DanhGiaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //EditText binhluan;
    //Button btnBinhLuan;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue; String temp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_danh_gia, container, false);
        rateCount= (TextView) view.findViewById(R.id.rateCount);
        ratingBar=(RatingBar)view.findViewById(R.id.ratingBar);
        review =(EditText)view.findViewById(R.id.review);
        submit=(Button)view.findViewById(R.id.btnBl);
        showRating= (TextView)view.findViewById(R.id.showRating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue=ratingBar.getRating();
                if(rateValue<=1 && rateValue >0)
                    rateCount.setText("Bad"+rateValue +"/5");
                else if(rateValue<=2 && rateValue >1)
                    rateCount.setText("ok"+rateValue +"/5");
                else if(rateValue<=3 && rateValue >2)
                    rateCount.setText("good"+rateValue +"/5");
                else if(rateValue<=4 && rateValue >3)
                    rateCount.setText("very good"+rateValue +"/5");
                else if(rateValue<=5 && rateValue >4)
                    rateCount.setText("Best"+rateValue +"/5");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp= rateCount.getText().toString();
                showRating.setText("Your Rating: \n" +temp+"\n"+ review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
        return view;
    }
        // Inflate the layout for this fragment
      /*
        binhluan =(EditText) view.findViewById(R.id.review);
        btnBinhLuan =(Button) view.findViewById(R.id.btnBl);
        // truyền dữ liệu
        Bundle bundle= getArguments();
        if(bundle !=null){
            binhluan.setText(bundle.getString("image"));
        }
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Danhgia danhgia = new Danhgia();
                Bundle bundle = new Bundle();
                bundle.putString("image","ý kiến");
                danhgia.setArguments(bundle);
                //add dữ liệu cần nhập vào
                fragmentTransaction.add(R.id.MylearLayout,danhgia);
                //hiển thị dữ liệu sau khi add
                fragmentTransaction.commit(); //

            }
        });


    }*/
}