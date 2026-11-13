package com.example.okonomi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
@Entity(tableName = "binders")
public class BinderEntity {
    @PrimaryKey
    @NonNull

    public String id;

    public String title;

    public long createdAt;

    public long updatedAt;

    public BinderEntity(String id, String title,
                        long createdAt, long updatedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
