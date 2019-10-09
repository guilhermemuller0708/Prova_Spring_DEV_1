package br.edu.restinga.ifrs.gui.provaSpring.controle;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Cliente;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.servico.ClienteServico;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class Clientes extends CRUDControle<Cliente> {

    @Autowired
    ClienteServico clienteServico;

    @Override
    public Servico<Cliente> getService() {
        return clienteServico;
    }

}
