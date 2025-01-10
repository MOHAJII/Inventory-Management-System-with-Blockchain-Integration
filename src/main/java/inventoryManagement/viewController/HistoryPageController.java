package inventoryManagement.viewController;

import inventoryManagement.dao.entities.Transaction;
import inventoryManagement.service.TransactionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryPageController implements Initializable {

    private final TransactionService transactionService = new TransactionService();
    private List<Transaction> transactions;

    @FXML
    private GridPane transactionContainer;

    private List<Transaction> loadTransactions() {
        return transactionService.getAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactions = loadTransactions();
        int column = 0;
        int row = 1;

        try {
            for (Transaction transaction : transactions) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/transaction-card.fxml"));
                VBox transactionBox = fxmlLoader.load();
                TransactionCardController transactionCardController = fxmlLoader.getController();
                transactionCardController.setData(transaction.getQuantity(), toDate(transaction.getTimestamp()), transaction.getTransactionType(), transaction.getUserName(), transaction.getBillPath());
                if (column == 4) {
                    column = 0;
                    ++row;
                }

                transactionContainer.add(transactionBox, column++, row);
                GridPane.setMargin(transactionBox, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }


}
