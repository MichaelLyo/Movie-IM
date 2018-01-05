package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends CrudRepository<Actor, Long>{


    List<Actor> findActorsByName(String name);
}
