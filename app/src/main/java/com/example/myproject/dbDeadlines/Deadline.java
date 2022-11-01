package com.example.myproject.dbDeadlines;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Deadline {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Dl_title")
    public String deadlineTitleCol;

    @ColumnInfo(name = "Dl_date")
    public String deadlineDateCol;
}