package mvc.controller.cadastro;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.algoritmos.IsNumeroInteiro;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.model.cadastro.DaoCadastro;
import mvc.model.movimentacao.RetornaDadosCadastroMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;

public class ControllerCadastroPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("nome", view.jTcadastroPesquisaNome.getText());
        entidadesPesquisa.put("id", view.jTcadastroPesquisaCodigo.getText());

        /* valida categoria de pesquisa */
        if (!isNull(view.jCcadastroPesquisaCategoria.getSelectedItem())) {

            entidadesPesquisa.put("categoria", view.jCcadastroPesquisaCategoria.getSelectedItem());

        }

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCcadastroPaginador.getSelectedItem(), new DaoCadastro().getTabela(), true);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("nome");
        colunasTabela.add("descricao");
        colunasTabela.add("medida");
        colunasTabela.add("estoqueMinimo");
        colunasTabela.add("valor");
        colunasTabela.add("categoria");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoCadastro().getTabela(), completaQuery, colunasTabela, null, "order by nome asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTcadastroLista.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* quantidade */
            BigDecimal quantidade = new RetornaDadosCadastroMovimentacao().getQuantidadeItens((String) dadosMap.get("id"));

            /* estoque mínimo */
            BigDecimal estoqueMinimo = FormataParaBigDecimal.formatar(dadosMap.get("estoqueMinimo"));

            /* saldo */
            BigDecimal valorTotal = FormataParaBigDecimal.formatar((String) dadosMap.get("valor")).multiply(quantidade);

            /* popula */
            dadosMap.put("quantidade", quantidade.toString().replace(".", ","));
            dadosMap.put("valor", FormataParaMoedaBrasileira.cifrar(dadosMap.get("valor")));
            dadosMap.put("valorTotal", FormataParaMoedaBrasileira.cifrar(valorTotal));
            dadosMap.put("estoqueMinimo", estoqueMinimo.toString().replace(".", ","));

            /* valida se é um número inteiro */
            if (IsNumeroInteiro.is(quantidade)) {

                /* popula */
                dadosMap.put("quantidade", quantidade.toBigInteger());

            }

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("nome"),
                dadosMap.get("quantidade"),
                dadosMap.get("medida"),
                dadosMap.get("valor"),
                dadosMap.get("valorTotal"),
                dadosMap.get("estoqueMinimo"),
                dadosMap.get("categoria")

            };

            /* valida se é menor do que o estoque mínimo */
            if (quantidade.compareTo(estoqueMinimo) <= 0) {

                /* vermelho */
                cores.add(Color.decode("#f49093"));

            } else {

                /* verde */
                cores.add(Color.decode("#ace0c6"));

            }

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(view.jTcadastroLista, cores);

        /* paginador */
        Paginador paginador = new Paginador(view.jCcadastroPaginador, new DaoCadastro().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
