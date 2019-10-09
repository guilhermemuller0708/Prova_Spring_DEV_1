package br.edu.restinga.ifrs.gui.provaSpring.modelo.servico;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ContatoDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Contato;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.ContatoRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.RegraNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class ContatoServico extends Servico<Contato> {

    @Autowired
    ContatoDAO contatoDAO;

    @Autowired
    ContatoRN contatoRN;

    @Override
    public CrudRepository<Contato, Integer> getDAO() {
        return contatoDAO;
    }

    @Override
    public RegraNegocio<Contato> getRegraNegocio() {
        return contatoRN;
    }

}
