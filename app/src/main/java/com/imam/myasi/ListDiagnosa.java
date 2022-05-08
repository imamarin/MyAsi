package com.imam.myasi;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDiagnosa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDiagnosa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton backdiagnosa;
    private MaterialCardView indikatorbayi, indikatoribu, tescemas;
    private TextView title,txtbayi,txtibu,txtcemas;
    private MaterialButton btnterapi;
    View root;
    public ListDiagnosa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListDiagnosa.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDiagnosa newInstance(String param1, String param2) {
        ListDiagnosa fragment = new ListDiagnosa();
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
        root = inflater.inflate(R.layout.fragment_list_diagnosa, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        backdiagnosa = (ImageButton) view.findViewById(R.id.backDiagnosa);
        indikatorbayi = view.findViewById(R.id.indikatorBayi);
        indikatoribu = view.findViewById(R.id.indikatorIbu);
        tescemas = view.findViewById(R.id.tesCemas);
        title = view.findViewById(R.id.title_diagnosa);
        txtbayi = view.findViewById(R.id.txtBayi);
        txtibu = view.findViewById(R.id.txtIbu);
        txtcemas = view.findViewById(R.id.txtCemas);
        btnterapi = view.findViewById(R.id.btnTerapi);

        @SuppressLint("WrongConstant") SharedPreferences session = ((Activity) getContext()).getSharedPreferences("sessionku", Context.MODE_APPEND);
        title.setText(session.getString("jdlDiagnosa",""));

        String iddgs = session.getString("idDiagnosa","");
        DbHelper dbHelper = new DbHelper(getContext());

        HasilModel hmibu = new HasilModel(iddgs,null,null,"ibu");
        Integer nilaiIbu = dbHelper.findHasil(hmibu);
        txtibu.setText(String.valueOf(nilaiIbu));
        if(nilaiIbu > 0){
            if(nilaiIbu>=5){
                txtibu.setText("Lancer");
            }else{
                txtibu.setText("Tidak Lancer");
            }
        }else{
            txtibu.setText("-");
        }

        HasilModel hmbayi = new HasilModel(iddgs,null,null,"bayi");
        Integer nilaiBayi = dbHelper.findHasil(hmbayi);
        if(nilaiBayi > 0){
            if(nilaiBayi>=4){
                txtbayi.setText("Lancer");
            }else{
                txtbayi.setText("Tidak Lancer");
            }
        }else{
            txtbayi.setText("-");
        }



        HasilModel hmcemas = new HasilModel(iddgs,null,null,"cemas");
        Integer nilaiCemas = dbHelper.findHasil(hmcemas);
        txtcemas.setText(String.valueOf(nilaiCemas));
        if(nilaiCemas > 0){
            if(nilaiCemas>=75){
                txtcemas.setText("Cemas Berat");
            }else if(nilaiCemas>=60){
                txtcemas.setText("Cemas Sedang");
            }else if(nilaiCemas>=45){
                txtcemas.setText("Cemas Ringan");
            }else{
                txtcemas.setText("Normal");
            }

        }else{
            txtcemas.setText("-");
        }


        btnterapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtibu.getText().equals("-") || txtbayi.getText().equals("-") || txtcemas.getText().equals("-") ){
                    Toast.makeText(getContext(), "Mohon maaf selesaikan terlebih dahulu pemeriksaan!",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), "Anda bisa terapi",Toast.LENGTH_SHORT).show();
                    DiagnosaModel diagnosa = new DiagnosaModel(null,Integer.valueOf(iddgs),"1");
                    dbHelper.updateDiagnosa(diagnosa);
                }
            }
        });

        indikatorbayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: bayi");
                Intent intent = new Intent(getActivity(),TestActivity.class );
                intent.putExtra("idDgs", session.getString("idDiagnosa",""));
                intent.putExtra("tes", "bayi");
                startActivity(intent);
            }
        });

        indikatoribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ibu");
                Intent intent = new Intent(getActivity(),TestActivity.class );
                intent.putExtra("idDgs", session.getString("idDiagnosa",""));
                intent.putExtra("tes", "ibu");
                startActivity(intent);
            }
        });

        tescemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: cemas");
                Intent intent = new Intent(getActivity(),TestActivity.class );
                intent.putExtra("idDgs", session.getString("idDiagnosa",""));
                intent.putExtra("tes", "cemas");
                startActivity(intent);
            }
        });

        backdiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity)getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_diagnosa, new FirstFragment())
                        .commit();
            }
        });
    }


}