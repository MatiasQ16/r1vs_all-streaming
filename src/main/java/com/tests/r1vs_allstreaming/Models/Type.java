package com.tests.r1vs_allstreaming.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Type() {
    }

    public Type(String type) {
        this.type = type;
        this.accounts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
