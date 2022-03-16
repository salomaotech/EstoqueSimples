package mvc.model.movimentacao;

import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class ExcluiCadastroMovimentacao {

    private final String tabela;

    /**
     * Construtor
     */
    public ExcluiCadastroMovimentacao() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaMovimentacao");

    }

    /**
     * Exclui as movimentações dos produtos
     *
     * @param idProduto ID do produto
     */
    public void excluir(String idProduto) {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("id");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "where idProduto='" + idProduto + "'";

        /* dados */
        List dados = banco.getBanco().consultarRegistro(this.tabela, entidades, condicaoPesquisa);

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* banco temporário */
            banco = new BancoFactory(true);

            /* deleta o registro */
            banco.getBanco().deletarRegistro(this.tabela, (String) dadosMap.get("id"));

        }

    }

}
