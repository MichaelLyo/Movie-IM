package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AmazonFactRepository extends CrudRepository<AmazonFact, String> {


    AmazonFact findAmazonFactByMovieId(String movieId);

    List<AmazonFact> findAmazonFactsByMovieIdIn(List<String> movieIds);

    List<AmazonFact> findAmazonFactsByPublicationDateIn(List<String> timeIds);

    List<AmazonFact> findAmazonFactsByReleaseDateIn(List<String> timeIds);
}
