package com.example.okonomi.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import com.example.okonomi.BinderDataProto.Binder;
import com.example.okonomi.data.repository.BinderRepository;

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
        repository.getBinderById(targetId, binder -> {
            currentBinder.postValue(binder);
        });

    }


    public LiveData<Binder> getBinder() {
        return currentBinder;
    }


}