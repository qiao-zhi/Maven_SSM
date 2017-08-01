package cn.qlq.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.qlq.bean.User;
import cn.qlq.mapper.UserMapper;


public class MybatisTest {

	private SqlSessionFactory  sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		//将全局配置文件作为一个流
				String resource="SqlMapConfig2.xml";
				/*String realPath = this.getClass().getClassLoader().getResource(resource).getPath();
				String realPath1 = MybatisTest.class.getClassLoader().getResource("SqlMapConfig2.xml").getPath();
				URL realPath2 = Resources.getResourceURL("SqlMapConfig2.xml");
			    System.out.println(realPath);
				System.out.println(realPath);
				System.out.println(realPath2);*/
				InputStream inputStream=Resources.getResourceAsStream(resource);
				
				//建立一个SqlSession工厂
				 sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}

	/*public void testAddZJ() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper us = sqlSession.getMapper(UserMapper.class);
		User sl=new SpecialistLibrary();
		sl.setSpecialistid("SL100011");
		sl.setName("张三");
		sl.setSex("男");
		sl.setBirthday(new Date());
		sl.setEngagedomain("脚投票弄");
		sl.setDuty("讲师");
		sl.setTitle("教授");
		sm.addSpecialist(sl);
		sqlSession.close();
//		System.out.println(user);
	}
	*/
	
	
	
//	测试删除专家
	/*@Test
	public void fun1() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		SpecialistLibraryMapper sm = sqlSession.getMapper(SpecialistLibraryMapper.class);
		SpecialistLibrary sl=new SpecialistLibrary();
		sm.deleteSpecialistById("SL100012");
		sqlSession.commit();
	}
	*/
	
	
//	测试修改
	/*@Test
	public void fun2() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		SpecialistLibraryMapper sm = sqlSession.getMapper(SpecialistLibraryMapper.class);
		SpecialistLibrary sl=new SpecialistLibrary();
		XG condition=new XG();
		condition.setSpecialistId("SL100011");
		condition.setName("改后的值！");
		sm.updateSpecialistById(condition);
		sqlSession.commit();
	}*/
	
//	测试查询所有
	/*@Test
	public void fun3() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		SpecialistLibraryMapper sm = sqlSession.getMapper(SpecialistLibraryMapper.class);
		SpecialistLibrary sl=new SpecialistLibrary();
		List<SpecialistLibrary> list = sm.findAllSpecialist();
		System.out.println(list);
	}
	*/
	
//	测试查询单个
	@Test
	public void fun4() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper us = sqlSession.getMapper(UserMapper.class);
		User user=us.findUserById(1);
		System.out.println(user);
	}
	
}
