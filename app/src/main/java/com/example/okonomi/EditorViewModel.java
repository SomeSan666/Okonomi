package com.example.okonomi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.okonomi.BinderDataProto.Binder;

public class EditorViewModel extends ViewModel {
    private final MutableLiveData<Binder> currentBinder = new MutableLiveData<>();
    private final BinderRepository repository = new BinderRepository();

    public void loadTargetBinder(String targetId) {
        Binder target = repository.getBinderById(targetId);
        currentBinder.setValue(target);
    }

    public LiveData<Binder> getBinder() {
        return currentBinder;
    }
}