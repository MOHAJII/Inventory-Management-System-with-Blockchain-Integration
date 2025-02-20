module inventoryMangement{
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
    requires javafx.web;
    requires com.fasterxml.jackson.databind;
    requires javax.websocket.api;
    requires java.desktop;

    opens inventoryManagement.service to javafx.fxml;
    exports inventoryManagement;
    exports inventoryManagement.dao;
    exports inventoryManagement.dao.entities;
    opens inventoryManagement.viewController to javafx.fxml;
    exports inventoryManagement.test.testDAO;
    exports inventoryManagement.dao.entities.enums;

}