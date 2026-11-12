package com.example.okonomi;

import com.example.okonomi.BinderDataProto.Binder;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class BinderRepository {
    /*
    public List<Binder> getDummyBinders(){
        List<Binder> list = new ArrayList<>();

        Binder dummy1 = Binder.newBuilder()
                .setId("binder_01")
                .setTitle("ねこちゃん")
                .build();

        Binder dummy2 = Binder.newBuilder()
                .setId("binder_02")
                .setTitle("カードゲーム")
                .build();

        list.add(dummy1);
        list.add(dummy2);
        return list;
    }
    public Binder getBinderById(String id){
        return Binder.newBuilder()
                .setId(id)
                .setTitle("選択したバインダー：" + id)
                .build();
    }

     */
    // データベースを保持
    private static AppDatabase database;

    // データベース処理用のスレッド
    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    // コンストラクタ
    public BinderRepository(Context context) {

        if (database == null) {
            synchronized (BinderRepository.class) {
                if (database == null) {
                    database = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "binder_database"
                    ).build();
                }
            }
        }
    }

    // バインダーを新規登録
    public void insertBinder(BinderEntity binder) {
        executor.execute(() -> {
            database.binderDao().insert(binder);
        });
    }

    // バインダーの情報を更新
    public void updateBinder(BinderEntity binder) {
        executor.execute(() -> {
            database.binderDao().update(binder);
        });
    }

    // バインダーを削除
    public void deleteBinder(BinderEntity binder) {
        executor.execute(() -> {
            database.binderDao().delete(binder);
        });
    }

    // バインダー一覧を取得
    public void getAllBinders(OnBindersLoadedListener listener) {
        executor.execute(() -> {
            List<BinderEntity> binders =
                    database.binderDao().getAll();

            listener.onLoaded(binders);
        });
    }

    // 読み込み完了時に呼び出す処理
    public interface OnBindersLoadedListener {
        void onLoaded(List<BinderEntity> binders);
    }

}
