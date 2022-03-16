package mvc.controller.movimentacao;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mvc.controller.cadastro.ControllerCadastroPesquisa;
import mvc.model.movimentacao.DaoMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

public class ControllerMovimentacaoEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBmovimentaGrava.addActionListener((ActionEvent e) -> {

            /* grava */
            if (ControllerMovimentacaoGrava.gravar(view, Swap.getIdCadastroMovimentacao())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* botão excluir */
        view.jBmovimentaExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoMovimentacao().getTabela(), Swap.getIdCadastroMovimentacao())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* resultados */
        view.jTmovimentaResultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdCadastroMovimentacao(view.jTmovimentaResultados.getModel().getValueAt(view.jTmovimentaResultados.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerMovimentacaoExibe.exibir(view, Swap.getIdCadastroMovimentacao());

                    /* seleciona aba */
                    view.jTmovimentarHistorico.setSelectedIndex(0);

                    /* exibe os controles */
                    ControllerMovimentacaoView.habilitaControles(view);

                }
            }

        });

        /* botao paginador pesquisa */
        view.jBmovimentaPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerMovimentacaoPesquisa.pesquisar(view);

        });

        /* botão limpar */
        view.jBmovimentaLimpa.addActionListener((ActionEvent e) -> {

            /* reseta os controles */
            ControllerMovimentacaoView.resetaControles(view);

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastroMovimentacao(null);

        /* reseta e habilita controles */
        ControllerMovimentacaoView.resetaControles(view);
        ControllerMovimentacaoView.habilitaControles(view);

        /* pesquisa */
        ControllerMovimentacaoPesquisa.pesquisar(view);
        ControllerCadastroPesquisa.pesquisar(view);

        /* seleciona a aba */
        view.jTmovimentarHistorico.setSelectedIndex(1);

    }

}
