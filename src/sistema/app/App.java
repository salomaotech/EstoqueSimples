package sistema.app;

import javax.swing.JOptionPane;
import mvc.controller.cadastro.ControllerCadastroEventos;
import mvc.controller.cadastro.ControllerCadastroExibe;
import mvc.controller.cadastro.ControllerCadastroPesquisa;
import mvc.controller.configuracoes.ControllerConfiguracoesEventos;
import mvc.controller.configuracoes.ControllerConfiguracoesExibe;
import mvc.controller.movimentacao.ControllerMovimentacaoEventos;
import mvc.controller.relatorio.ControllerRelatorioEventos;
import mvc.model.cadastro.DaoCadastro;
import mvc.model.configuracoes.CarregaConfiguracoes;
import mvc.model.movimentacao.DaoMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.CriaBancoDados;

public class App {

    /**
     * Main
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {

        /* configurações */
        CarregaConfiguracoes configuracoes = new CarregaConfiguracoes();

        /* view */
        ViewSistema view = new ViewSistema();

        /* valida se as configurações funcionaram */
        if (configuracoes.isConfiguracoesFuncionaram()) {

            /* 1 cria o banco de dados */
            new CriaBancoDados().criarBanco();

            /* 2 cria as tabelas */
            new DaoCadastro().criaEntidade();
            new DaoMovimentacao().criaEntidade();

            /* 3 pesquisa os dados */
            ControllerCadastroPesquisa.pesquisar(view);

            /* 4 adiciona eventos */
            ControllerCadastroEventos.addEventos(view);
            ControllerMovimentacaoEventos.addEventos(view);
            ControllerRelatorioEventos.addEventos(view);

            /* 5 carrega dados da view necessários */
            ControllerCadastroExibe.exibir(view);

        } else {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "O sistema não conseguiu carregar as configurações.");

        }

        /* adiciona eventos */
        ControllerConfiguracoesEventos.addEventos(view);

        /* carrega a view das configurações */
        ControllerConfiguracoesExibe.exibir(view);

        /* seleciona aba */
        view.jTabaPrincipal.setSelectedIndex(0);

        /* exibe a view */
        view.setVisible(true);

    }

}
