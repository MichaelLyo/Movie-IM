package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.Actor;
import com.tongji.movie.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Long>
{

    List<Actor> findActorsByName(String name);

    @Query(value = "select * from actor d join director a on d.movie_id = a.movie_id where d.name = ?1", nativeQuery=true)
    List<Actor> findActorsByDirector(String name);
}
