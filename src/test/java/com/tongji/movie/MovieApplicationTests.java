package com.tongji.movie;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import com.tongji.movie.service.DirectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieApplicationTests {

	@Autowired
	DirectorService directorService;
	@Autowired
	AmazonFactRepository amazonFactRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSelectDirector()
	{
		//List<AmazonFact> movies = directorService.findMoviesByDirectorName("Ken Burns");
		List<AmazonFact> movies = amazonFactRepository.findByDirectorName("Ken Burns");
		//System.out.println(movies);
		for(AmazonFact item : movies)
		{
			System.out.println(item.getTitle());
		}
	}

}
