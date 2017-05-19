package se.remit.core.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import se.remit.core.network.models.Categories;

public interface NetworkService {

    @GET("videos-enhanced-c.json")
    Observable<Categories> getCategories();
}
