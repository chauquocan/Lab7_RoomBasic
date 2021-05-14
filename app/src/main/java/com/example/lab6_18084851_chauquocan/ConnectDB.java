package com.example.lab6_18084851_chauquocan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class ConnectDB extends RoomDatabase {
    public abstract UserDao userDao();
}
