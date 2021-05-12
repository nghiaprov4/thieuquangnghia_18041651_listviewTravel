package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<Travel> adapter;
    Button btnSave;
    EditText editText;
    Travel travel =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        btnSave=findViewById(R.id.btnsave);
        editText=findViewById(R.id.editText);
        Travel travel1 = new Travel("1. Đà Lạt");

        DatabaseHander db= new DatabaseHander(this);
        Log.d("tag","onCreate");
        db.addTravel(travel1);
        List<Travel> travels = db.getAllTravels();
        adapter = new ArrayAdapter<>(this, R.layout.listview, R.id.tvTen, travels);
        listView.setAdapter(adapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String name = editText.getText().toString();
                for (int i = 0; i < travels.size(); i++) {
                    if (travels.get(i).getName() != name && editText.length() != 0) {
                        travel = new Travel(name);
                        db.addTravel(travel);
                        adapter.add(travel);
                        Toast.makeText(MainActivity.this, "Thêm travel thành công!!!", Toast.LENGTH_SHORT).show();
                        flag++;
                        break;
                    }
                    else
                        flag = 0;
                }
                if(flag == 0)
                    Toast.makeText(MainActivity.this, "Tên travel không hợp lệ hoặc bị trùng!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}