package mvc.controller.movimentacao;

import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerMovimentacaoView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jTmovimentaEntrada.setText("0");
        view.jTmovimentaSaida.setText("0");
        view.jDmovimentaData.setDate(null);
        view.jTmovimentaHistorico.setText("");
        view.jTmovimentaValor.setText("0");

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBmovimentaGrava.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jBmovimentaExcluir.setEnabled(!isNull(Swap.getIdCadastroMovimentacao()));
        view.jDmovimentaData.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jTmovimentaEntrada.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jTmovimentaSaida.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jTmovimentaHistorico.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jBmovimentaPaginador.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jCmovimentaPaginador.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jBmovimentaLimpa.setEnabled(!isNull(Swap.getIdCadastro()));
        view.jTmovimentaValor.setEnabled(!isNull(Swap.getIdCadastro()));

    }

    /**
     * Limpa resultados antigos
     *
     * @param view View
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTmovimentaResultados.getModel();
        model.setNumRows(0);

    }

}
