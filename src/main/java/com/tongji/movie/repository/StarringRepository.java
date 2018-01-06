package com.tongji.movie.repository;

import com.tongji.movie.model.Starring;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StarringRepository extends JpaRepository<Starring, Long>
{
    List<Starring> findStarringsByName(String name);
}
