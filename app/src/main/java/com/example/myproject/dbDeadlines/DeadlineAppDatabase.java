package com.example.myproject.dbDeadlines;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Deadline.class},version = 1)
public abstract class DeadlineAppDatabase extends RoomDatabase {
    public abstract DeadlineDao deadlineDao();
    private static DeadlineAppDatabase INSTANCE;
    public static DeadlineAppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DeadlineAppDatabase.class, "DLDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
