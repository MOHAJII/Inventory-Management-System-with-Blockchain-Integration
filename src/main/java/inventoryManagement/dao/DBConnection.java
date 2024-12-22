package inventoryManagement.dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBConnection {
    private static final DBConnection dbConnection;
    private static MongoClient mongoClient;
    private static ConnectionString mongoURI = null;
    private static boolean isConnected = false;

    private DBConnection() {
    }

    static {
        dbConnection = new DBConnection();
        try {
            Dotenv dotenv = Dotenv.load();
            mongoURI = new ConnectionString(dotenv.get("MONGO_URI"));
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .codecRegistry(pojoCodecRegistry)
                    .applyConnectionString(mongoURI).build();
            mongoClient = MongoClients.create(settings);
            // Check if the connection is valid by listing the available databases
            MongoIterable<String> databases = mongoClient.listDatabaseNames();
            // Just attempt to retrieve a database name to ensure connection
            databases.first();
            isConnected = true;
            System.out.println("Connection created successfully.");
        } catch (MongoException mongoException) {
            System.err.println("Unable to connect to the MongoDB instance due to an error: " + mongoException);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e);
        }
    }

    public static DBConnection getInstance() {
        if (!isConnected) {
            System.err.println("MongoDB Connection is not established");
            return null;
        }
        return dbConnection;
    }

    public static MongoDatabase getDatabase(String databaseName) {
        return mongoClient.getDatabase(databaseName);
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase("inventoryManagement");
    }

}
