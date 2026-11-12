package com.example.okonomi;

import com.example.okonomi.BinderDataProto.Binder;
import java.util.ArrayList;
import java.util.List;

public class BinderRepository {
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
}
