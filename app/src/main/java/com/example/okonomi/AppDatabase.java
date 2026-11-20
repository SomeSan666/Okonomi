
package com.example.okonomi;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {BinderEntity.class, PageEntity.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BinderDao binderDao();

    public abstract PageDao pageDao();
}
