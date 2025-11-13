package controller;

import dao.ProdutoDAO;
import model.Produto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProdutosController {

    @FXML private TableView<Produto> tableView;
    @FXML private TableColumn<Produto, Integer> colId;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;
    @FXML private TableColumn<Produto, Integer> colEstoque;
    @FXML private TableColumn<Produto, String> colCategoria;

    @FXML private TextField txtNome, txtPreco, txtEstoque, txtCategoria;

    private ObservableList<Produto> produtoLista;
    private ProdutoDAO produtoDAO;

    @FXML
    public void initialize() {
        produtoDAO = new ProdutoDAO();
        produtoLista = FXCollections.observableArrayList();
        tableView.setItems(produtoLista);

        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());
        colPreco.setCellValueFactory(cell -> cell.getValue().precoProperty().asObject());
        colEstoque.setCellValueFactory(cell -> cell.getValue().estoqueProperty().asObject());
        colCategoria.setCellValueFactory(cell -> cell.getValue().categoriaProperty());

        carregarProdutos();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> selecionarProduto(newSel)
        );
    }

    private void carregarProdutos() {
        try {
            produtoLista.clear();
            produtoLista.addAll(produtoDAO.read());
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void selecionarProduto(Produto produto) {
        if (produto != null) {
            txtNome.setText(produto.getNome());
            txtPreco.setText(String.valueOf(produto.getPreco()));
            txtEstoque.setText(String.valueOf(produto.getEstoque()));
            txtCategoria.setText(produto.getCategoria());
        }
    }

    @FXML
    private void handleSalvar() {
        if (validarCampos()) {
            try {
                Produto p = new Produto();
                p.setNome(txtNome.getText());
                p.setPreco(Double.parseDouble(txtPreco.getText()));
                p.setEstoque(Integer.parseInt(txtEstoque.getText()));
                p.setCategoria(txtCategoria.getText());

                produtoDAO.create(p);
                carregarProdutos();
                limparCampos();
                mostrarAlerta("Produto salvo com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao salvar produto: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleAtualizar() {
        Produto selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado != null && validarCampos()) {
            try {
                selecionado.setNome(txtNome.getText());
                selecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                selecionado.setEstoque(Integer.parseInt(txtEstoque.getText()));
                selecionado.setCategoria(txtCategoria.getText());

                produtoDAO.update(selecionado);
                carregarProdutos();
                limparCampos();
                mostrarAlerta("Produto atualizado com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao atualizar produto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Selecione um produto para atualizar!");
        }
    }

    @FXML
    private void handleExcluir() {
        Produto selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                produtoDAO.delete(selecionado.getId());
                carregarProdutos();
                limparCampos();
                mostrarAlerta("Produto excluído com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao excluir produto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Selecione um produto para excluir!");
        }
    }

    @FXML
    private void handleLimpar() {
        limparCampos();
        tableView.getSelectionModel().clearSelection();
    }

    private void limparCampos() {
        txtNome.clear();
        txtPreco.clear();
        txtEstoque.clear();
        txtCategoria.clear();
    }

    private boolean validarCampos() {
        if (txtNome.getText().isEmpty()) {
            mostrarAlerta("Nome é obrigatório!");
            return false;
        }
        return true;
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
