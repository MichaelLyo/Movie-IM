package com.tongji.movie.service;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.model.Director;
import com.tongji.movie.repository.AmazonFactRepository;
import com.tongji.movie.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by LSL on 2018/1/5
 */
@Component
public class DirectorService
{
	@Autowired
	private DirectorRepository directorRepository;
	@Autowired
	private AmazonFactRepository amazonFactRepository;

	public List<AmazonFact> findMoviesByDirectorName(String director)
	{
		List<Director> directors = directorRepository.findDirectorsByName(director);
		List<String> list = new LinkedList<String>();
		for(Director item : directors)
		{
			list.add(item.getMovieId());
		}
		return amazonFactRepository.findAmazonFactsByMovieIdIn(list);
	}

}
