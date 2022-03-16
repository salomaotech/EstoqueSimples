package mvc.controller.cadastro;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.IsStringNumero;
import br.com.taimber.swings.ValidaCamposNumericos;
import javax.swing.JOptionPane;
import mvc.model.cadastro.BeanCadastro;
import mvc.model.cadastro.DaoCadastro;
import mvc.model.cadastro.RetornaPodeUsarNomeCadastro;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerCadastroGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True gravou
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanCadastro cadastro = new BeanCadastro();
        cadastro.setId(id);
        cadastro.setNome(view.jTcadastroNome.getText());
        cadastro.setDescricao(view.jTdescricao.getText());
        cadastro.setMedida(view.jCmedida.getEditor().getItem());
        cadastro.setEstoqueMinimo(FormataParaBigDecimal.formatar(view.jTminimo.getText()));
        cadastro.setValor(view.jTvalor.getText());
        cadastro.setCategoria(view.jCcategoria.getEditor().getItem());

        /* cadastro */
        DaoCadastro cadastroDao = new DaoCadastro();

        /* validad dados */
        if (isDadosValidados(view, id)) {

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
    private static boolean isDadosValidados(ViewSistema view, Object id) {

        /* valida campo */
        if (view.jTcadastroNome.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe o nome do produto.");

            /* foco */
            view.jTcadastroNome.requestFocus();

            /* retorno */
            return false;

        }

        /* valida se pode usar o nome para o cadastro */
        if (!new RetornaPodeUsarNomeCadastro().isPodeUsar(view.jTcadastroNome.getText(), id)) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Já existe um cadastro com este nome!");

            /* foco */
            view.jTcadastroNome.requestFocus();

            /* retorno */
            return false;

        }

        /* valida campo */
        if (view.jTdescricao.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe a descrição do produto.");

            /* foco */
            view.jTdescricao.requestFocus();

            /* retorno */
            return false;

        }

        /* valida campo */
        if (view.jTminimo.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe o estoque mínimo.");

            /* foco */
            view.jTminimo.requestFocus();

            /* retorno */
            return false;

        }

        /* valida */
        if (!IsStringNumero.executa(view.jTminimo.getText()) && !view.jTminimo.getText().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Estoque mínimo inválido.");

            /* foco */
            view.jTminimo.requestFocus();

            /* retorno */
            return false;

        }

        /* valida campo */
        if (view.jCmedida.getEditor().getItem().equals("")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Informe a unidade de medida, exemplo KG, UN.");

            /* foco */
            view.jCmedida.requestFocus();

            /* retorno */
            return false;

        }

        /* retorno */
        return ValidaCamposNumericos.isCamposNumericosValidados(view.jTvalor);

    }

}
