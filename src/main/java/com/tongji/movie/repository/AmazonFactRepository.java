package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AmazonFactRepository extends JpaRepository<AmazonFact, String>
{

    AmazonFact findAmazonFactByMovieId(String movieId);

    List<AmazonFact> findAmazonFactsByMovieIdIn(List<String> movieIds);

    List<AmazonFact> findAmazonFactsByPublicationDateIn(List<String> timeIds);

    List<AmazonFact> findAmazonFactsByReleaseDateIn(List<String> timeIds);

    //@Query(value = "select a from AmazonFact a inner join Director d on a.movieId=d.movieID where d.name=?1",nativeQuery = true)
    //List<AmazonFact> findByDirectorName(String director);
}
