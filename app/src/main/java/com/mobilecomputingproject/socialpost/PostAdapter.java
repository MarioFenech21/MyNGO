package com.mobilecomputingproject.socialpost;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    //Class in charge of the RecyclerView in the MainActivity view
    private List<Post> postList;
    Context context;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        String imageUrl = post.getImageUrl();

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(holder.postImage);

        holder.postDescription.setText(post.getDescription());

        if (holder.postLinkButton != null) {
            holder.postLinkButton.setOnClickListener(v -> {
                // Open the post link in a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getLink()));
                v.getContext().startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postDescription;
        Button postLinkButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.post_image);
            postDescription = itemView.findViewById(R.id.post_description);
            postLinkButton = itemView.findViewById(R.id.post_link_button);

        }
    }
}
