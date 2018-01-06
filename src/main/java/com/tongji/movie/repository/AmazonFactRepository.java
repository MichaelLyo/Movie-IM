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
    List<AmazonFact> findAmazonFactsByPublicationMonthIn(int year, List<Integer> month);

    @Query(value="SELECT * FROM amazon_fact a JOIN time_dim d ON a.publication_date = d.time_id WHERE d.year = ?1 AND d.month BETWEEN ?2 * 3 - 2 AND ?2 * 3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationQuarter(int year, int quarter);

    @Query(value="select * from amazon_fact a join time_dim d on a.publication_date = d.time_id where d.year = ?1 and d.month = ?2 and d.day = ?3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByPublicationDay(int year, int month, int day);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseYear(int year);

    @Query(value="SELECT * FROM amazon_fact a JOIN time_dim d ON a.release_date = d.time_id WHERE d.year = ?1 AND d.month BETWEEN ?2 AND ?3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseMonthBetween(int year, int month1, int month2);

    @Query(value="select * from amazon_fact a join time_dim d on a.release_date = d.time_id where d.year = ?1 and d.month = ?2 and d.day = ?3", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByReleaseDay(int year, int month, int day);

    @Query(value="select * from amazon_fact a join genre g on a.movie_id = g.movie_id where g.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByGenre(String genre);

    @Query(value = "select * from amazon_fact a where a.title LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByTitle(String title);

    @Query(value = "select * from amazon_fact a join actor ac on a.movie_id = ac.movie_id where ac.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByActor(String name);

    @Query(value = "select * from amazon_fact a join starring s on a.movie_id = s.movie_id where s.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByStarring(String name);

    @Query(value = "select * from amazon_fact a join director d on a.movie_id = d.movie_id where d.name LIKE ?1", nativeQuery=true)
    List<AmazonFact> findAmazonFactsByDirector(String name);

    @Query(value = "select * from AmazonFact a inner join director d on (a.movieid = d.movieid) inner JOIN genre g on (a.movieid = g.movieid) inner join actor ac on (a.movieid = ac.movieid) WHERE d.name like ?1", nativeQuery=true)
    List<AmazonFact> findCoActorAndAmazonFactByDirector(String name);
}
