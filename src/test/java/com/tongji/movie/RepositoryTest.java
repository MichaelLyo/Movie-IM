package com.tongji.movie;

import com.tongji.movie.model.*;
import com.tongji.movie.repository.*;
import com.tongji.movie.service.SearchMovieWithCombination;
import com.tongji.movie.service.SearchMovieWithRunTime;
import net.minidev.json.JSONArray;
import org.jetbrains.annotations.NotNull;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TimeDimRepository timeDimRepository;

    @Autowired
    private AmazonFactRepository amazonFactRepository;

    @Autowired
    private SearchMovieWithRunTime searchMovieWithRunTime;

    @Autowired
    private SearchMovieWithCombination searchMovieWithCombination;

    @Test
    public void testActor() throws Exception
    {
        List<Actor> actors = actorRepository.findActorsByName("Don Wilson");
    }

    @Test
    public void testTimeDim() throws Exception
    {
        List<TimeDim> timeDims = timeDimRepository.findTimeDimsByYear(2010);

        List<String> timeIds = new LinkedList<String>();
        for(TimeDim t : timeDims){
            timeIds.add(t.getTimeId());
        }


    }

    @Test
    public void testAmazonFact() throws Exception
    {
        JSONArray array = searchMovieWithCombination.searchInOracle("2000-12-03","Fire on the Amazon","Luis Llosa","Craig Sheffer","Adventure");
    }

}
