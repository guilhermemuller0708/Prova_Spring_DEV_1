package br.edu.restinga.ifrs.gui.provaSpring.modelo.rn;

import br.edu.restinga.ifrs.gui.provaSpring.excecoes.ExcecaoProvaRN;
import br.edu.restinga.ifrs.gui.provaSpring.modelo.entidade.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRN implements RegraNegocio<Cliente> {

    @Override
    public void validarCadastrar(Cliente entidade) {
        this.valida(entidade);
    }

    @Override
    public void validarAtualizar(Cliente entidadeAtinga, Cliente entidadeNova) {
        this.valida(entidadeNova);
    }

    @Override
    public void validarExcluir(Cliente entidade) {

    }

    private void valida(Cliente entidade) {
        if (entidade.getNome() == null
                || entidade.getCnpj() == null
                || entidade.getContato() == null
                || entidade.getNome().equals("")
                || entidade.getCnpj().equals("")
                || entidade.getContato().equals("")) {
            throw new ExcecaoProvaRN(1001, "Todos os campos obrigatórios");
        }

        if (entidade.getCnpj().length() != 14) {
            throw new ExcecaoProvaRN(1002, "CNPJ deve ter 14 caracteres");
        }

        if (entidade.getContato().getNome() == null || entidade.getContato().getEmail() == null) {
            throw new ExcecaoProvaRN(2001, "Todos os campos obrigatórios");
        }

        if (!entidade.getContato().getEmail().contains("@")) {
            throw new ExcecaoProvaRN(2002, "Email deve conter o caractere '@'");
        }

    }
}
