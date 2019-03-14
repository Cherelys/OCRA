package com.ocra.dwarbi.ocra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ocra.dwarbi.ocra.Database.DatabaseHelper;
import com.ocra.dwarbi.ocra.R;


public class Formulier extends AppCompatActivity {

    EditText etDienstName,etAardName,etBudgetName;
    Button btnAdd,btnView;
    DatabaseHelper myDB;

    public Formulier(String phone, String birthday, String birthplace, String email, String previous_education, double major) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_formulier);
        etDienstName = (EditText) findViewById(R.id.etDienstName);
        etAardName = (EditText) findViewById(R.id.etAardName);
        etBudgetName = (EditText) findViewById(R.id.etBudgetName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Formulier.this,ViewListContents.class);
                startActivity(intent);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dName = etDienstName.getText().toString();
                String aName = etAardName.getText().toString();
                String bName = etBudgetName.getText().toString();
                if(dName.length() != 0 && aName.length() != 0 && bName.length() != 0){
                    AddData(dName,aName, bName);
                    etBudgetName.setText("");
                    etDienstName.setText("");
                    etAardName.setText("");
                }else{
                    Toast.makeText(Formulier.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void AddData(String firstName,String lastName, String favFood ){
        boolean insertData = myDB.addData(firstName,lastName,favFood);

        if(insertData==true){
            Toast.makeText(Formulier.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Formulier.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }

}
