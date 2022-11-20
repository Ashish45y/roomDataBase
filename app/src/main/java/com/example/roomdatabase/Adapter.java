package com.example.roomdatabase;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholer> {

    List<User> users;
    private myviewholer holder;
    private int position;

    public Adapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public myviewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
      return new myviewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholer holder, @SuppressLint("RecyclerView") int position) {
        this.holder = holder;
        this.position = position;
        holder.recid.setText(String.valueOf(users.get(position).getUid()));
   holder.recfname.setText(users.get(position).getFirstName());
        holder.reclname.setText(users.get(position).getLastName());
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
                        AppDatabase.class, "room_dase").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                //delete from roomData base

                userDao.deleteByid(users.get(position).getUid());
               //delete from arraylist
                users.remove(position);
               // update the list view of recview
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myviewholer extends RecyclerView.ViewHolder{
       TextView recid,recfname,reclname;
       ImageButton delButton;

        public myviewholer(@NonNull View itemView) {
            super(itemView);
            recid=itemView.findViewById(R.id.recid);
            recfname=itemView.findViewById(R.id.recfname);
            reclname=itemView.findViewById(R.id.reclname);
            delButton=itemView.findViewById(R.id.delbutton);

        }
    }
}
