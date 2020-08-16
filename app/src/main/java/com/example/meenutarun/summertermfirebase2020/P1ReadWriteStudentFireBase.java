package com.example.meenutarun.summertermfirebase2020;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class P1ReadWriteStudentFireBase extends AppCompatActivity {

    EditText etName, etReg;

    int count =1;
    DatabaseReference root;
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> ad;
    TextView tv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1readwritestudentfirebase);

        etName =(EditText) findViewById(R.id.etname);
        etReg = (EditText)findViewById(R.id.etreg);

        root = FirebaseDatabase.getInstance().getReference();

        lv = (ListView) findViewById(R.id.lv);

        al = new ArrayList<>();
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);

        lv.setAdapter(ad);
    }

    public void dothis(View view)
    {
       String s  = etName.getText().toString();
       int a = Integer.parseInt(etReg.getText().toString());

       P1Student stu = new P1Student(s,a);
        root.push().setValue(stu);

    }

    public void doRead(View view)
    {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null)
                {
                    String s1 = "";
                    Iterable<DataSnapshot>  iterable =   dataSnapshot.getChildren();
                    for(DataSnapshot ds : iterable)
                    {
                     Toast.makeText(P1ReadWriteStudentFireBase.this,""+ds.getValue(),Toast.LENGTH_SHORT).show();
                      s1 = s1+ ds.getValue();

                    }
                    al.add(s1);
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}