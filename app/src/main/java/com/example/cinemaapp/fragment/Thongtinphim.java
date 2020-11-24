package com.example.cinemaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.Sodorap;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Thongtinphim#newInstance} factory method to
 * create an instance of this fragment.
 */
/*   <view
               android:id="@+id/view"
               class="com.google.android.youtube.player.YouTubePlayerView"
               android:layout_alignParentTop="true"
               android:layout_alignParentStart="true"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"/>*/
public class Thongtinphim extends Fragment {
    //YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    //YouTubePlayer.OnInitializedListener mOnInitializedListener;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Thongtinphim() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Thongtinphim.
     */
    // TODO: Rename and change types and number of parameters
    public static Thongtinphim newInstance(String param1, String param2) {
        Thongtinphim fragment = new Thongtinphim();
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
        Button muave;
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_thongtinphim, container, false);
        muave =(Button) view.findViewById(R.id.btndatve) ;
        muave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chonghe= new Intent();
                startActivity(chonghe);
            }
        });
       //mYouTubePlayerView=(YouTubePlayer)view.findViewById(R.id.view);
       return  view;
    }
}