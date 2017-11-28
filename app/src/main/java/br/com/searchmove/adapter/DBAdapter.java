package br.com.searchmove.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.searchmove.R;
import br.com.searchmove.model.Result;

/**
 * Created by marcelo.gomes.junior on 11/19/2017.
 */

public class DBAdapter extends ArrayAdapter<Result> {

    public DBAdapter(Context context, List<Result> results){
        super(context,0, results);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Result result = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }

        ImageView imgPoster = (ImageView)convertView.findViewById(R.id.item_movie_poster);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.item_movie_title);

        if (result.getPosterPath() != ""){
            String imageUrl = String.format("%s%s%s", "https://image.tmdb.org/t/p/", // base
                    "w1280", // size
                    result.getPosterPath());

            Picasso.with(getContext())
                    .load(imageUrl)
                    .into(imgPoster);
            Log.d("DEBUG", "result DBAdapter--> "+result.getPosterPath());
            if(result.getTitle() == null)
            txtTitle.setText(result.getOriginalTitle());
            else txtTitle.setText(result.getTitle());
        }else{
            imgPoster.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }
}
