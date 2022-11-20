package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2,t3;
    Button b1,b2;
    TextView lb1,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t2=findViewById(R.id.t2);
        lb1=findViewById(R.id.lb1);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_dase").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                boolean check= userDao.is_exists(Integer.parseInt(t1.getText().toString()));
                if(check==false) {
                    userDao.insertrecord(new User(Integer.parseInt(t1.getText().toString()), t2.getText().toString(), t3.getText().toString()));
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    lb1.setText("inserted");
                }else{
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    lb1.setText("exixts already");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_dase").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                List<User> users=userDao.getallUser();
                String str="";
                for(User user:users)
                    str=str +"\t  "+user.getUid()+" "+user.getFirstName()+" "+user.getLastName()+"\n\n";
                 data.setText(str);
                 ///upper method to show the data in list view in scroll view.
                 */
                startActivity(new Intent(getApplicationContext(),fetch.class));

            }
        });

    }

    }
