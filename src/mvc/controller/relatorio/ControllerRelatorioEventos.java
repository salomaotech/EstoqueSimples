package mvc.controller.relatorio;

import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import mvc.model.relatorio.SalvarRelatorioPDF;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerRelatorioEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão para exportar relatório */
        view.jBexportarRelatorio.addActionListener((ActionEvent e) -> {

            /* novo relatório */
            SalvarRelatorioPDF relatorio = new SalvarRelatorioPDF();

            /* entidades de pesquisa */
            LinkedHashMap entidades = new LinkedHashMap();
            entidades.put("nome", view.jTcadastroPesquisaNome.getText());
            entidades.put("id", view.jTcadastroPesquisaCodigo.getText());

            /* valida categoria de pesquisa */
            if (!isNull(view.jCcadastroPesquisaCategoria.getSelectedItem())) {

                entidades.put("categoria", view.jCcadastroPesquisaCategoria.getSelectedItem());

            }

            /* seta as entidades */
            relatorio.setEntidades(entidades);

            /* valida se salvou o relatório */
            if (relatorio.salvar()) {

                JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Relatório não salvo!");

            }

        });

    }

}
