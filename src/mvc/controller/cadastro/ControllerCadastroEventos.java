package mvc.controller.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.util.Objects.isNull;
import mvc.controller.movimentacao.ControllerMovimentacaoPesquisa;
import mvc.controller.movimentacao.ControllerMovimentacaoView;
import mvc.model.cadastro.DaoCadastro;
import mvc.model.cadastro.RetornaDadosUltimoItemCadastrado;
import mvc.model.movimentacao.ExcluiCadastroMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

public class ControllerCadastroEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            /* valida id de cadastro aberto */
            if (!isNull(Swap.getIdCadastro())) {

                /* grava */
                if (ControllerCadastroGrava.gravar(view, Swap.getIdCadastro())) {

                    /* pós ação */
                    executarPosAcao1(view);

                }

            } else {

                /* grava */
                if (ControllerCadastroGrava.gravar(view, null)) {

                    /* atualiza o swap */
                    Swap.setIdCadastro(new RetornaDadosUltimoItemCadastrado().getIdUltimoProdutoCadastrado());

                    /* pós ação */
                    executarPosAcao1(view);

                }

            }

        });

        /* botão excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoCadastro().getTabela(), Swap.getIdCadastro())) {

                /* exclui as dependencias */
                new ExcluiCadastroMovimentacao().excluir((String) Swap.getIdCadastro());

                /* pós ação */
                executarPosAcao3(view);

            }

        });

        /* botão novo */
        view.jBcadastroNovo.addActionListener((ActionEvent e) -> {

            /* pós ação */
            executarPosAcao2(view);

        });

        /* botão novo */
        view.jBatalhoNovoProduto.addActionListener((ActionEvent e) -> {

            /* pós ação */
            executarPosAcao2(view);

        });

        /* botão pesquisa */
        view.jBcadastroPesquisa.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerCadastroPesquisa.pesquisar(view);

        });

        /* botao paginador pesquisa */
        view.jBcadastroPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerCadastroPesquisa.pesquisar(view);

        });

        /* resultados */
        view.jTcadastroLista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* pós ação */
                    executarPosAcao2(view);

                    /* atualiza o swap */
                    Swap.setIdCadastro(view.jTcadastroLista.getModel().getValueAt(view.jTcadastroLista.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerCadastroExibe.exibir(view, Swap.getIdCadastro());

                    /* seleciona aba */
                    view.jTabaPrincipal.setSelectedIndex(1);

                    /* exibe os controles */
                    ControllerCadastroView.habilitaControles(view);

                    /* exibe os dados */
                    ControllerMovimentacaoPesquisa.pesquisar(view);

                }

            }

        });

        /* pesquisa */
        view.jTcadastroPesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* enter */
                if (e.getKeyCode() == 10) {

                    /* pesquisa */
                    ControllerCadastroPesquisa.pesquisar(view);

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* pesquisa */
        view.jTcadastroPesquisaCodigo.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* enter */
                if (e.getKeyCode() == 10) {

                    /* pesquisa */
                    ControllerCadastroPesquisa.pesquisar(view);

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

    /**
     * Executar após uma ação 1
     *
     * @param view View
     */
    private static void executarPosAcao1(ViewSistema view) {

        /* exibe os dados */
        ControllerCadastroExibe.exibir(view, Swap.getIdCadastro());

        /* exibe os controles */
        ControllerCadastroView.habilitaControles(view);

        /* pesquisa */
        ControllerCadastroPesquisa.pesquisar(view);

        /* seleciona aba */
        view.jTmovimentarHistorico.setSelectedIndex(0);

    }

    /**
     * Executar após uma ação 2
     *
     * @param view View
     */
    private static void executarPosAcao2(ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastro(null);
        Swap.setIdCadastroMovimentacao(null);

        /* reseta e habilita controles */
        ControllerCadastroView.resetaControles(view);
        ControllerCadastroView.habilitaControles(view);

        /* reseta e habilita controles */
        ControllerMovimentacaoView.resetaControles(view);
        ControllerMovimentacaoView.habilitaControles(view);
        ControllerMovimentacaoView.limpaListaResultadosAntigos(view);

        /* seleciona a aba */
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jTmovimentarHistorico.setSelectedIndex(0);

        /* move o foco */
        view.jTcadastroNome.requestFocus();

    }

    /**
     * Executar após uma ação 3
     *
     * @param view View
     */
    private static void executarPosAcao3(ViewSistema view) {

        /* pós ação */
        executarPosAcao2(view);

        /* pesquisa */
        ControllerCadastroPesquisa.pesquisar(view);

    }

}
