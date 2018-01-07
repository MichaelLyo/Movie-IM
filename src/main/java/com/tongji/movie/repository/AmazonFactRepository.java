package com.tongji.movie.repository;
import java.io.Serializable;
import java.util.List;

import com.tongji.movie.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AmazonFactRepository extends JpaRepository<AmazonFact, Long>
{

    AmazonFact findAmazonFactByMovieId(String movieId);

    List<AmazonFact> findAmazonFactsByPublicationDate(String publicationDate);

    List<AmazonFact> findAmazonFactsByReleaseDate(String releaseDate);

    @Query(value="select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationYear(int year);

    @Query(value="select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.month IN ?2", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationMonthIn(int year, Integer[] months);

    @Query(value="select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.month IN ?2 and d.week IN ?3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationDayIn(int year, Integer[] months, Integer[] days);

    @Query(value="select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.week IN ?2", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationDayIn(int year, Integer[] days);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseYear(int year);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.month IN ?2", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseMonthIn(int year, Integer[] months);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.month IN ?2 and d.week IN ?3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseDayIn(int year, Integer[] months, Integer[] days);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.week IN ?2", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseDayIn(int year, Integer[] days);

    @Query(value="select * from amazon_fact a join genre g on a.movie_id = g.movie_id where g.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByGenre(String genre);

    @Query(value = "select * from amazon_fact a where a.title LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByTitle(String title);

    @Query(value = "select * from amazon_fact a join actor ac on a.movie_id = ac.movie_id where ac.name = ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByActor(String name);

    @Query(value = "select * from amazon_fact a join starring s on a.movie_id = s.movie_id where s.name = ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByStarring(String name);

    @Query(value = "select * from amazon_fact a join director d on a.movie_id = d.movie_id where d.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByDirector(String name);

    @Query(value = "select * from amazon_fact a left join actor ac on a.movie_id = ac.movie_id " +
            "left join director d on a.movie_id = d.movie_id " +
            "left join genre g on a.movie_id = g.movie_id " +
            "where a.release_date = ?1 and a.title LIKE ?2 and ac.name LIKE ?3 and d.name LIKE ?4 and g.name = ?5", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByCombination(String date,String name,String actor,String director,String genre);



}