package mvc.controller.movimentacao;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.IsStringNumero;
import br.com.taimber.swings.ValidaCamposNumericos;
import javax.swing.JOptionPane;
import mvc.model.movimentacao.BeanMovimentacao;
import mvc.model.movimentacao.DaoMovimentacao;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerMovimentacaoGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True gravou
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanMovimentacao cadastro = new BeanMovimentacao();
        cadastro.setId(id);
        cadastro.setIdProduto(Swap.getIdCadastro());
        cadastro.setEntrada(FormataParaBigDecimal.formatar(view.jTmovimentaEntrada.getText()));
        cadastro.setSaida(FormataParaBigDecimal.formatar(view.jTmovimentaSaida.getText()));
        cadastro.setHistorico(view.jTmovimentaHistorico.getText());
        cadastro.setData(Datas.converterDateParaString(view.jDmovimentaData.getDate()));
        cadastro.setValor(FormataParaBigDecimal.formatar(view.jTmovimentaValor.getText()));

        /* cadastro */
        DaoMovimentacao cadastroDao = new DaoMovimentacao();

        /* validad dados */
        if (isDadosValidados(view)) {

            /* grava */
            if (cadastroDao.gravar(cadastro)) {

                /* Informa que o cadastro foi realizado */
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

                /* retorno */
                return true;

            }

        }

        /* retorno */
        return false;

    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view) {

        /* valida */
        if (!IsStringNumero.executa(view.jTmovimentaEntrada.getText()) && !view.jTmovimentaEntrada.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe uma entrada válida, exemplo 25.");

            /* foco */
            view.jTmovimentaEntrada.requestFocus();

            /* retorno */
            return false;

        }

        /* valida */
        if (!IsStringNumero.executa(view.jTmovimentaSaida.getText()) && !view.jTmovimentaSaida.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe uma saida válida, exemplo 27.");

            /* foco */
            view.jTmovimentaSaida.requestFocus();

            /* retorno */
            return false;

        }

        /* valida se as quantidades foram informadas */
        if (view.jTmovimentaEntrada.getText().equals("") && view.jTmovimentaSaida.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe uma entrada, ou saida.");

            /* retorno */
            return false;

        }

        /* retorno */
        return ValidaCamposNumericos.isCamposNumericosValidados(view.jTmovimentaValor);

    }

}
