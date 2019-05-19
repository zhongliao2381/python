package recommend;

import redis.clients.jedis.Jedis;

public class redistest {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost");
		jedis.set("tao", "hi");
		System.out.println(jedis.get("tao"));
		
	}
}
