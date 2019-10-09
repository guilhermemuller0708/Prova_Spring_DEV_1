package br.edu.restinga.ifrs.gui.provaSpring.modelo.servico;

import br.edu.restinga.ifrs.gui.provaSpring.excecoes.ExcecaoProvaRN;
import br.edu.restinga.ifrs.gui.provaSpring.excecoes.NaoEncontrado;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.AtividadeDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ProjetoDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Atividade;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Projeto;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.AtividadeRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.ProjetoRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.RegraNegocio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjetoServico extends Servico<Projeto> {

    @Autowired
    ProjetoDAO projetoDAO;

    @Autowired
    ProjetoRN projetoRN;

    @Autowired
    AtividadeDAO atividadeDAO;

    @Autowired
    AtividadeRN atividadeRN;

    @Override
    public CrudRepository<Projeto, Integer> getDAO() {
        return projetoDAO;
    }

    @Override
    public RegraNegocio<Projeto> getRegraNegocio() {
        return projetoRN;
    }

    public Atividade cadastrarAtividade(int idProjeto, Atividade atividade) throws Throwable {
        if (!projetoDAO.existsById(idProjeto)) {
            throw new NaoEncontrado("Projeto com id: " + idProjeto + " não encontrado");
        }
        atividadeRN.validarCadastrar(atividade);

        Projeto projeto = this.recuperar(idProjeto);

        atividadeRN.validarDescricao(projeto, atividade);

        // As horas executadas devem ser calculadas pela soma das horas executadas das
        // atividades
        int horas = this.getHorasExecutadasDaAtividade(projeto.getAtividades(), atividade);
        projeto.setHorasExecutadas(horas);

        this.horasPrevistasDaAtividadePost(projeto.getAtividades(), atividade, projeto);

        atividade.setId(0);
        Atividade aividadeBanco = atividadeDAO.save(atividade);
        projeto.getAtividades().add(aividadeBanco);
        projetoDAO.save(projeto);
        return aividadeBanco;
    }

    public Atividade recuperaridAtividade(int idProjeto, int idAtividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        List<Atividade> atividades = projeto.getAtividades();
        for (Atividade atividade : atividades) {
            if (atividade.getId() == idAtividade) {
                return atividade;
            }
        }
        throw new NaoEncontrado("id " + idAtividade + " não foi encontrada");

    }

    public void atualizarAtividade(int idProjeto, Atividade atividade) throws Throwable {
        atividadeRN.validarAtualizar(atividade, atividade);
        Projeto projeto = this.recuperar(idProjeto);
        this.horasPrevistasDaAtividadePut(projeto.getAtividades(), atividade, projeto);
        atividadeRN.validarDescricao(projeto, atividade);
        this.recuperaridAtividade(idProjeto, atividade.getId());
        atividadeDAO.save(atividade);

        atividadeDAO.save(atividade);
    }

    public void excluiridAtividade(int idProjeto, int idAtividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        List<Atividade> atividades = projeto.getAtividades();

        for (Atividade atividade : atividades) {
            if (atividade.getId() == idAtividade) {
                projeto.setHorasExecutadas(projeto.getHorasExecutadas() - atividade.getHorasExecutadas());
                projeto.getAtividades().remove(atividade);
                projetoDAO.save(projeto);
                return;
            }
        }
        throw new NaoEncontrado("id " + idAtividade + " não foi encontrada");
    }

    public List<Atividade> listarAtividades(int idProjeto) throws Throwable {
        return this.recuperar(idProjeto).getAtividades();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    private int getHorasExecutadasDaAtividade(List<Atividade> atividades, Atividade atividadeInserida) {
        int totalHoras = 0;
        for (Atividade atividade : atividades) {
            totalHoras += atividade.getHorasExecutadas();
        }
        totalHoras += atividadeInserida.getHorasExecutadas();
        return totalHoras;
    }

    private void horasPrevistasDaAtividadePost(List<Atividade> atividades, Atividade atividadeInserida,
            Projeto projeto) {
        int totalHoras = 0;
        for (Atividade atividade : atividades) {
            totalHoras += atividade.getHorasPrevistas();
        }
        totalHoras += atividadeInserida.getHorasPrevistas();

        if (totalHoras > projeto.getHorasPrevistas()) {
            throw new ExcecaoProvaRN(4003,
                    "A soma das horas previstas das atividades não pode exceder as horas previstas do projeto");
        }
    }

    private void horasPrevistasDaAtividadePut(List<Atividade> atividades, Atividade atividadeAtualizada,
            Projeto projeto) {
        int totalHoras = 0;
        for (Atividade atividade : atividades) {
            if (atividade.getId() != atividadeAtualizada.getId()) {
                totalHoras += atividade.getHorasPrevistas();
            }
        }
        totalHoras += atividadeAtualizada.getHorasPrevistas();
        if (totalHoras > projeto.getHorasPrevistas()) {
            throw new ExcecaoProvaRN(4003,
                    "A soma das horas previstas das atividades não pode exceder as horas previstas do projeto");
        }
    }
}
