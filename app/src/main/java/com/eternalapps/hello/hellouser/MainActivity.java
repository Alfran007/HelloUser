package com.eternalapps.hello.hellouser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase DatabaseObj;
    private DatabaseReference ReferenceObj;
    private ChildEventListener EventListenerobj;
    private MessageAdapter myAdapter;
    private EditText texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseObj = FirebaseDatabase.getInstance();
        ReferenceObj = DatabaseObj.getReference().child("messages");


        Button send = (Button)findViewById(R.id.button2);
        texts = (EditText) findViewById(R.id.editText) ;

        List<MessageObject> messages = new ArrayList<>();
        myAdapter = new MessageAdapter(this,messages);
        ListView myView =(ListView)findViewById(R.id.MessageListView);
        myView.setAdapter(myAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                MessageObject mobj = new MessageObject(texts.getText().toString(),"Baba", null);
                ReferenceObj.push().setValue(mobj);
                texts.setText("e");
            }
        });

        EventListenerobj = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageObject mesg = dataSnapshot.getValue(MessageObject.class);
                myAdapter.add(mesg);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ReferenceObj.addChildEventListener(EventListenerobj);
    }
}
