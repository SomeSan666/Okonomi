package com.example.okonomi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.okonomi.BinderDataProto.Binder;

import java.util.List;

public class BookshelfViewModel extends ViewModel {
    private final MutableLiveData<List<Binder>> bindersLiveData = new MutableLiveData<>();
    private final BinderRepository repository = new BinderRepository();

    public BookshelfViewModel(){
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