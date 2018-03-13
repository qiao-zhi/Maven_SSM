package cn.qlq.jedis;
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.util.Set;  
import java.util.concurrent.locks.ReadWriteLock;  
import java.util.concurrent.locks.ReentrantReadWriteLock;  
  
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;  
import org.apache.ibatis.cache.Cache;  
  
import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
  
  
public class JedisCache implements Cache {  
    static JedisPool jp=null;  
    private String id;  
    //构造器  
    public JedisCache(String id) {  
        //redis池只需要初始化一次  
        if(jp==null){  
            try {  
                //关于池的一些配置  
                GenericObjectPoolConfig gopc=new GenericObjectPoolConfig();  
                //最大连接数  
                gopc.setMaxTotal(100);  
                //最大排队数  
                gopc.setMaxIdle(10);  
                  
                //创建一个redis连接池  
                jp=new JedisPool(gopc, "localhost");  
                //启动一个redis  检查池创建是否正常  
                Jedis redis = jp.getResource();  
                //检查没错就回收回去  
                jp.returnResourceObject(redis);  
            } catch (Exception e) {  
                  
                //出错时就不创建redis连接池  
                jp=null;  
            }  
        }  
        this.id = id;  
    }  
  
    static class SeqUtils{  
        //反序列化为对象  
        public static Object deSer(byte[] bt) throws ClassNotFoundException, IOException{  
            ByteArrayInputStream bais=new ByteArrayInputStream(bt);  
            ObjectInputStream ois=new ObjectInputStream(bais);  
            return ois.readObject();  
        }  
          
        //对象序列化成字节数组  
        public static byte[] ser(Object obj) throws IOException{  
            ByteArrayOutputStream baos=new ByteArrayOutputStream();  
            ObjectOutputStream obos=new ObjectOutputStream(baos);  
            obos.writeObject(obj);  
            return baos.toByteArray();  
        }  
          
    }  
      
      
    @Override  
    public void clear() {  
        Jedis redis = jp.getResource();  
        //将所有的缓存清空 所有对象都清空  
        redis.flushAll();  
          
        jp.returnResourceObject(redis);  
    }  
  
    /** 
     * 这个方法返回的是mapper查询的id 
     * */  
    @Override  
    public String getId() {  
          
        return id;  
    }  
  
    /** 
     * mybatis自动调用该方法 判断返回值是否为null 
     *   如果为空  自动查询数据库   
     *   不为空  直接使用 返回的对象 
     *   key 才是 传入当前查询的oid 主键 
     */  
    //程序一进来首先检查key是否存在如果存在的话就不从数据库查   
    @Override  
    public Object getObject(Object key) {  
        if(jp==null){  
            return null;  
        }  
        Jedis redis = jp.getResource();  
        try {  
            byte[] bs = redis.get(SeqUtils.ser(key));  
              
            if(bs!=null){  
                Object obj = SeqUtils.deSer(bs);  
                jp.returnResourceObject(redis);  
                return obj;  
            }  
              
              
        } catch (IOException e) {  
              
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
              
            e.printStackTrace();  
        }  
        jp.returnResourceObject(redis);  
          
        return null;  
    }  
  
      
    /** 
     * 非阻塞式io  non-blocking io   
     * */  
    @Override  
    public ReadWriteLock getReadWriteLock() {  
        // TODO Auto-generated method stub  
        return new ReentrantReadWriteLock();  
    }  
  
    /** 
     * 用于读取 redis中缓存了多少元素 
     */  
    @Override  
    public int getSize() {  
          
        Jedis jedis=jp.getResource();  
          
        //表示匹配所有的key  
        Set<String> alllElemebts=jedis.keys("*");  
          
        return alllElemebts.size();  
    }  
  
    /** 
     * 第一次查询数据库后 mybatis会自动调用 该方法将数据写入缓存 
     */  
    @Override  
    public void putObject(Object key, Object value) {  
        if(jp==null){  
            return;  
        }  
          
        Jedis jedis=jp.getResource();  
        try {  
            //因为只能传字节数组  所以我们需要将key和value转成字节数组  
            jedis.set(SeqUtils.ser(key),SeqUtils.ser(value));  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        jp.returnResourceObject(jedis);  
    }  
  
    /** 
     * 移出缓存中一个键 
     * */  
    @Override  
    public Object removeObject(Object key) {  
        Jedis jedis=jp.getResource();  
          
        try {  
            jedis.del(SeqUtils.ser(key));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        jp.returnResourceObject(jedis);  
        return null;  
    }  
  
} 