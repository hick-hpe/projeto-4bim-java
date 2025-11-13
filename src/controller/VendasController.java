package controller;

import dao.VendaDAO;
import model.Venda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VendasController {

    @FXML private TableView<Venda> tableView;
    @FXML private TableColumn<Venda, Integer> colId;
    @FXML private TableColumn<Venda, String> colCliente;
    @FXML private TableColumn<Venda, String> colData;
    @FXML private TableColumn<Venda, Double> colTotal;

    private ObservableList<Venda> vendaLista;
    private VendaDAO vendaDAO;

    @FXML
    public void initialize() {
        vendaDAO = new VendaDAO();
        vendaLista = FXCollections.observableArrayList();
        tableView.setItems(vendaLista);

        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colCliente.setCellValueFactory(cell -> cell.getValue().clienteProperty());
        colData.setCellValueFactory(cell -> cell.getValue().dataProperty());
        colTotal.setCellValueFactory(cell -> cell.getValue().totalProperty().asObject());

        carregarVendas();
    }

    private void carregarVendas() {
        try {
            vendaLista.clear();
            vendaLista.addAll(vendaDAO.read());
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String msg) {
        mostrarAlerta(msg, Alert.AlertType.ERROR);
    }

    private void mostrarAlerta(String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
