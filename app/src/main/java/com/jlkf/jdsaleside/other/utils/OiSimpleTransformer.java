package com.jlkf.jdsaleside.other.utils;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DuoNuo on 2017/12/24.
 */

public class OiSimpleTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
