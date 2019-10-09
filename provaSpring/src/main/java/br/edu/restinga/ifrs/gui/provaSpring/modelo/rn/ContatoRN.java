package br.edu.restinga.ifrs.gui.provaSpring.modelo.rn;

import br.edu.restinga.ifrs.gui.provaSpring.excecoes.ExcecaoProvaRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ContatoDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoRN implements RegraNegocio<Contato> {

    @Autowired
    ContatoDAO autorDAO;

    @Override
    public void validarCadastrar(Contato entidade) {
        this.valida(entidade);
    }

    @Override
    public void validarAtualizar(Contato entidadeAtinga, Contato entidadeNova) {
        this.valida(entidadeNova);
    }

    @Override
    public void validarExcluir(Contato entidade) {

    }

    private void valida(Contato entidade) {
        if (entidade.getNome() == null || entidade.getEmail() == null) {
            throw new ExcecaoProvaRN(2001, "Todos os campos obrigatórios");
        }
        if (!entidade.getEmail().contains("@")) {
            throw new ExcecaoProvaRN(2002, "Email deve conter o caractere '@'");
        }
    }
}
