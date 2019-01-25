package com.rcrucetastudios.moviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rcrucetastudios.moviesapp.R;
import com.rcrucetastudios.moviesapp.model.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {
    private Context  mContext;
    private List<Trailer> trailerList;

    public TrailerAdapter(Context mContext, List<Trailer> trailerList) {
            this.mContext = mContext;
            this.trailerList = trailerList;
    }

    @NonNull
    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.trailer_card, viewGroup, false);

    return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final TrailerAdapter.MyViewHolder viewHolder, int position) {
     viewHolder.title.setText(trailerList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Trailer clickedDataItem = trailerList.get(position);
                        String video = trailerList.get(position).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video));
                        intent.putExtra("VIDEO_ID",  video);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You Clicked" + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
