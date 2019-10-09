package br.edu.restinga.ifrs.gui.provaSpring.modelo.dao;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

}
