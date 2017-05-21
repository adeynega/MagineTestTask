package se.remit.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import se.remit.R;
import se.remit.app.App;
import se.remit.core.Interactor;
import se.remit.ui.adapters.VideoListAdapter;

public class MainActivity extends AppCompatActivity {

    private Interactor interactor;

    @BindView(R.id.video_list)
    RecyclerView videoList;

    @BindView(R.id.progressbar_wrapper)
    View progressBar;

    @BindView(R.id.error_wrapper)
    View errorWrapper;

    @BindView(R.id.reload)
    Button reload;

    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.disposable = new CompositeDisposable();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //Bind View objects to this activity's layout

        this.interactor = ((App) getApplication()).getInteractor(); //Get Interactor instance

        initUI();
        requestCategories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();  //Dispose all operations
        }
    }

    /**
     * Initialize user interface components and set listeners
     */
    private void initUI() {
        this.videoList.setHasFixedSize(true);
        this.videoList.setLayoutManager(new LinearLayoutManager(this));
        this.reload.setOnClickListener(view -> requestCategories());
    }

    /**
     * Request categories list from server
     */
    private void requestCategories() {
        this.disposable.add(this.interactor.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressBar.setVisibility(View.VISIBLE);
                    switchToVideoList();
                })
                .doOnTerminate(() -> progressBar.setVisibility(View.GONE))
                .flatMap(category -> Observable.fromIterable(category.getVideos()))
                .toList()
                .subscribe(videos -> {
                    this.videoList.setAdapter(new VideoListAdapter(videos));
                }, e -> {
                    switchToError();
                }));
    }

    /**
     * Show error text and reload button
     */
    private void switchToError() {
        this.errorWrapper.setVisibility(View.VISIBLE);
        this.videoList.setVisibility(View.GONE);
    }

    /**
     * Hide error and show video list
     */
    private void switchToVideoList() {
        this.errorWrapper.setVisibility(View.GONE);
        this.videoList.setVisibility(View.VISIBLE);
    }
}
