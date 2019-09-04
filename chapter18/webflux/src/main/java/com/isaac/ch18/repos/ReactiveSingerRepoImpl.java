package com.isaac.ch18.repos;

import com.isaac.ch18.entities.Singer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class ReactiveSingerRepoImpl implements ReactiveSingerRepo {
    private SingerRepository singerRepository;

    @Override
    public Flux<Singer> findAll() {
        return Flux.fromIterable(singerRepository.findAll());
    }

    @Override
    public Mono<Singer> findById(Long id) {
        return Mono.justOrEmpty(singerRepository.findById(id));
    }

    @Override
    public Mono<Void> save(Mono<Singer> singerMono) {
        return singerMono.doOnNext(singerRepository::save).thenEmpty(Mono.empty());
    }
}
