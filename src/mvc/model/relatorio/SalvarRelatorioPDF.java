package mvc.model.relatorio;

import br.com.taimber.arquivos.GerarPdf;
import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.algoritmos.IsNumeroInteiro;
import br.com.taimber.arquivos.DialogoSalvarArquivo;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mvc.model.cadastro.DaoCadastro;
import static java.util.Objects.isNull;
import mvc.model.movimentacao.RetornaDadosCadastroMovimentacao;
import sistema.model.BancoFactory;

public class SalvarRelatorioPDF {

    private LinkedHashMap entidades;

    /**
     * Atualiza lista de entidades a serem pesquisadas
     *
     * @param entidades Entidades a serem pesquisadas
     */
    public void setEntidades(LinkedHashMap entidades) {

        /* popula entidades */
        this.entidades = entidades;

    }

    /**
     * Monta condição de pesquisa
     *
     * @return String com condição de pesquisa
     */
    private String montaCondicaoPesquisa() {

        /* completa query */
        return new SqlCompletaQuery(this.entidades, 0, new DaoCadastro().getTabela(), true).completar(false, null, null) + " order by " + new DaoCadastro().getTabela() + ".nome asc";

    }

    /**
     * Salva o relatório
     *
     * @return True conseguiu salvar o relatório
     */
    public boolean salvar() {

        /* endereço onde será salvo o arquivo */
        String enderecoSalvar = DialogoSalvarArquivo.executar();

        /* valida endereço para salvar arquivo */
        if (isNull(enderecoSalvar)) {

            /* retorno */
            return false;

        }

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* dados */
        List dados = banco.getBanco().consultarRegistro("select *from " + new DaoCadastro().getTabela() + " " + montaCondicaoPesquisa());

        /* novo PDF */
        GerarPdf pdf = new GerarPdf(enderecoSalvar);

        /* valor de todos os produtos somados */
        BigDecimal valorTotalProdutosSomados = new BigDecimal("0");

        /* quantidade total de produtos */
        BigDecimal quantidadeTotalProdutos = new BigDecimal("0");

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* quantidade */
            BigDecimal quantidade = new RetornaDadosCadastroMovimentacao().getQuantidadeItens((String) dadosMap.get("id"));

            /* saldo */
            BigDecimal valorTotal = FormataParaBigDecimal.formatar((String) dadosMap.get("valor")).multiply(quantidade);

            /* atualiza o valor total de todos os produtos somados */
            valorTotalProdutosSomados = valorTotalProdutosSomados.add(valorTotal);

            /* atualiza a quantidade total */
            quantidadeTotalProdutos = quantidadeTotalProdutos.add(FormataParaBigDecimal.formatar("1"));

            /* valida se é um número inteiro */
            if (IsNumeroInteiro.is(quantidade)) {

                /* popula */
                dadosMap.put("quantidade", String.valueOf(quantidade.toBigInteger()));

            } else {

                /* popula */
                dadosMap.put("quantidade", String.valueOf(quantidade));

            }

            /* conteudo */
            String conteudo = ""
                    + "COD: " + dadosMap.get("id")
                    + " ******* "
                    + dadosMap.get("nome")
                    + "\n"
                    + "MEDIDA: " + dadosMap.get("medida")
                    + "\n"
                    + "QUANTIDADE: " + dadosMap.get("quantidade")
                    + "\n"
                    + "VALOR UNIDADE R$: " + FormataParaMoedaBrasileira.cifrar(dadosMap.get("valor"))
                    + "\n"
                    + "CATEGORIA: " + dadosMap.get("categoria")
                    + "\n"
                    + "VALOR TOTAL R$: " + FormataParaMoedaBrasileira.cifrar(valorTotal)
                    + "\n"
                    + "-----------------------------------------------------------------------------------------"
                    + "";

            /* add linha */
            pdf.addLinha(conteudo);

        }

        /* titulo */
        String título = ""
                + "Saldo do estoque: " + Datas.getDataAtualDiaMesAnoComHoraMinuto()
                + "\n"
                + "Itens cadastrados: " + quantidadeTotalProdutos
                + "\n"
                + "Total: " + FormataParaMoedaBrasileira.cifrar(valorTotalProdutosSomados)
                + "";

        /* gera o PDF */
        pdf.gerar(título);

        /* retorno */
        return true;

    }

}
