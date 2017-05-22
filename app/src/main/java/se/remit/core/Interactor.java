package se.remit.core;

import io.reactivex.Observable;
import se.remit.core.network.NetworkInteractor;
import se.remit.core.network.models.Category;

/**
 * Class designed to access functionality of other interactors (suck as Memory, Network, etc.)
 */
public class Interactor {
    private NetworkInteractor networkInteractor;

    public Interactor() {
        networkInteractor = new NetworkInteractor();
    }

    /**
     * Request categories list from server
     @return CategoriesRoot observable
     */
    public Observable<Category> getCategories() {
        return networkInteractor.getCategories();
    }
}
