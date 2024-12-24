package inventoryManagement.test.testDAO.test;
import io.github.cdimascio.dotenv.Dotenv;
import redis.clients.jedis.Jedis;

public class redis {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String REDIS_URI = dotenv.get("REDIS_URI");
        Jedis jedis = new Jedis(REDIS_URI);
        jedis.set("name:3", "fouad");
        String name = jedis.get("client:3");
        System.out.println(name);

        jedis.close();
    }
}
