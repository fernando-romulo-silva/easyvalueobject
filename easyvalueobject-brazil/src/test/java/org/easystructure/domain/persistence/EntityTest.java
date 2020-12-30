package org.easystructure.domain.persistence;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.easystructure.domain.valueobject.personal.Cpf;

@Entity
public class EntityTest {

    @Id
    private Long id;

    @Column
    @Convert(converter = CpfConverter.class)
    private Cpf cpf;

    @Column
    private String nome;

    // -----------------------------------------------
    public EntityTest() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(final Cpf cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }
}
