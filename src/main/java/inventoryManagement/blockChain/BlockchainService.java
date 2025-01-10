package inventoryManagement.blockChain;

public class BlockchainService {
    private static BlockchainService instance;
    private final BlockchainWebSocketClient client;

    private BlockchainService() {
        client = new BlockchainWebSocketClient(BlockchainConfig.WEBSOCKET_URI);
        client.connect();
    }

    public static BlockchainService getInstance() {
        if (instance == null) {
            instance = new BlockchainService();
        }
        return instance;
    }

    public void sendTransaction(String operation, String productId, long quantity, long price, String location) {
        client.sendTransaction(operation, productId, quantity, price, location);
    }
}