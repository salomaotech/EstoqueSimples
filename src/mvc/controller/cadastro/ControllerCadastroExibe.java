package mvc.controller.cadastro;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.swings.PopulaCombobox;
import java.util.Map;
import java.util.Properties;
import mvc.model.cadastro.DaoCadastro;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class ControllerCadastroExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* carrega os comboboxes */
        carregaComboboxes(view);

        /* excessão */
        try {

            /* cadastro */
            DaoCadastro cadastro = new DaoCadastro();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jTcadastroNome.setText((String) dados.get("nome"));
            view.jTdescricao.setText((String) dados.get("descricao"));
            view.jCmedida.setSelectedItem(dados.get("medida"));
            view.jTminimo.setText((String) dados.get("estoqueMinimo"));
            view.jTvalor.setText((String) dados.get("valor"));
            view.jCcategoria.setSelectedItem(dados.get("categoria"));

            /* popula o código */
            view.jLcodigo.setText((String) id);

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void exibir(ViewSistema view) {

        /* carrega os comboboxes */
        carregaComboboxes(view);

    }

    /**
     * Carrega os comboboxes necessários
     */
    private static void carregaComboboxes(ViewSistema view) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* popula */
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCmedida, propriedades.getProperty("prop.server.tabela"), "medida");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCcategoria, propriedades.getProperty("prop.server.tabela"), "categoria");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCcadastroPesquisaCategoria, propriedades.getProperty("prop.server.tabela"), "categoria");

    }

}
