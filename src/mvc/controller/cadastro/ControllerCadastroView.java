package mvc.controller.cadastro;

import static java.util.Objects.isNull;
import mvc.controller.movimentacao.ControllerMovimentacaoView;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerCadastroView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jTcadastroNome.setText("");
        view.jTdescricao.setText("");
        view.jCmedida.setSelectedIndex(-1);
        view.jTminimo.setText("0");
        view.jLcodigo.setText("###");
        view.jTvalor.setText("0");
        view.jCcategoria.setSelectedIndex(-1);

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBcadastroExcluir.setEnabled(!isNull(Swap.getIdCadastro()));

        /* habilita os controles da movimentação */
        ControllerMovimentacaoView.habilitaControles(view);

    }

    /**
     * Limpa resultados antigos
     *
     * @param view View
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

    }

}
