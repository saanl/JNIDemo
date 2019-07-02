package myapp.com.jnidemo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaTest {
    private static String tag = "RxJavaTest";
    public void test1(){

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(tag, "Item: " + s);
            }


            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }

            @Override
            public void onComplete() {
                Log.d(tag, "Error!");
            }
        };

    }

}
