package se.remit.app;

import android.app.Application;

import se.remit.core.Interactor;

public class App extends Application {
    private Interactor interactor;

    @Override
    public void onCreate() {
        super.onCreate();
        this.interactor = new Interactor();
    }

    public Interactor getInteractor() {
        return interactor;
    }
}
