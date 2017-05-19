package se.remit.core.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.remit.core.network.models.Categories;

public class NetworkInteractor {
    private NetworkService networkService;

    public NetworkInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.networkService = retrofit.create(NetworkService.class);
    }

    public Observable<Categories> getCategories() {
        return this.networkService.getCategories()
                .subscribeOn(Schedulers.io());
    }
}
