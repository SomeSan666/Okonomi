package com.example.okonomi;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BinderDao {
    // バインダーを新規登録
    @Insert
    void insert(BinderEntity binder);

    // バインダーの情報を更新
    @Update
    void update(BinderEntity binder);

    // バインダーを削除
    @Delete
    void delete(BinderEntity binder);

    // バインダー一覧を取得（作成日時の新しい順）
    @Query("SELECT * FROM binders ORDER BY createdAt DESC")
    List<BinderEntity> getAll();

    // IDを指定してバインダーを取得
    @Query("SELECT * FROM binders WHERE id = :binderId")
    BinderEntity getById(String binderId);
}
