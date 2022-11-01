package com.example.myproject.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Note_title")
    public String titleCol;

    @ColumnInfo(name = "Note_des")
    public String desCol;

    @ColumnInfo(name = "Note_date")
    public String dateCol;

    @ColumnInfo(name = "Note_priority")
    public String priorityCol;
}
