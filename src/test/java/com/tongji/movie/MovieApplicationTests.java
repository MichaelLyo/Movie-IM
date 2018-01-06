package com.tongji.movie;

import com.tongji.movie.model.AmazonFact;
import com.tongji.movie.repository.AmazonFactRepository;
import com.tongji.movie.service.DirectorService;
import oracle.jdbc.internal.OracleTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<AmazonFact> movies = directorService.findMoviesByDirectorName("Ken Burns");
		//List<AmazonFact> movies = amazonFactRepository.findByDirectorName("Ken Burns");
		//System.out.println(movies);
		for(AmazonFact item : movies)
		{
			System.out.println(item.getTitle());
		}
	}
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	//	  this.jdbcTemplate = jdbcTemplate;
	//}
	@Test
	public void testProcedure(){
		//JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.execute("call testpro(4,'pPP')");
	}

	@Test
	public void testCallableStatement()
	{
		List resultList = (List) jdbcTemplate.execute(
			new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					String storedProc = "{call testPro2(?,?)}";// 调用的sql
					CallableStatement cs = con.prepareCall(storedProc);
					cs.setString(1, "p1");// 设置输入参数的值
					cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
					return cs;
				}
			}, new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException
				{
					List resultsMap = new ArrayList();
					cs.execute();
					ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
					while (rs.next()) {// 转换每行的返回值到Map中
						Map rowMap = new HashMap();
						rowMap.put("id", rs.getInt("id"));
						rowMap.put("value", rs.getString("value"));
						resultsMap.add(rowMap);
					}
					rs.close();
					return resultsMap;
				}
			});
		for (int i = 0; i < resultList.size(); i++) {
			Map rowMap = (Map) resultList.get(i);
			String id = rowMap.get("id").toString();
			String name = rowMap.get("value").toString();
			System.out.println("id=" + id + ";value=" + name);
		}
	}

	@Test
	public void testSimpleJdbc()
	{
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("testpro2")
				. declareParameters(
						new SqlParameter("PARAM1", OracleTypes.VARCHAR),
						//new SqlOutParameter("PARAM2", Types.VARCHAR));
						new SqlOutParameter("test_cursor", OracleTypes.CURSOR));

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		//inParamMap.put("PARAM1", 6);
		inParamMap.put("PARAM1", "dadf");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> execute = call.execute(in);
		System.out.println(execute);
	}

}
