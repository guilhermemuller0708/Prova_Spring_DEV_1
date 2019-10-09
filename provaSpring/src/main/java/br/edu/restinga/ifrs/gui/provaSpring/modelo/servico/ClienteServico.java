package br.edu.restinga.ifrs.gui.provaSpring.modelo.servico;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.dao.ClienteDAO;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Cliente;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.ClienteRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.rn.RegraNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteServico extends Servico<Cliente> {

    @Autowired
    ClienteDAO clienteDAO;

    @Autowired
    ClienteRN clienteRN;

    @Override
    public CrudRepository<Cliente, Integer> getDAO() {
        return clienteDAO;
    }

    @Override
    public RegraNegocio<Cliente> getRegraNegocio() {
        return clienteRN;
    }
}
