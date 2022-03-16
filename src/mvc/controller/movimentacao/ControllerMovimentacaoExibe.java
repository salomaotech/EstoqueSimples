package mvc.controller.movimentacao;

import br.com.taimber.algoritmos.Datas;
import java.util.Map;
import mvc.model.movimentacao.DaoMovimentacao;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerMovimentacaoExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* excessão */
        try {

            /* cadastro */
            DaoMovimentacao cadastro = new DaoMovimentacao();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jTmovimentaEntrada.setText((String) dados.get("entrada"));
            view.jTmovimentaSaida.setText((String) dados.get("saida"));
            view.jDmovimentaData.setDate(Datas.converterStringParaDate((String) dados.get("data")));
            view.jTmovimentaHistorico.setText((String) dados.get("historico"));
            view.jTmovimentaValor.setText((String) dados.get("valor"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

}
