package com.dzifa.popularmoviesstage1;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.view.ViewGroup;
import com.squareup.picasso.*;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Movie[] mMovies;
    private final Context mContext;
    View.OnClickListener listener;

    public ImageAdapter(Context mContext, Movie[] mMovies) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(ImageView v) {
            super(v);
            mImageView = v;
        }
    }
    @NonNull
    @Override
    // Create new views (Invoked by the Layout Manager)
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        ImageView v = (ImageView) LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.image_thumbnail, parent, false);

        return new ViewHolder (v);
    }

    @Override
    // Replace the contents of a view (Invoked by the layout manager)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        loadPicasso(holder, position);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MovieDetails.class);
            intent.putExtra("movie", mMovies[position]);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (mMovies == null || mMovies.length == 0) {
            return -1;
        }

        return mMovies.length;
    }

    private void loadPicasso(ViewHolder holder, int position){
        Picasso.with(mContext)
                .load(mMovies[position].getPosterPath())
                .fit()
                .error(R.drawable.imdbimage)
                .placeholder(R.drawable.imdbimage)
                .into((ImageView) holder.mImageView.findViewById (R.id.image_view)
                );
    }
}
