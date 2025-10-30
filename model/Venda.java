package model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Venda {
    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> dataVenda;
    private final IntegerProperty clienteId;
    private final IntegerProperty produtoId;
    private final IntegerProperty quantidade;
    private final DoubleProperty valorTotal;

    public Venda() {
        this.id = new SimpleIntegerProperty();
        this.dataVenda = new SimpleObjectProperty<>();
        this.clienteId = new SimpleIntegerProperty();
        this.produtoId = new SimpleIntegerProperty();
        this.quantidade = new SimpleIntegerProperty();
        this.valorTotal = new SimpleDoubleProperty();
    }

    public Venda(LocalDate dataVenda, int clienteId, int produtoId, int quantidade, double valorTotal) {
        this();
        this.dataVenda.set(dataVenda);
        this.clienteId.set(clienteId);
        this.produtoId.set(produtoId);
        this.quantidade.set(quantidade);
        this.valorTotal.set(valorTotal);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public LocalDate getDataVenda() {
        return dataVenda.get();
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda.set(dataVenda);
    }

    public ObjectProperty<LocalDate> dataVendaProperty() {
        return dataVenda;
    }

    public int getClienteId() {
        return clienteId.get();
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }

    public IntegerProperty clienteIdProperty() {
        return clienteId;
    }

    public int getProdutoId() {
        return produtoId.get();
    }

    public void setProdutoId(int produtoId) {
        this.produtoId.set(produtoId);
    }

    public IntegerProperty produtoIdProperty() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
    }

    public IntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal.get();
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal.set(valorTotal);
    }

    public DoubleProperty valorTotalProperty() {
        return valorTotal;
    }
}
