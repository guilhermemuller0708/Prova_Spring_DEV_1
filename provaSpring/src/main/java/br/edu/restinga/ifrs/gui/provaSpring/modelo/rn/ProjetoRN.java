package br.edu.restinga.ifrs.gui.provaSpring.modelo.rn;

import br.edu.restinga.ifrs.gui.provaSpring.excecoes.ExcecaoProvaRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ProjetoDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Atividade;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Projeto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoRN implements RegraNegocio<Projeto> {

    @Autowired
    ProjetoDAO projetoDAO;

    @Override
    public void validarCadastrar(Projeto entidade) {
        this.valida(entidade);
    }

    @Override
    public void validarAtualizar(Projeto entidadeAtinga, Projeto entidadeNova) {
        this.valida(entidadeNova);
        if (!entidadeAtinga.getLinguagem().equalsIgnoreCase(entidadeNova.getLinguagem())) {
            throw new ExcecaoProvaRN(3004, "Linguagem não pode ser alterada ");
        }
    }

    @Override
    public void validarExcluir(Projeto entidade) {

    }

    private void valida(Projeto entidade) {
        if (entidade.getNome() == null
                || entidade.getLinguagem() == null
                || entidade.getHorasPrevistas() == 0) {
            throw new ExcecaoProvaRN(3001, "Nome, linguagem e horas previstas são obrigatórios");
        }
        if (entidade.getHorasExecutadas() != 0) {
            throw new ExcecaoProvaRN(3002, "Horas executadas não podem ser definidas pelo cliente");
        }

        if (!(entidade.getLinguagem().equalsIgnoreCase("java")
                || entidade.getLinguagem().equalsIgnoreCase("c")
                || entidade.getLinguagem().equalsIgnoreCase("python")
                || entidade.getLinguagem().equalsIgnoreCase("javascript")
                || entidade.getLinguagem().equalsIgnoreCase("objective-c")
                || entidade.getLinguagem().equalsIgnoreCase("delphi")
                || entidade.getLinguagem().equalsIgnoreCase("go")
                || entidade.getLinguagem().equalsIgnoreCase("visual basic")
                || entidade.getLinguagem().equalsIgnoreCase("kotlin"))) {
            throw new ExcecaoProvaRN(3003, "Linguagem só pode aceitar os valores: java, c, python, "
                    + "javascript, php, objective-c, delphi, go, visual basic ou kotlin");
        }
    }

}
