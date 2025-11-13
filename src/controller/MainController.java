package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML
    private void abrirCliente(){
        carregarTela("view/ClientesView.fxml", "Gerenciar Clientes");
    }


    @FXML
    private void abrirProdutos(){
        carregarTela("view/ProdutosView.fxml", "Gerenciar Produtos");
    }

    @FXML
    private void abrirCategoria(){
        carregarTela("view/CategoriaView.fxml", "Gerenciar Categoria");
    }

    @FXML
    private void abrirVendas(){
        carregarTela("view/VendasView.fxml", "Gerenciar Vendas");
    }


    @FXML
    private void abrirGrafico(){
        carregarTela("view/GraficoView.fxml", "Gerenciar Grafico");
    }

    private void carregarTela(String fxml, String titulo){

        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxml));

            Stage stage = new Stage();
            stage.setTitle(titulo);
    
            stage.setScene(new Scene(root));

            stage.show();

        }catch(IOException e ){

            mostrarAlerta("Erro ao carregar a tela:" +e.getMessage());

        }
    }

    private void mostrarAlerta(String msg){

        javafx.scene.control.Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Erro");

        alerta.setContentText(msg);

        alerta.showAndWait();
    }
}