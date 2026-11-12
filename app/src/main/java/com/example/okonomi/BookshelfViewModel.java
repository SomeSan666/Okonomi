package com.example.okonomi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.okonomi.BinderDataProto.Binder;

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
        //bindersLiveData.setValue(repository.getDummyBinders());
    }

    public LiveData<List<Binder>> getBinders() {
        return bindersLiveData;
    }
}