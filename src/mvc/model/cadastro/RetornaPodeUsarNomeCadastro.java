package mvc.model.cadastro;

import br.com.taimber.algoritmos.RemoveCaracteresEspeciaisString;
import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class RetornaPodeUsarNomeCadastro {

    private final String tabela;

    /**
     * Construtor
     */
    public RetornaPodeUsarNomeCadastro() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabela");

    }

    /**
     * Retorna se pode usar o nome para o cadastro
     *
     * @param nomePesquisa Nome a ser pesquisado
     * @param id ID do produto
     * @return True pode usar
     */
    public boolean isPodeUsar(String nomePesquisa, Object id) {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("nome");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa;

        /* trata nome de pesquisa */
        nomePesquisa = RemoveCaracteresEspeciaisString.executa(nomePesquisa);

        /* valida id */
        if (!isNull(id)) {

            /* condição de pesquisa */
            condicaoPesquisa = "where nome='" + nomePesquisa + "' AND id!='" + id + "'";

        } else {

            /* condição de pesquisa */
            condicaoPesquisa = "where nome='" + nomePesquisa + "'";

        }

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, condicaoPesquisa).isEmpty();

    }

}
