
package com.example.okonomi.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.okonomi.data.dao.BinderDao;
import com.example.okonomi.data.dao.PageDao;
import com.example.okonomi.data.entity.BinderEntity;
import com.example.okonomi.data.entity.PageEntity;

@Database(
        entities = {BinderEntity.class, PageEntity.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BinderDao binderDao();

    public abstract PageDao pageDao();
}
