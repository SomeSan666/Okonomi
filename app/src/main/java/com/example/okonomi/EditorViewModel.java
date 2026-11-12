package com.example.okonomi;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.okonomi.BinderDataProto.Binder;

public class EditorViewModel extends AndroidViewModel {

    private final MutableLiveData<Binder> currentBinder = new MutableLiveData<>();
    //private final BinderRepository repository = new BinderRepository();
    private final BinderRepository repository;
    public EditorViewModel(Application application) {
        super(application);
        repository = new BinderRepository(application);
    }

    public void loadTargetBinder(String targetId) {
       // Binder target = repository.getBinderById(targetId);
        //currentBinder.setValue(target);
    }

    public LiveData<Binder> getBinder() {
        return currentBinder;
    }


}