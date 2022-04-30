package com.imam.myasi;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CemasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CemasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private ListView listViewData;
    private RecyclerView listViewData;

//    ArrayList<String> listItem;
    ArrayList<CemasModel> cemasmodel;
//    ListAdapter adapter;
    CemasAdapter adapter;
    CemasAdapter cemasAdapter;
    DbHelper db;
    AppCompatButton simpan;
    String[] idPertanyaan;
    String[] Hasil;

    public CemasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CemasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CemasFragment newInstance(String param1, String param2) {
        CemasFragment fragment = new CemasFragment();
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
        return inflater.inflate(R.layout.fragment_cemas, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewData = view.findViewById(R.id.listViewCemas);
        simpan = view.findViewById(R.id.simpan);

        db = new DbHelper(getContext());
        String iddgs = getActivity().getIntent().getStringExtra("idDgs");

        DbHelper dbHelper = new DbHelper(getContext());
        cemasmodel = (ArrayList<CemasModel>) createItem();

        idPertanyaan = new String[15];
        Hasil = new String[15];

        adapter = new CemasAdapter(cemasmodel, getContext(), new CemasAdapter.PassData() {
            @Override
            public void onClick(String pertanyaan, String id, String hasil, int pos) {
                Toast.makeText(getContext(), pertanyaan+" - "+id, Toast.LENGTH_LONG).show();
                idPertanyaan[pos]=id;
                Hasil[pos]=hasil;

                adapter.setIdHasil(Hasil);

//                HasilModel hm = new HasilModel(iddgs,id,hasil,"cemas");
//                String hsl = db.findHasil2(hm);
//                if(hsl.equals("1")){
//                    Log.d(TAG, "onClick: update="+id+"-"+hasil);
//                    db.updateHasil(hm);
//                }else{
//                    Log.d(TAG, "onClick: insert="+id+"-"+hasil);
//                    db.addHasil(hm);
//                }
            }

            @Override
            public void onCreate(String[] hasil) {
                Hasil = hasil;
            }
        });

        Hasil = adapter.getIdHasil();
        idPertanyaan = adapter.getIdPertanyaan();
        listViewData.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.HORIZONTAL);
        listViewData.setLayoutManager(llm);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Arrays.toString(Hasil).indexOf("null") < 0){
                    for (int a=0;a<Hasil.length;a++){
                        HasilModel hm = new HasilModel(iddgs,idPertanyaan[a],Hasil[a],"cemas");
                        String hsl = db.findHasil2(hm);
                        if(hsl.equals("1")){
                            Log.d(TAG, "onClick: update="+idPertanyaan[a]+"-"+Hasil[a]);
                            db.updateHasil(hm);
                        }else{
                            Log.d(TAG, "onClick: insert="+idPertanyaan[a]+"-"+Hasil[a]);
                            db.addHasil(hm);
                        }
                    }
                    Intent intent = new Intent(getContext(),DiagnosaActivity.class);
                    intent.putExtra("listdiagnosa", 1);
                    getContext().startActivity(intent);
                }else{
                    Log.d(TAG, "onClick: nilai array ="+Arrays.toString(Hasil));
                    Toast.makeText(getContext(),"Maaf, ada pertanyaan yang belum dijawab!",Toast.LENGTH_LONG).show();
                }
            }
        });

//        simpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SparseBooleanArray sba = listViewData.getCheckedItemPositions();
//                StringBuffer sb = new StringBuffer("");
//
//
//
//                if(sba != null){
//                    for(int i = 0; i < sba.size(); i++){
//                        if(sba.valueAt(i)){
//                            int idx = sba.keyAt(i);
//                            Long di = (Long) listViewData.getAdapter().getItemId(idx)+1;
//                            String dt = (String) listViewData.getAdapter().getItem(idx);
//                            sb.append(dt);
//
//                            Integer idI = i+1;
//
//                            HasilModel hm = new HasilModel(iddgs,"cemas"+di,"1",null);
//                            dbHelper.addHasil(hm);
//
//                            Intent intent = new Intent(getContext(),DiagnosaActivity.class);
//                            intent.putExtra("listdiagnosa", 1);
//                            getContext().startActivity(intent);
//
//                        }
//                    }
//                }
//                Log.d(TAG, "onClick: "+sb.toString());
//            }
//        });
    }

    @SuppressLint("Range")
    private List<CemasModel> createItem(){
        List<CemasModel>data=new ArrayList<>();
        Cursor cr = db.viewData("quiz_questions","cemas");

        if(cr.getCount() < 1 ){
            Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_LONG).show();
        }else{
            while(cr.moveToNext()){
                CemasModel model = new CemasModel();
                model.setPertanyaan(cr.getString(cr.getColumnIndex("question")));
                model.setIdPertanyaan(cr.getString(cr.getColumnIndex("_id")));
                data.add(model);
            }


        }
        cr.close();

        return data;
    }



}