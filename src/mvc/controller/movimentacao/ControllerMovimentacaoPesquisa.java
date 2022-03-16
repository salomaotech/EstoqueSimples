package mvc.controller.movimentacao;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import mvc.model.movimentacao.DaoMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;
import sistema.model.Swap;

public class ControllerMovimentacaoPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("idProduto", Swap.getIdCadastro());

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCmovimentaPaginador.getSelectedItem(), new DaoMovimentacao().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("idProduto");
        colunasTabela.add("entrada");
        colunasTabela.add("saida");
        colunasTabela.add("historico");
        colunasTabela.add("data");
        colunasTabela.add("valor");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoMovimentacao().getTabela(), completaQuery, colunasTabela, null, "order by str_to_date(data, '%d/%m/%Y') asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTmovimentaResultados.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* saldo */
        BigDecimal saldo = new BigDecimal("0");

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* entrada e saída */
            BigDecimal entrada = FormataParaBigDecimal.formatar(dadosMap.get("entrada"));
            BigDecimal saida = FormataParaBigDecimal.formatar(dadosMap.get("saida"));

            /* valor */
            BigDecimal valor = FormataParaBigDecimal.formatar(dadosMap.get("valor"));

            /* calcula o saldo */
            saldo = saldo.add(entrada.subtract(saida));

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("data"),
                FormataParaMoedaBrasileira.cifrar(valor),
                entrada,
                saida,
                saldo

            };

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(view.jTmovimentaResultados, cores);

        /* paginador */
        Paginador paginador = new Paginador(view.jCmovimentaPaginador, new DaoMovimentacao().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
