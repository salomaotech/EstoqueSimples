package sistema.model;

public class Swap {

    private static Object idCadastro;
    private static Object idCadastroMovimentacao;

    public static Object getIdCadastro() {
        return idCadastro;
    }

    public static void setIdCadastro(Object aIdCadastro) {
        idCadastro = aIdCadastro;
    }

    public static Object getIdCadastroMovimentacao() {
        return idCadastroMovimentacao;
    }

    public static void setIdCadastroMovimentacao(Object aIdCadastroMovimentacao) {
        idCadastroMovimentacao = aIdCadastroMovimentacao;
    }

}
