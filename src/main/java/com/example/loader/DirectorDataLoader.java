package com.example.loader;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.dataloader.MappedBatchLoader;

import com.example.entity.Director;
import com.netflix.graphql.dgs.DgsDataLoader;

@DgsDataLoader(name = "directorLoader")
public class DirectorDataLoader implements MappedBatchLoader<String, Director> {

    @Override
    public CompletableFuture<Map<String, Director>> load(Set<String> keys) {
        // Simulate fetching directors in batch
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Director> directors = new HashMap<>();
            for (String key : keys) {
                directors.put(key, new Director(key, "Director " + key));
            }
            return directors;
        });
    }
}