package com.tongji.movie.repository;
import java.util.List;

import com.tongji.movie.model.TimeDim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TimeDimRepository extends JpaRepository<TimeDim, String>
{

    List<TimeDim> findTimeDimsByYear(int year);

    List<TimeDim> findTimeDimsByMonth(int month);

    List<TimeDim> findTimeDimsByYearAndMonth(int year, int month);

    List<TimeDim> findTimeDimsByYearAndMonthIn(int year, List<Integer> month);

    List<TimeDim> findTimeDimsByYearAndMonthAndDay(int year, int month, int day);

}
