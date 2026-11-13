package com.example.okonomi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.annotation.NonNull;
@Entity(
        tableName = "pages",
        foreignKeys = @ForeignKey(
                entity = BinderEntity.class,
                parentColumns = "id",
                childColumns = "binderId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("binderId")}
)
public class PageEntity {
    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    public String binderId;

    public int layoutType;

    public String content;

    public String imagePaths;

    public int pageOrder;

    public PageEntity(String id, String binderId,
                      int layoutType, String content,
                      String imagePaths, int pageOrder) {
        this.id = id;
        this.binderId = binderId;
        this.layoutType = layoutType;
        this.content = content;
        this.imagePaths = imagePaths;
        this.pageOrder = pageOrder;
    }
}
