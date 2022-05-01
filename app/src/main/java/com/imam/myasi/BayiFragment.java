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

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BayiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BayiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listViewData;
    private String status;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DbHelper db;
    AppCompatButton simpan;

    public BayiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BayiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BayiFragment newInstance(String param1, String param2) {
        BayiFragment fragment = new BayiFragment();
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
        return inflater.inflate(R.layout.fragment_bayi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewData = view.findViewById(R.id.listViewBayi);
        simpan = view.findViewById(R.id.simpan);

        listItem= new ArrayList<>();
        db = new DbHelper(getContext());

        String iddgs = getActivity().getIntent().getStringExtra("idDgs");
        DbHelper dbHelper = new DbHelper(getContext());

        HasilModel hm = new HasilModel(iddgs,null,null,"bayi");
        Integer hasil = dbHelper.findHasil(hm);

        if(hasil > 0){
            DiagnosaModel dm = new DiagnosaModel(null,Integer.valueOf(iddgs),null);
            String status = dbHelper.findDiagnosa(dm);
            if(status.equals("1")){
                simpan.setVisibility(View.GONE);
                this.status = status;
            }else{
                this.status = "0";
            }
            viewData(false);
        }else{
            viewData(true);
        }
        listViewData.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = listViewData.getItemAtPosition(i).toString();

            }
        });



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray sba = listViewData.getCheckedItemPositions();
                StringBuffer sb = new StringBuffer("");
                String[] where = {iddgs,"bayi"};
                dbHelper.delHasilData(where);
                if(sba != null){
                    for(int i = 0; i < sba.size(); i++){
                        if(sba.valueAt(i)){
                            int idx = sba.keyAt(i);
                            Long di = (Long) listViewData.getAdapter().getItemId(idx)+1;
//                            String dt = (String) listViewData.getAdapter().getItem(idx);
                            sb.append(di);

                            Integer idI = i+1;

                            HasilModel hm = new HasilModel(iddgs,"bayi"+di,"1",null);

                            dbHelper.addHasil(hm);

                            Intent intent = new Intent(getContext(),DiagnosaActivity.class);
                            intent.putExtra("listdiagnosa", 1);
                            getContext().startActivity(intent);
                        }
                    }
                }
                Log.d(TAG, "onClick: SBA"+sb.toString());
            }
        });
    }

    @SuppressLint("Range")
    public void viewData(Boolean enable){
        Cursor cr = db.viewData("quiz_questions","bayi");

        if(cr.getCount() < 1 ){
            Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_LONG).show();
        }else{
            while(cr.moveToNext()){

                listItem.add(cr.getString(cr.getColumnIndex("question")));
            }

            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, listItem){

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    final CheckedTextView ctv = (CheckedTextView) view.findViewById(android.R.id.text1);
                    if(enable==false){
                        String iddgs = getActivity().getIntent().getStringExtra("idDgs");
                        DbHelper dbHelper = new DbHelper(getContext());
                        Integer pos = position+1;

                        HasilModel hm = new HasilModel(iddgs,"bayi"+pos,null,null);
                        String hsl = dbHelper.findHasil2(hm);
                        Log.d(TAG, "getView: "+position+"-"+hsl);
                        if (hsl.equals("1")){
                            ctv.setChecked(true);
                            ctv.setEnabled(true);
                            if(status.equals("1")){
                                ctv.setCheckMarkDrawable(R.drawable.ic_baseline_check_circle_outline_24);
                            }else{
                                listViewData.setItemChecked(position,true);
                            }
//                            ctv.setCheckMarkDrawable(R.drawable.ic_baseline_check_circle_outline_24);
                        }else{
                            ctv.setEnabled(true);
                            ctv.setChecked(false);
                            if(status.equals("1")) {
                                ctv.setCheckMarkDrawable(R.drawable.ic_baseline_clear_24);
                            }else{
                                listViewData.setItemChecked(position, false);
                            }
//                            ctv.setCheckMarkDrawable(R.drawable.ic_baseline_clear_24);
                        }
                    }


                    // Set the text color of TextView (ListView Item)
                    ctv.setTextColor(Color.GRAY);


                    // Generate ListView Item using TextView
                    return view;
                }
            };
            listViewData.setAdapter(adapter);
        }
        cr.close();
    }
}