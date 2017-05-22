package se.remit.app;

import android.app.Application;

import se.remit.core.Interactor;

/**
 * Application class, holds some variables that should be available within app
 */
public class App extends Application {
    private Interactor interactor;

    @Override
    public void onCreate() {
        super.onCreate();
        interactor = new Interactor();
    }

    public Interactor getInteractor() {
        return interactor;
    }
}
