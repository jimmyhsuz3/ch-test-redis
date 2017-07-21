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