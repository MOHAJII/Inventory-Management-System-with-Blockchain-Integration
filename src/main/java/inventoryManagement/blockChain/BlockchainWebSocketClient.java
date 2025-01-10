package inventoryManagement.blockChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.websocket.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ClientEndpoint
public class BlockchainWebSocketClient {
    private Session session;
    private final String serverUri;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BlockchainWebSocketClient(String serverUri) {
        this.serverUri = serverUri;
    }

    public void connect() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(serverUri));
            System.out.println("Connected to blockchain server");
        } catch (Exception e) {
            System.err.println("WebSocket connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("WebSocket session established");
    }

    public void sendTransaction(String operation, String productId, long quantity, long price, String location) {
        try {
            Map<String, Object> transaction = new HashMap<>();
            transaction.put("operation", operation);
            transaction.put("productId", productId);
            transaction.put("quantity", quantity);
            transaction.put("price", price);
            if (location != null) {
                transaction.put("location", location);
            }

            String jsonMessage = objectMapper.writeValueAsString(transaction);
            session.getBasicRemote().sendText(jsonMessage);
            System.out.println("Sent transaction: " + jsonMessage);
        } catch (Exception e) {
            System.err.println("Failed to send transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received from server: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        this.session = null;
        System.out.println("WebSocket closed: " + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
        throwable.printStackTrace();
    }
}