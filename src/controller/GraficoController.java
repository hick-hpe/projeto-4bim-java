package controller;

import dao.VendaDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Venda;

import java.util.Map;

public class GraficoController {

    @FXML private BarChart<String, Double> graficoVendas;
    @FXML private CategoryAxis eixoX;
    @FXML private NumberAxis eixoY;

    private VendaDAO vendaDAO;

    @FXML
    public void initialize() {
        vendaDAO = new VendaDAO();
        carregarGrafico();
    }

    private void carregarGrafico() {
        try {
            Map<String, Double> dados = vendaDAO.getTotalVendasPorMes();
            XYChart.Series<String, Double> serie = new XYChart.Series<>();
            serie.setName("Vendas Mensais");

            for (String mes : dados.keySet()) {
                serie.getData().add(new XYChart.Data<>(mes, dados.get(mes)));
            }

            graficoVendas.getData().clear();
            graficoVendas.getData().add(serie);
        } catch (Exception e) {
            System.out.println("Erro ao carregar gráfico: " + e.getMessage());
        }
    }
}
