package com.even.androidlabs.rxjava;

import android.support.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import java.util.HashMap;
import java.util.Map;

/**
 * RxBus
 * Created by Even on 17/1/23.
 */

public final class RxBus {

    private static PublishSubject<Object> sSubject;

    private static Map<Object, CompositeDisposable> sDisposableMap = new HashMap<>();

    private RxBus() {
    }

    private static PublishSubject<Object> getSubject() {
        if (sSubject == null) {
            synchronized (RxBus.class) {
                if (sSubject == null) {
                    sSubject = PublishSubject.create();
                    sSubject.subscribeOn(AndroidSchedulers.mainThread());
                }
            }
        }

        return sSubject;
    }

    private static CompositeDisposable getCompositeDisposable(@NonNull Object disposeKey) {
        CompositeDisposable compositeDisposable = sDisposableMap.get(disposeKey);

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            sDisposableMap.put(disposeKey, compositeDisposable);
        }

        return compositeDisposable;
    }

    public static void register(@NonNull Object disposeKey, @NonNull Consumer<Object> consumer) {
        Disposable disposable = getSubject().subscribe(consumer);
        getCompositeDisposable(disposeKey).add(disposable);
    }

    public static void unregister(@NonNull Object disposeKey) {
        CompositeDisposable compositeDisposable = sDisposableMap.get(disposeKey);

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            sDisposableMap.remove(compositeDisposable);
        }
    }

    public static void publish(@NonNull Object message) {
        getSubject().onNext(message);
    }
}
