package com.tongji.movie;

import com.tongji.movie.model.TimeDim;
import com.tongji.movie.repository.TimeDimRepository;
import com.tongji.movie.service.SearchMovieWithDirector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LSL on 2018/1/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleTest
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	SearchMovieWithDirector searchMovieWithDirector;
	@Autowired
	private TimeDimRepository timeDimRepository;

	@Test
	public void selectTest()
	{
		List<TimeDim> times = timeDimRepository.findTimeDimsByYear(1991);
		for(TimeDim item : times)
		{
			System.out.println(item.getTimeId());
		}
	}
	@Test
	public void directorTest() throws SQLException
	{
		System.out.println(searchMovieWithDirector.searchCoActor("Ken Burns"));
	}
}
