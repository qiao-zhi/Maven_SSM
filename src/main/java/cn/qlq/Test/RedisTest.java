package cn.qlq.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.qlq.bean.User;
import cn.qlq.util.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-*.xml")
@SuppressWarnings("all")
public class RedisTest {
	@Autowired
	private RedisUtil redisUtil;
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;

	@Test
	public void testSpringRedis() {
		// stringRedisTemplate的操作
		// String读写
		redisTemplate.delete("myStr");
		redisTemplate.opsForValue().set("myStr", "skyLine");
		System.out.println(redisTemplate.opsForValue().get("myStr"));
		System.out.println("---------------");

		// List读写
		redisTemplate.delete("myList");
		redisTemplate.opsForList().rightPush("myList", "T");
		redisTemplate.opsForList().rightPush("myList", "L");
		redisTemplate.opsForList().leftPush("myList", "A");
		List<String> listCache = redisTemplate.opsForList().range("myList", 0, -1);
		for (String s : listCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Set读写
		redisTemplate.delete("mySet");
		redisTemplate.opsForSet().add("mySet", "A");
		redisTemplate.opsForSet().add("mySet", "B");
		redisTemplate.opsForSet().add("mySet", "C");
		Set<String> setCache = redisTemplate.opsForSet().members("mySet");
		for (String s : setCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Hash读写
		redisTemplate.delete("myHash");
		redisTemplate.opsForHash().put("myHash", "BJ", "北京");
		redisTemplate.opsForHash().put("myHash", "SH", "上海");
		redisTemplate.opsForHash().put("myHash", "HN", "河南");
		Map<String, String> hashCache = redisTemplate.opsForHash().entries("myHash");
		for (Map.Entry entry : hashCache.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println("---------------");
	}
	
	@Test
	public void testSpringRedis2(){
		String  str = "string";//1.字符串
		List<String> list = new ArrayList<String>();//list
		list.add("0");
		list.add("中国");
		list.add("2");
		Set<String> set = new HashSet<String>();//set
		set.add("0");
		set.add("中国");
		set.add("2");
		Map<String, Object> map = new HashMap();//map
		map.put("key1", "str1");
		map.put("key2", "中国");
		map.put("key3", "str3");
		
		
		
		redisUtil.del("myStr","str");//删除数据
		
		
		//1.字符串操作
		redisUtil.set("str", str);
		redisUtil.expire("str", 120);//指定失效时间为2分钟
		String str1 = (String) redisUtil.get("str");
		System.out.println(str1);
		
		//2.list操作
		redisUtil.lSet("list", list);
		redisUtil.expire("list", 120);//指定失效时间为2分钟
		List<Object> list1 = redisUtil.lGet("list", 0, -1);
		System.out.println(list1);
			
		//3.set操作
		redisUtil.sSet("set", set);
		redisUtil.expire("set", 120);//指定失效时间为2分钟
		Set<Object> set1 = redisUtil.sGet("set");
		System.out.println(set1);
		
		
		//3.map操作
		redisUtil.hmset("map", map);
		redisUtil.expire("map", 120);//指定失效时间为2分钟
		 Map<Object, Object> map1 = redisUtil.hmget("map");
		System.out.println(map1);
		
		
	}
	@Test
	public void testSpringRedis3(){
		List<User> list = new ArrayList<User>();//list
		list.add(new User(1,"QLQ1"));
		list.add(new User(2,"QLQ2"));
		list.add(new User(3,"QLQ3"));
		
		
		Set<User> set = new HashSet<User>();//set
		set.add(new User(1,"QLQ1"));
		set.add(new User(2,"QLQ2"));
		set.add(new User(3,"QLQ3"));
		
		Map<String, Object> map = new HashMap();//map
		map.put("key1", new User(1,"QLQ1"));
		map.put("key2", new User(1,"QLQ2"));
		map.put("key3", new User(1,"QLQ3"));
		
		
		//2.list操作
		redisUtil.lSet("list", list,1200);
		List<Object> list1 = redisUtil.lGet("list", 0, -1);
		System.out.println(list1);
		
		//3.set操作
		redisUtil.sSet("set", set);
		redisUtil.expire("set", 1200);//指定失效时间为2分钟
		Set<Object> set1 = redisUtil.sGet("set");
		System.out.println(set1);
		
		
		//3.map操作
		redisUtil.hmset("map", map);
		redisUtil.expire("map", 120);//指定失效时间为2分钟
		Map<Object, Object> map1 = redisUtil.hmget("map");
		System.out.println(map1);
		
		
	}
}
