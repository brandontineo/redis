package redis.demo.redistest;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
/**
 * Redis based demo application on Java
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Config config = new Config();
        
        // use single Redis server       
        // ./redis-server --port 6380 --slaveof 127.0.0.1 6379
        String thisPath = new File("").getAbsolutePath();
		config = Config.fromJSON(new File(thisPath + "/src/main/resources/config.json"));

        
        RedissonClient redisson = Redisson.create(config);
        
        // perform operations
        RBucket<String> bucket = redisson.getBucket("simpleObject");
        bucket.set("This is object value");
        
        
        RMap<String, String> map = redisson.getMap("simpleMap");
        map.put("Brandon", "26");
        map.put("Steven", "27");
        
        
        String objectValue = bucket.get();
        System.out.println("stored object value: " + objectValue);
        
        String mapValue = map.get("Brandon");
        System.out.println("stored map value: " + mapValue);
        
        
        redisson.shutdown();
    }
}