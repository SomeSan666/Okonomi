
package com.example.okonomi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PageDao {

    // ページを新規登録
    @Insert
    void insert(PageEntity page);

    // ページの内容を更新
    @Update
    void update(PageEntity page);

    // ページを削除
    @Delete
    void delete(PageEntity page);

    // 指定したバインダーのページ一覧を取得
    @Query("SELECT * FROM pages WHERE binderId = :binderId ORDER BY pageOrder ASC")
    List<PageEntity> getPagesByBinderId(String binderId);

    // ページIDを指定して取得
    @Query("SELECT * FROM pages WHERE id = :pageId")
    PageEntity getById(String pageId);

    // 指定したバインダーに属するページをすべて削除
    @Query("DELETE FROM pages WHERE binderId = :binderId")
    void deletePagesByBinderId(String binderId);
}
