package mvc.model.cadastro;

import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class RetornaDadosUltimoItemCadastrado {

    private final String tabela;

    /**
     * Construtor
     */
    public RetornaDadosUltimoItemCadastrado() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabela");

    }

    /**
     * Retorna o ID do último produto cadastrado
     *
     * @return ID de último produto cadastrado
     */
    public String getIdUltimoProdutoCadastrado() {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("id");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "order by id desc limit 1";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(this.tabela, entidades, condicaoPesquisa).get(0);

        /* retorno */
        return (String) dados.get("id");

    }

}
