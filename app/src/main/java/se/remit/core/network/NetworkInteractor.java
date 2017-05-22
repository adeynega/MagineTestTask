package se.remit.core.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.remit.BuildConfig;
import se.remit.core.network.models.Category;

/**
 * Class designed to perform network operations with Retrofit
 */
public class NetworkInteractor {
    private NetworkService networkService;

    /**
    Constructor initializes Retrofit library with proper values (Server URL, factories) and creates Retrofit instance object (NetworkService)
     */
    public NetworkInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }

    /**
    Request categories list from server
     @return CategoriesRoot observable
     */
    public Observable<Category> getCategories() {
        return networkService.getCategories()
                .flatMap(categories -> Observable.fromIterable(categories.getCategories()))
                .subscribeOn(Schedulers.io());
    }
}
