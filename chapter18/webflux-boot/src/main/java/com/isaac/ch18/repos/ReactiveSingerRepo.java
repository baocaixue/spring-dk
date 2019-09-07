package com.isaac.ch18.repos;

import com.isaac.ch18.entities.Singer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveSingerRepo {
    Flux<Singer> findAll();

    Mono<Singer> findById(Long id);

    Mono<Void> save(Mono<Singer> singerMono);
}
