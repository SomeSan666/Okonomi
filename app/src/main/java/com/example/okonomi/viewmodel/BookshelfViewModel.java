package com.example.okonomi.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.okonomi.BinderDataProto.Binder;
import com.example.okonomi.data.repository.BinderRepository;

import java.util.List;

public class BookshelfViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Binder>> bindersLiveData = new MutableLiveData<>();
    //private final BinderRepository repository = new BinderRepository();
    private final BinderRepository repository;
    public BookshelfViewModel(Application application) {
        super(application);
        repository = new BinderRepository(application);
        loadBinders();
    }

    private void loadBinders() {
        bindersLiveData.setValue(repository.getDummyBinders());
    }

    public LiveData<List<Binder>> getBinders() {
        return bindersLiveData;
    }
    public void addBinder(String title) {
        java.util.List<Binder> currentList = bindersLiveData.getValue();
        if (currentList == null) {
            currentList = new java.util.ArrayList<>();
        } else {
            currentList = new java.util.ArrayList<>(currentList);
        }

        Binder newBinder = Binder.newBuilder()
                .setId("binder_" + System.currentTimeMillis())
                .setTitle(title)
                .build();

        currentList.add(newBinder);
        bindersLiveData.setValue(currentList);
    }
}