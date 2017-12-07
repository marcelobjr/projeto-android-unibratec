package br.com.searchmove.service;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.searchmove.model.Album;
import br.com.searchmove.model.Movie;

/**
 * Created by marcelo on 02/12/17.
 */

public class ServiceApi extends AsyncTask<Void, Void, List<Album>> {
    private final ListView mLvMovies;
    private final String HOST = "https://api.themoviedb.org/3/";
    private final String API_KEY = "api_key=0468dca260ceb349764750f3183186f3";
    Context mContext;
    private ProgressDialog dialag;

    public ServiceApi(Context context, ListView lvMovies) {
        mContext = context;
        mLvMovies = lvMovies;
    }

    @Override
    protected void onPreExecute() {
        dialag = ProgressDialog.show(mContext,"Search Move","Aguarde...");
    }

    @Override
    protected List<Album> doInBackground(Void... params) {
        List<Album> filmes = new ArrayList<>();
        try {
            URL url = new URL(HOST + "movie/popular?" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.connect();

            InputStream inputStream = conn.getInputStream();

            Scanner scanner;
            scanner = new Scanner(inputStream);

            String json = scanner.next();

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            json = jsonArray.toString();

            Gson gson = new Gson();

            Type type = new TypeToken<List<Album>>(){}.getType();
            filmes = gson.fromJson(json, type);
            Log.d("TESTE", filmes.size() + "");

            conn.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    @Override
    protected void onPostExecute(List<Album> movies) {
        // Desfazer o progressDialog
        dialag.dismiss();
        // Carregar ListView da Activity
        ArrayAdapter<Album> adapter = new ArrayAdapter<Album>(mContext,android.R.layout.simple_list_item_2,movies);
        mLvMovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}