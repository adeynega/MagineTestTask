package se.remit.core.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import se.remit.core.network.models.CategoriesRoot;

/**
 * Retrofit interface with request methods declaration
 */
interface NetworkService {

    @GET("videos-enhanced-c.json")
    Observable<CategoriesRoot> getCategories();
}
