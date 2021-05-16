package com.example.lab6_18084851_chauquocan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    Button add_btn,remove_btn,cancel_btn;
    EditText name_edt;
    ListView listView;
    ArrayList nameList;
    ArrayList idList;
    UserDao userDao;
    int index =-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh xa
        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_edt = findViewById(R.id.name_edt);
        listView = findViewById(R.id.name_lv);

        //list
        idList = new ArrayList();
        nameList = new ArrayList();

        //Database
        ConnectDB db = Room.databaseBuilder(getApplicationContext(),ConnectDB.class,"user.db")
                .allowMainThreadQueries()
                .build();
        userDao = db.userDao();

        getList();
        //Adapter
        ArrayAdapter adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,nameList);
        listView.setAdapter(adapter);


        add_btn.setOnClickListener(v -> {
            String name = name_edt.getText().toString().trim();
            if(!name.isEmpty()){
                userDao.addUser(new User(name));
                getList();
                adapter.notifyDataSetChanged();
                name_edt.setText("");
            }
        });

        remove_btn.setOnClickListener(v -> {
            if(index !=-1){
                userDao.deleteUser(userDao.getAll().get(index));
                getList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Chon sv de xoa", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
        cancel_btn.setOnClickListener(v -> name_edt.setText(""));
    }
    private ArrayList getList(){
        nameList.clear();
        idList.clear();
        for (Iterator iterator = userDao.getAll().iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());
        }
        return nameList;
    }
}