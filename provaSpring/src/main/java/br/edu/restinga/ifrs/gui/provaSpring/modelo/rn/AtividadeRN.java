package br.edu.restinga.ifrs.gui.provaSpring.modelo.rn;

import br.edu.restinga.ifrs.gui.provaSpring.excecoes.ExcecaoProvaRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.AtividadeDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ProjetoDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Atividade;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Projeto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtividadeRN implements RegraNegocio<Atividade> {

    @Autowired
    AtividadeDAO atividadeDAO;

    @Autowired
    ProjetoDAO projetoDAO;

    @Override
    public void validarCadastrar(Atividade entidade) {
        if (entidade.getHorasExecutadas() != 0) {
            throw new ExcecaoProvaRN(4002, "Horas executadas não podem ser definidas no cadastro, somente na atualização");
        }
        this.valida(entidade);
    }

    @Override
    public void validarAtualizar(Atividade entidadeAtinga, Atividade entidadeNova) {
        this.valida(entidadeNova);
    }

    @Override
    public void validarExcluir(Atividade entidade) {

    }

    private void valida(Atividade entidade) {
        if (entidade.getDescricao() == null
                || entidade.getHorasPrevistas() == 0) {
            throw new ExcecaoProvaRN(4001, "Descricao e horas previstas são obrigatórios.");
        }

    }

    public void validarDescricao(Projeto entidade, Atividade atividade) {
        List<Atividade> atividades = entidade.getAtividades();
        for (int i = 0; i < atividades.size(); i++) {

            if (atividades.get(i).getDescricao().equals(atividade.getDescricao())) {
                throw new ExcecaoProvaRN(4004, "As atividades não podem ter descrição repetida no mesmo projeto");

            }
        }
    }
}
