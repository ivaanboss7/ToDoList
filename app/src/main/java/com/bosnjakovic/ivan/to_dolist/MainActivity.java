package com.bosnjakovic.ivan.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etAddItems;
    private Button bAdd;
    private ListView lvItems;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setUpListView();
        setUpListeners();
    }


    private void initWidgets() {
        etAddItems = (EditText) findViewById(R.id.etItem);
        bAdd = (Button) findViewById(R.id.bAdd);
        lvItems = (ListView) findViewById(R.id.lvItems);
    }

    private void setUpListView() {
        items = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(adapter);
    }

    private void setUpListeners() {
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToAdd = etAddItems.getText().toString();
                if (itemToAdd.length() > 0) {
                    items.add(itemToAdd);
                    refreshList();
                }else {
                    Toast.makeText(MainActivity.this, "Add item!", Toast.LENGTH_SHORT).show();
                    etAddItems.requestFocus();
                }

            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                refreshList();
                return true;
            }
        });
    }

    private void refreshList() {
        adapter.notifyDataSetChanged();
        etAddItems.setText("");
    }


}
