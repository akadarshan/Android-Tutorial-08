package com.darshan.tutorial08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseHelper myDbHelper;
    ListView lstUserListView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("session", MODE_PRIVATE);
        editor = preferences.edit();

        lstUserListView = findViewById(R.id.lstUserListView);
        myDbHelper=new DatabaseHelper(Welcome.this);
        adapter = new ArrayAdapter<String>(
                Welcome.this,
                android.R.layout.simple_list_item_1,
                myDbHelper.getUserList()
        );
        lstUserListView.setAdapter(adapter);
        lstUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem = ((TextView)view).getText().toString();
                Intent intent = new Intent(Welcome.this,UserDetail.class);
                intent.putExtra("userdata",listItem);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lgt_menu:
                editor.remove("email");
                editor.commit();
                startActivity(new Intent(Welcome.this,Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}