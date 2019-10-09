package br.edu.restinga.ifrs.gui.provaSpring.modelo.dao;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoDAO extends CrudRepository<Contato, Integer> {

}
