package com.example.lab6_18084851_chauquocan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add_btn,remove_btn,cancel_btn;
    EditText name_edt;
    ListView listView;
    UserDao userDao;
    int index =-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_edt = findViewById(R.id.name_edt);
        listView = findViewById(R.id.name_lv);

        ConnectDB db = Room.databaseBuilder(getApplicationContext(),ConnectDB.class,"user.db")
                .allowMainThreadQueries()
                .build();

        userDao = db.userDao();

        ArrayAdapter adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,userDao.getAllUsers());
        listView.setAdapter(adapter);
        adapter.addAll(userDao.getAllUsers());

        add_btn.setOnClickListener(v -> {
            String name = name_edt.getText().toString().trim();
            if(!name.isEmpty()){
                User user = new User(name);
                userDao.addUser(user);
                adapter.notifyDataSetChanged();
                name_edt.setText("");
            }
        });

        remove_btn.setOnClickListener(v -> {
            if(index !=-1){
                userDao.deleteUser(userDao.getAllUsers().get(index));
                adapter.notifyDataSetChanged();
            }
        });
        cancel_btn.setOnClickListener(v -> name_edt.setText(""));
    }
}