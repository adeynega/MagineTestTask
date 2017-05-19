package se.remit.core;

import io.reactivex.Observable;
import se.remit.core.network.NetworkInteractor;
import se.remit.core.network.models.Categories;

public class Interactor {
    private NetworkInteractor networkInteractor;

    public Interactor() {
        this.networkInteractor = new NetworkInteractor();
    }

    public Observable<Categories> getCategories() {
        return this.networkInteractor.getCategories();
    }
}
