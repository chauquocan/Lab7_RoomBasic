package com.example.lab6_18084851_chauquocan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User... user);

    @Query("Select * from user")
    public List<User> getAll();

    @Delete
    public void deleteUser(User user);

}
