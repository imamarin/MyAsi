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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IbuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IbuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listViewData;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DbHelper db;
    AppCompatButton simpan;

    public IbuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IbuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IbuFragment newInstance(String param1, String param2) {
        IbuFragment fragment = new IbuFragment();
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
        return inflater.inflate(R.layout.fragment_ibu, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewData = view.findViewById(R.id.listViewIbu);
        simpan = view.findViewById(R.id.simpan);

        listItem= new ArrayList<>();
        db = new DbHelper(getContext());

        String iddgs = getActivity().getIntent().getStringExtra("idDgs");
        DbHelper dbHelper = new DbHelper(getContext());

        HasilModel hm = new HasilModel(iddgs,null,null,"ibu");
        Integer hasil = dbHelper.findHasil(hm);

        if(hasil > 0){
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

                String[] where = {iddgs,"ibu"};
                dbHelper.delHasilData(where);

                if(sba != null){
                    for(int i = 0; i < sba.size(); i++){
                        if(sba.valueAt(i)){
                            int idx = sba.keyAt(i);
                            Long di = (Long) listViewData.getAdapter().getItemId(idx)+1;
                            String dt = (String) listViewData.getAdapter().getItem(idx);
                            sb.append(dt);

                            Integer idI = i+1;

                            HasilModel hm = new HasilModel(iddgs,"ibu"+di,"1",null);

                            dbHelper.addHasil(hm);

                            Intent intent = new Intent(getContext(),DiagnosaActivity.class);
                            intent.putExtra("listdiagnosa", 1);
                            getContext().startActivity(intent);

                        }
                    }
                }
                Log.d(TAG, "onClick: "+sb.toString());
            }
        });
    }

    @SuppressLint("Range")
    public void viewData(Boolean enable){
        Cursor cr = db.viewData("quiz_questions","ibu");

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

                        HasilModel hm = new HasilModel(iddgs,"ibu"+pos,null,null);
                        String hsl = dbHelper.findHasil2(hm);
                        Log.d(TAG, "getView: "+position+"-"+hsl);
                        if (hsl.equals("1")){
                            ctv.setChecked(true);
                            ctv.setEnabled(true);
                            listViewData.setItemChecked(position,true);
//                            ctv.setCheckMarkDrawable(R.drawable.ic_baseline_check_circle_outline_24);
                        }else{
                            ctv.setEnabled(true);
                            ctv.setChecked(false);
                            listViewData.setItemChecked(position,false);
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