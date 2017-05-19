package se.remit.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        this.videoList.setHasFixedSize(true);
        this.videoList.setLayoutManager(new LinearLayoutManager(this));

        this.interactor = ((App) getApplication()).getInteractor();

        requestCategories();
    }

    private void requestCategories() {
        this.interactor.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> progressBar.setVisibility(View.VISIBLE))
                .doOnComplete(() -> progressBar.setVisibility(View.GONE))
                .flatMap(categories -> Observable.fromIterable(categories.getCategories()))
                .flatMap(category -> Observable.fromIterable(category.getVideos()))
                .toList()
                .subscribe(videos -> {
                    this.videoList.setAdapter(new VideoListAdapter(videos));
                }, e -> Log.d("b1ametw", "Все плохо"));
    }
}
