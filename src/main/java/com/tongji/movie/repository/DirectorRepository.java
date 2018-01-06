package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DirectorRepository extends JpaRepository<Director, Long>
{

    List<Director> findDirectorsByName(String name);

    @Query(value = "select * from director d join actor a on a.movie_id = d.movie_id where a.name = ?1", nativeQuery=true)
    List<Director> findDirectorsActor(String name);
}
