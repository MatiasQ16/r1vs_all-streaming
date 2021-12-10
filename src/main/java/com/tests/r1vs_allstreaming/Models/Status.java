package com.tests.r1vs_allstreaming.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "status")
    private List<Account> accounts;

    public Status() {
    }

    public Status(long id, String status, List<Account> accounts) {
        this.id = id;
        this.status = status;
        this.accounts = accounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
