package mvc.model.cadastro;

public class BeanCadastro {

    private Object id;
    private Object nome;
    private Object descricao;
    private Object medida;
    private Object estoqueMinimo;
    private Object valor;
    private Object categoria;

    public BeanCadastro() {
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getNome() {
        return nome;
    }

    public void setNome(Object nome) {
        this.nome = nome;
    }

    public Object getDescricao() {
        return descricao;
    }

    public void setDescricao(Object descricao) {
        this.descricao = descricao;
    }

    public Object getMedida() {
        return medida;
    }

    public void setMedida(Object medida) {
        this.medida = medida;
    }

    public Object getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Object estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getCategoria() {
        return categoria;
    }

    public void setCategoria(Object categoria) {
        this.categoria = categoria;
    }

}
