package cn.qlq.Test;


import org.junit.Test;
import redis.clients.jedis.Jedis;

public class test1 {

	private static final String host = "qiaoliqiang.cn";  
    
    private static final int post = 7000;  
      
      
    public static void main(String[] args) {  
          
        Jedis jedis = new Jedis(host, post);  
        System.out.println(jedis.ping());  
        for(int i=10919;i<10920;i++) {  
            System.out.println(jedis.clusterAddSlots(i));  
        }  
        System.out.println("结束");  
    } 
}
