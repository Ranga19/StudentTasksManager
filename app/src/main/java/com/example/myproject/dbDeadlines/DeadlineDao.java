package com.example.myproject.dbDeadlines;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeadlineDao {
    @Query("SELECT * FROM deadline")
    List<Deadline> getAllDeadlines();

    @Insert
    void insertDeadline(Deadline... users);

    @Delete
    void deleteDeadline(Deadline user);
}
