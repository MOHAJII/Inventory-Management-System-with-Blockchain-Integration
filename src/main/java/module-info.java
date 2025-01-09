module inventoryMangement{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires jbcrypt;
    requires io.github.cdimascio.dotenv.java;
    requires redis.clients.jedis;
    requires org.apache.commons.lang3;
    requires java.sql;

    opens inventoryManagement.service to javafx.fxml;
    exports inventoryManagement;
    exports inventoryManagement.dao;
    exports inventoryManagement.dao.entities;
    opens inventoryManagement.viewController to javafx.fxml;
    exports inventoryManagement.test.testDAO;
    exports inventoryManagement.dao.entities.enums;

}