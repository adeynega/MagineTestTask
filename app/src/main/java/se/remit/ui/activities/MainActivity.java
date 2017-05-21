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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        initUI();
        requestCategories();
    }

    private void initUI() {
        this.videoList.setHasFixedSize(true);
        this.videoList.setLayoutManager(new LinearLayoutManager(this));

        this.interactor = ((App) getApplication()).getInteractor();

        this.reload.setOnClickListener(view -> requestCategories());
    }

    private void requestCategories() {
        this.interactor.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressBar.setVisibility(View.VISIBLE);
                    switchToVideoList();
                })
                .doOnTerminate(() -> progressBar.setVisibility(View.GONE))
                .flatMap(categories -> Observable.fromIterable(categories.getCategories()))
                .flatMap(category -> Observable.fromIterable(category.getVideos()))
                .toList()
                .subscribe(videos -> {
                    this.videoList.setAdapter(new VideoListAdapter(videos));
                }, e -> {
                    switchToError();
                });
    }

    private void switchToError() {
        this.errorWrapper.setVisibility(View.VISIBLE);
        this.videoList.setVisibility(View.GONE);
    }

    private void switchToVideoList() {
        this.errorWrapper.setVisibility(View.GONE);
        this.videoList.setVisibility(View.VISIBLE);
    }
}
