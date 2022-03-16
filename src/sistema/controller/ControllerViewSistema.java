package sistema.controller;

import mvc.controller.cadastro.ControllerCadastroView;
import sistema.model.Swap;

public class ControllerViewSistema {

    /**
     * Reseta os controles da view
     *
     * @param view View
     */
    public static void resete(mvc.view.telas.sistema.ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastro(null);
        Swap.setIdCadastroMovimentacao(null);

        /* tratamentos */
        ControllerCadastroView.resetaControles(view);
        ControllerCadastroView.limpaListaResultadosAntigos(view);

        /* reseta abas */
        view.jTabaPrincipal.setSelectedIndex(1);

        /* repaint */
        view.repaint();

    }

}
