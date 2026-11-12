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
}