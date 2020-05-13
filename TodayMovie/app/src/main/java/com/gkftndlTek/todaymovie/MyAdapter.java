package com.gkftndlTek.todaymovie;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List<MovieData> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView texttitle;
        public TextView textpubDate;
        public TextView textdirector;
        public TextView textactor;
        public TextView textUserRationg;
        public SimpleDraweeView imageview;
        public MyViewHolder(View v) {
            super(v);
            texttitle = v.findViewById(R.id.texttitle);
            textpubDate = v.findViewById(R.id.textpubDate);
            textdirector = v.findViewById(R.id.textdirector);
            textactor = v.findViewById(R.id.textactor);
            textUserRationg = v.findViewById(R.id.textUserRationg);
            texttitle.setPaintFlags(texttitle.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);



            GradientDrawable drawable = (GradientDrawable) v.getContext().getDrawable(R.drawable.imagedddddrawble);
            imageview = (SimpleDraweeView) v.findViewById(R.id.imageview);
            imageview.setBackground(drawable);
            imageview.setClipToOutline(true);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<MovieData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie, parent, false);

        //화면 각각 목록의 레이아웃

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        MovieData data = mDataset.get(position);

        String d = data.getTitle();
        d = d.replace("<b>","");
        d = d.replace("</b>","");

        holder.texttitle.setText(d);
        holder.textpubDate.setText( data.getPubDate());
        holder.textactor.setText(data.getActor());
        holder.textdirector.setText(data.getDirector());
        holder.textUserRationg.setText(data.getUserRationg());
        Uri uri = Uri.parse(data.getUrlimage());
        holder.imageview.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0  : mDataset.size();
    }
}