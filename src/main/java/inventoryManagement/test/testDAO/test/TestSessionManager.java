package inventoryManagement.test.testDAO.test;

import inventoryManagement.dao.entities.User;
import inventoryManagement.dao.entities.enums.UserRole;
import inventoryManagement.service.SessionManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.types.ObjectId;

import java.util.Map;

public class TestSessionManager {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String REDIS_URI = dotenv.get("REDIS_URI");
        SessionManager sessionManager = new SessionManager(REDIS_URI, 3600);
        User user = new User();
        user.setUserName("Omar");
        user.setRole(UserRole.ADMIN);
        user.setId(new ObjectId());
        String sessionId = sessionManager.createSession(user);
        System.out.println("New session aded " + sessionId);
        Map<String, String> sessionInfo = sessionManager.getSession(sessionId);
        System.out.println(sessionInfo.values());
    }
}
