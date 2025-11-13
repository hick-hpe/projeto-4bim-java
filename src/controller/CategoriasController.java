package controller;

import dao.CategoriaDAO;
import model.Categoria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CategoriasController {

    @FXML private TableView<Categoria> tableView;
    @FXML private TableColumn<Categoria, Integer> colId;
    @FXML private TableColumn<Categoria, String> colNome;

    @FXML private TextField txtNome;

    private ObservableList<Categoria> categoriaLista;
    private CategoriaDAO categoriaDAO;

    @FXML
    public void initialize() {
        categoriaDAO = new CategoriaDAO();
        categoriaLista = FXCollections.observableArrayList();
        tableView.setItems(categoriaLista);

        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());

        carregarCategorias();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> selecionarCategoria(newSel)
        );
    }

    private void carregarCategorias() {
        try {
            categoriaLista.clear();
            categoriaLista.addAll(categoriaDAO.read());
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar categorias: " + e.getMessage());
        }
    }

    private void selecionarCategoria(Categoria categoria) {
        if (categoria != null) {
            txtNome.setText(categoria.getNome());
        }
    }

    @FXML
    private void handleSalvar() {
        if (validarCampos()) {
            try {
                Categoria c = new Categoria();
                c.setNome(txtNome.getText());
                categoriaDAO.create(c);
                carregarCategorias();
                limparCampos();
                mostrarAlerta("Categoria salva com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao salvar categoria: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleAtualizar() {
        Categoria selecionada = tableView.getSelectionModel().getSelectedItem();
        if (selecionada != null && validarCampos()) {
            try {
                selecionada.setNome(txtNome.getText());
                categoriaDAO.update(selecionada);
                carregarCategorias();
                limparCampos();
                mostrarAlerta("Categoria atualizada com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao atualizar categoria: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Selecione uma categoria para atualizar!");
        }
    }

    @FXML
    private void handleExcluir() {
        Categoria selecionada = tableView.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            try {
                categoriaDAO.delete(selecionada.getId());
                carregarCategorias();
                limparCampos();
                mostrarAlerta("Categoria excluída com sucesso!", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Erro ao excluir categoria: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Selecione uma categoria para excluir!");
        }
    }

    private void limparCampos() {
        txtNome.clear();
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
