package com.alifandroidapp.studentregister;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    DatabaseHelper myDb;
    EditText id, name, email;
    TextView textview;
    String Id, Name, Email;
    Button register, delete, update, clear, search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        id = (EditText) findViewById(R.id.studentid);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        
        textview = (TextView) findViewById(R.id.view);

        register = (Button) findViewById(R.id.register);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        clear = (Button) findViewById(R.id.clear_text);
        search = (Button) findViewById(R.id.searchText);



        AddData();
        updateData();
        deleteData();
        viewAll();
        clearText();
        getData();
    }



    public void clearText(){
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               id.setText("");
               name.setText("");
               email.setText("");
            }
        });
    }
    public void AddData(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();
                Name = name.getText().toString();
                Email = email.getText().toString();

                if(Id.equals("") || Name.equals("") || Email.equals("")){
                    Toast.makeText(MainActivity.this, "Insert the full info", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted=myDb.insertData(Id, Name, Email);
                    if(isInserted == true){
                        Toast.makeText(MainActivity.this, "student registre", Toast.LENGTH_SHORT).show();
                        viewAll();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Data could not be inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();
                Name = name.getText().toString();
                Email = email.getText().toString();
                if(Id.equals("") || Name.equals("") || Email.equals("")){
                    Toast.makeText(MainActivity.this, "Insert the full info", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isUpdate = myDb.updateData(Id, Name, Email);
                    if(isUpdate == true){
                        Toast.makeText(MainActivity.this, "student file is update", Toast.LENGTH_SHORT).show();
                        viewAll();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Data could not be updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();
                Name = name.getText().toString();
                Email = email.getText().toString();
                if(Id.equals("")){
                    Toast.makeText(MainActivity.this, "Insert the full info", Toast.LENGTH_SHORT).show();
                }
                else {
                    Integer deletedRows = myDb.deleteData(Id);
                    if(deletedRows > 0){
                        Toast.makeText(MainActivity.this, "Data is deleted", Toast.LENGTH_SHORT).show();
                        viewAll();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Data could not be deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void getData(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = id.getText().toString();

                if(Id.equals("")){
                    viewAll();
                    Toast.makeText(MainActivity.this, "Insert the full info", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor get = myDb.getData(Id);
                    StringBuffer stringBuffer = new StringBuffer();
                    if(get.moveToFirst()){
                        stringBuffer.append(" ID: "+get.getString(0)+"   Name: "+get.getString(1)+"   Email: "+get.getString(2)+" \n");
                    }
                    textview.setText(stringBuffer.toString());
                }
            }
        });
    }
    public void viewAll(){
        Cursor res = myDb.getAllData();

        StringBuffer stringBuffer = new StringBuffer();
        while (res.moveToNext()) {
            stringBuffer.append(" ID: "+res.getString(0)+"   Name: "+res.getString(1)+"   Email: "+res.getString(2)+" \n");

        }
        textview.setText(stringBuffer.toString());
    }



}