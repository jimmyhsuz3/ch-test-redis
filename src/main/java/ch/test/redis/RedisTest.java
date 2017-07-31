package ch.test.redis;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
// mserver
// db, framework
// batch
// dependency, 三角
// transaction
// test
// json
// exercise

// profileSearch
// profileView
// mq

// http://docs.spring.io/spring/docs/4.2.x/javadoc-api/org/springframework/context/annotation/Bean.html
// https://docs.spring.io/spring/docs/2.0.8/javadoc-api/org/springframework/beans/factory/config/PropertyResourceConfigurer.html
// BeanFactoryPostProcessor
// http://www.atetric.com/atetric/javadoc/org.springframework.batch/spring-batch-core/3.0.0.RELEASE/org/springframework/batch/core/configuration/annotation/StepScopeConfiguration.html
// http://docs.spring.io/spring-batch/2.2.x/apidocs/org/springframework/batch/core/configuration/annotation/AbstractBatchConfiguration.html
// scope configuration.stepscope is non-static
// https://stackoverflow.com/questions/14942304/springs-javaconfig-and-customscopeconfigurer-issue
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Import.html

public class RedisTest {
	private final String host = "172.19.7.129";
	private final int db = 23;
	public static void main(String[] args){
		System.out.println(new RedisTest().testRedis());
	}
	public String testRedis(){
		StringBuilder builder = new StringBuilder();
		JedisPool pool = null;
		try {
			pool = new JedisPool(new JedisPoolConfig(), host);
			Jedis jedis =pool.getResource();
			jedis.select(db);
			Map<String, String> map = new HashMap<String, String>();
			map.put("c", "apple");
			map.put("c", "cat");
			map.put("d", "door");
			map.put("time", new java.util.Date().toString());
			builder.append(jedis.hmset("test", map)).append('\n');
			builder.append(jedis.hgetAll("test"));
		} catch (RuntimeException re){
			builder.append(re.getLocalizedMessage());
		}
		return builder.toString();
	}
}