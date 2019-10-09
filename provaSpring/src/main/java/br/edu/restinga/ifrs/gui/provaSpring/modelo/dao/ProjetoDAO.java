package br.edu.restinga.ifrs.gui.provaSpring.modelo.dao;

import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Projeto;
import org.springframework.data.repository.CrudRepository;

public interface ProjetoDAO extends CrudRepository<Projeto, Integer> {

}
