package com.example.meenutarun.summertermfirebase2020;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class P15MultiUserReadWriteFirebase extends AppCompatActivity {
    EditText et1, et2, et3, et4;
    Button write, read;
    static int count = 1;
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> a;
    DatabaseReference d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p15_multi_user_read_write_firebase);
        d = FirebaseDatabase.getInstance().getReference();
        et1 = (EditText) findViewById(R.id.enterid);
        et2 = (EditText) findViewById(R.id.enterphnum);
        et3 = (EditText) findViewById(R.id.entersection);
        et4 = (EditText) findViewById(R.id.enterindex);
        write = (Button) findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.lv);
        read = (Button) findViewById(R.id.save);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et1.getText().toString();
                Long ph = Long.parseLong(et2.getText().toString());
                String section = et3.getText().toString();
                String index = et4.getText().toString();
                d.child("Faculty" + count).child("id").setValue(id);
                d.child("Faculty" + count).child("ph").setValue(ph);
                d.child("Faculty" + count).child("section").setValue(section);
                d.child("Faculty" + count).child("index").setValue(index);
                count++;
                al = new ArrayList<>();
                a = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
                lv.setAdapter(a);
            }
        });
    }

    public void doRead(View v) {
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    String s = "";
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    for (DataSnapshot ds : iterable) {
                        Toast.makeText(getApplicationContext(), "" + ds.getValue(),
                                Toast.LENGTH_SHORT).show();
                        s = s + ds.getValue();
                    }
                    al.add(s);
                    a.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}