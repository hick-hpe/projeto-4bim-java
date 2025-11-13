package controller;

import dao.ClienteDAO;
import model.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.table.TableColumn;

public class ClienteController {
    @FXML private TableView<Cliente> tableView;

    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colTelefone;
    @FXML private TableColumn<Cliente, String> colEndereco;

    @FXML private TextField txtNome, txtEmail, txtTelefone, txtEndereco;
    //pode ser feito @FXML private TextField txtNome; (linha por linha, dado por dado(ele fez assim no "quadro"))

    //Variáveis de controle do controle (foi o que o professor disse)
    private ObservableList<Cliente> clenteLista;

    private ClienteDAO clienteDAO;

    @FXML
    public void inicializacao(){
        clienteDAO = new ClienteDAO;
        //criando a lista
        clienteLista = FXCollections.observableArrayList();

        tableView.setItems(clienteLista);

        //Configurações das colunas das tarefas
        colId.setCellValueFactory(cellData -> celData.getValue().idProperty().asObject());

        colNome.setCellValueFactory(cellData -> celData.getValue().nomeProperty());

        colEmail.setCellValueFactory(cellData -> celData.getValue().emailProperty());

        colTelefone.setCellValueFactory(cellData -> celData.getValue().telefoneProperty());

        colEndereco.setCellValueFactory(cellData -> celData.getValue().enderecoProperty());

        carregarClientes();

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> delecionarCliente(newValue));
    }

    public void carregarCliente(){
        try {
            clienteLista.clear();
            clienteLista.addAll(clienteDAO.read()); //adiciona todos os clientes do BD na clienteLista
        } catch (Exception e) {
            mostrarAlerta("Erro ao carregar clientes"+e.getMessage());
        }
    }

    private void selecionarCliente(Cliente cliente){
        //verificar se o cliente ñ é null
        if (cliente != null) {
            txtNome.setText(cliente.getNome);
            txtEmail.setText(cliente.getEmail);
            txtTelefone.setText(cliente.getTelefone);
            txtEndereco.setText(cliente.getEndereco);
        }
    }
    
    @FXML
    private void handleSalvar(){
        if(validarCampos()){
            try {
                Cliente cliente = new Cliente();
                
                cliente.setNome(txtNome.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtTelefone.getText());
                cliente.setEndereco(txtEndereco.getText());

                clienteDAO.create(cliente);

                carregarCliente();
                limparCampos();

                mostrarAlerta("Cliente salvo com sucesso!!!", Alert.AlertType.INFORMATION);
            } catch(Exception e){
                mostrarAlerta("Erro ao salvar Cliente"+ e.getMessage());
            }
        }
    }

    public void handleAtualizar(){
        Cliente clienteSelecionado = tableView.getSelectionModel.getSelectedItem();

        if(clienteSelecionado != null && validarCampos()){
            try {
                clienteSelecionado.setNome(txtNome.getText());
                clienteSelecionado.setEmail(txtEmail.getText());
                clienteSelecionado.setTelefone(txtTelefone.getText());
                clienteSelecionado.setEndereco(txtEndereco.getText());

                clienteDAO.update(clienteSelecionado);

                carregarCliente();
                limparCampos();

                mostrarAlerta("Cliente atualizado com sucesso!!!", Alert.AlertType.INFORMATION);
            } catch(Exception e){
                mostrarAlerta("Erro ao salvar Cliente"+ e.getMessage());
            }
        } else{
            mostrarAlerta("Selecione um Cliente para atualizar!");
        }
    }

    public void handleExcluir(){
        Cliente clienteSelecionado = tableView.getSelectionModel.getSelectedItem();

        if(clienteSelecionado != null){
            try {
                clienteDAO.delete(clienteSelecionado.getId());

                carregarCliente();

                limparCampos();

                mostrarAlerta("Cliente excluído com sucesso!", Alert.AlertType.INFORMATION);
            } catch(Exception e){
                mostrarAlerta("Erro ao excluir Cliente"+ e.getMessage());
            }
        } else{
            mostrarAlerta("Selecione um Cliente para excluir!");
        }
    }

    @FXML
    private void handleLimpar(){
        limparCampos();

        tableView.getSelectionModel().clearSelection();
    }

    private void limparCampos(){
        txtNome.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtEndereco.clear();
    }

    private boolean validarCampos(){
        if(txtNome.getText().isEmpty()){
            mostrarAlerta("Nome é obrigatório!")
            return false;
        }
        return true;
    }
        
    private void mostrarAlerta(String msg){
        mostrarAlerta(msg, Alert,AlertType.ERROR);

    }

    private void mostrarAlerta(String msg, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);

        alert.setContentText(msg);
        
        alert.showAndWait();
    }

}