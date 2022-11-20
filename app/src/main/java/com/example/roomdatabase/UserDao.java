package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {


    @Insert
    void insertrecord(User  users);

    @Query("select exists(select * from User where uid=:userid)")

    boolean is_exists(int userid);

    @Query("SELECT * FROM User")

    List<User> getallUser();
    @Query("DELETE  FROM User WHERE uid=:id")
     void deleteByid(int id);

}
