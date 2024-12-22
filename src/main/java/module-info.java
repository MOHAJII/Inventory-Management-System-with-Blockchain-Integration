module inventoryMangement{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.logging;
    requires java.desktop;
    requires jbcrypt;
    requires io.github.cdimascio.dotenv.java;

    opens inventoryManagement.controller to javafx.fxml;
    exports inventoryManagement;
    exports inventoryManagement.dao;
    exports inventoryManagement.dao.entities;
    opens inventoryManagement.viewControler to javafx.fxml;
    exports inventoryManagement.test.testDAO;

}