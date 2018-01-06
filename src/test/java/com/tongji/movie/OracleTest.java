package com.tongji.movie;

import com.tongji.movie.model.TimeDim;
import com.tongji.movie.repository.TimeDimRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
}
