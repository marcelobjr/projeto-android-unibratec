package br.com.searchmove.service;

import br.com.searchmove.model.Movie;
import br.com.searchmove.model.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") String movieId);

    @GET("search/movie")
    Call<SearchResult> searchMovie(@Query("query") String query);
}
