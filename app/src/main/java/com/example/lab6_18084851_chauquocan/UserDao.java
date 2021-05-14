package com.example.lab6_18084851_chauquocan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User... user);

    @Query("Select * from user")
    public List<User> getAllUsers();

    @Delete
    void deleteUser(User user);

}
