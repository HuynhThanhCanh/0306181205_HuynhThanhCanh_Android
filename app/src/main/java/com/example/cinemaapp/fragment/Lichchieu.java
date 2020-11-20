package com.example.cinemaapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.ui.MainActivity;

import java.util.concurrent.ThreadPoolExecutor;

import static com.example.cinemaapp.R.array.Adres;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Lichchieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lichchieu extends Fragment  { //implements AdapterView.OnItemSelectedListener


    Spinner spinner;
    Spinner loai;
    View view;
    String mAdress[];
    String dinhdang[];
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Lichchieu() {
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
    public static Lichchieu newInstance(String param1, String param2) {
        Lichchieu fragment = new Lichchieu();
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
         view= inflater.inflate(R.layout.fragment_lichchieu, container, false);
        LoadlistLoai();
        loadListAddress();

       //Spinner spinner1=(Spinner)view.findViewById(R.id.spinner_dd);


        return view;
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