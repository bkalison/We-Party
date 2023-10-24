package br.udesc.weparty.ui.criarEvento;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CriarEventoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CriarEventoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}