package inventoryManagement.service;

import inventoryManagement.dao.entities.User;
import org.bson.types.ObjectId;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private final Jedis redisClient;
    private final int sessionExpiry;

    public SessionManager(String URI, int sessionExpiry) {
        this.redisClient = new Jedis(URI);
        this.sessionExpiry = sessionExpiry;
    }

    public String createSession(User user) {
        String sessionId = generateSessionId();
        String sessionKey = "session:" + sessionId;

        Map<String, String> sessionData = new HashMap<>();
        sessionData.put("userId", user.getId().toString());
        sessionData.put("role", user.getRole().toString());
        sessionData.put("userName", user.getUserName());
        sessionData.put("userEmail", user.getEmail());

        redisClient.hset(sessionKey, sessionData);
        redisClient.expire(sessionKey, sessionExpiry);
        return sessionId;
    }

    public Map<String, String> getSession(String sessionId) {
        String sessionKey = "session:" + sessionId;
        return redisClient.hgetAll(sessionKey);
    }

    public String generateSessionId() {
        return java.util.UUID.randomUUID().toString();
    }

    public void extendSession(String sessionId) {
        String sessionKey = "session:" + sessionId;
        redisClient.expire(sessionKey, sessionExpiry); // Reset expiry
    }

    public void deleteSession(String sessionId) {
        String sessionKey = "session:" + sessionId;
        redisClient.del(sessionKey);
    }

    public boolean isValidSession(String sessionId) {
        String sessionKey = "session:" + sessionId;
        return redisClient.exists(sessionKey); // Check existence
    }


    public void close() {
        redisClient.close();
    }
}
