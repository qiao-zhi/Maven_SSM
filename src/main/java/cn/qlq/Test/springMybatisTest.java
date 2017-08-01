/*package JunitTest;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.xm.mapper.UserMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class springMybatisTest {
	@Resource(name="userMapper")
	private UserMapper userMapper;
	@Test
	public void fun1(){
		System.out.println(userMapper.findUserById(4));
	}
}
*/