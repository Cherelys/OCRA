package com.ocra.dwarbi.ocra.Fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ocra.dwarbi.ocra.Database.DatabaseHelper;
import com.ocra.dwarbi.ocra.R;

public class FormulierFragment extends Fragment{

    String bouwwerkStr,dienstenStr;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_formulier,container,false);

        Spinner diensten=(Spinner)rootView.findViewById(R.id.diensten_spinner);

        ArrayAdapter<CharSequence> dienstenAdapter =
                ArrayAdapter.createFromResource(getActivity() ,R.array.diensten_items,android.R.layout.simple_spinner_item);
        dienstenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diensten.setAdapter(dienstenAdapter);

        Spinner bouwwerk=(Spinner)rootView.findViewById(R.id.bouwwerk_spinner);

        ArrayAdapter<CharSequence> bouwwerkAdapter =
                ArrayAdapter.createFromResource(getActivity() ,R.array.bouwwerk_items,android.R.layout.simple_spinner_item);
        dienstenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bouwwerk.setAdapter(bouwwerkAdapter);

        bouwwerk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bouwwerkStr=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        diensten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dienstenStr=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }

    public void addPVE() {

        View view=getView();
        EditText budgetV=(EditText)view.findViewById(R.id.etBudgetName);
        EditText eisenV=(EditText)view.findViewById(R.id.etEisen);

        double budget=Double.valueOf(String.valueOf(budgetV.getText()));
        String eisen=String.valueOf(eisenV.getText());

        // String userid= String.valueOf(userid.getClass());

        ContentValues contentValues=new ContentValues();
        contentValues.put(DIENST,dienstenStr);
        contentValues.put(BOUWWERK,bouwwerkStr);
        contentValues.put(BUDGET,budget);
        contentValues.put(EISEN,eisen);
        // contentValues.put(ID.userid);

        DatabaseHelper db=new DatabaseHelper(getActivity());
        if (db.insertPVE(contentValues)){
            Toast.makeText(getActivity(), "succes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
        }

    }
}


