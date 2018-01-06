package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Long>
{

    List<Genre> findGenresByName(String name);
}
