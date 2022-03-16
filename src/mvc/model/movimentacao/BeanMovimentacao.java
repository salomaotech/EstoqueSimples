package mvc.model.movimentacao;

public class BeanMovimentacao {

    private Object id;
    private Object idProduto;
    private Object entrada;
    private Object saida;
    private Object historico;
    private Object data;
    private Object valor;

    public BeanMovimentacao() {

    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Object idProduto) {
        this.idProduto = idProduto;
    }

    public Object getEntrada() {
        return entrada;
    }

    public void setEntrada(Object entrada) {
        this.entrada = entrada;
    }

    public Object getSaida() {
        return saida;
    }

    public void setSaida(Object saida) {
        this.saida = saida;
    }

    public Object getHistorico() {
        return historico;
    }

    public void setHistorico(Object historico) {
        this.historico = historico;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

}
