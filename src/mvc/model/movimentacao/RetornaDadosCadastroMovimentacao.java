package mvc.model.movimentacao;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.arquivos.LeitorDePropriedades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class RetornaDadosCadastroMovimentacao {

    private final String tabela;

    /**
     * Construtor
     */
    public RetornaDadosCadastroMovimentacao() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaMovimentacao");

    }

    /**
     * Retorna a quantidade de itens
     *
     * @param idProduto ID do produto
     * @return Quantidade de itens de um produto
     */
    public BigDecimal getQuantidadeItens(String idProduto) {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("SUM(entrada - saida)");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "where idProduto='" + idProduto + "'";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(this.tabela, entidades, condicaoPesquisa).get(0);

        /* retorno */
        return FormataParaBigDecimal.formatar(dados.get("SUM(entrada - saida)"));

    }

}
