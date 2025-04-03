package com.example.fetcher;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;

import com.example.entity.Director;
import com.example.entity.Movie;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;

@DgsComponent
public class MovieDataFetcher {

    @DgsQuery
    public List<Movie> movies() {
        return List.of(
                new Movie("1", "Inception", "101"),
                new Movie("2", "Interstellar", "102"),
                new Movie("3", "Dunkirk", "103"));
    }

    @DgsData(parentType = "Movie", field = "director")
    public CompletableFuture<Director> getDirector(DgsDataFetchingEnvironment dfe) {
        Movie movie = dfe.getSource();
        DataLoader<String, Director> dataLoader = dfe.getDataLoader("directorLoader");
        return dataLoader.load(movie.getDirectorId());
    }
}