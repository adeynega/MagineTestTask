package se.remit.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.remit.BuildConfig;
import se.remit.R;
import se.remit.core.network.models.Video;


public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {
    private List<Video> videoList;

    public VideoListAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new VideoViewHolder(layoutInflater.inflate(R.layout.video_item, null));
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = this.videoList.get(position);
        holder.title.setText(video.getTitle());
        holder.studio.setText(video.getStudio());

        Glide.with(holder.itemView)
                .load(BuildConfig.API_URL.concat(video.getSmallImage()))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        public ImageView image;
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.studio)
        public TextView studio;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
