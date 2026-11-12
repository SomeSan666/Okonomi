package com.example.okonomi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.okonomi.BinderDataProto.Binder;

public class ComparisonViewModel extends ViewModel {
    private final MutableLiveData<Binder> primaryBinder = new MutableLiveData<>();
    private final MutableLiveData<Binder> secondaryBinder = new MutableLiveData<>();
    private final BinderRepository repository = new BinderRepository();

    public void loadBindersForComparison(String primaryId, String secondaryId) {
        primaryBinder.setValue(repository.getBinderById(primaryId));
        secondaryBinder.setValue(repository.getBinderById(secondaryId));
    }

    public LiveData<Binder> getPrimaryBinder() {
        return primaryBinder;
    }

    public LiveData<Binder> getSecondaryBinder() {
        return secondaryBinder;
    }
}