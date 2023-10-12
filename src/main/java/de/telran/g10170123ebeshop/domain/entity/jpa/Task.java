package de.telran.g10170123ebeshop.domain.entity.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "executed_at")
    private Timestamp executedAt;

    public Task() {
        executedAt = new Timestamp(System.currentTimeMillis());
    }

    public Task(String description) {
        this.description = description;
        executedAt = new Timestamp(System.currentTimeMillis());
    }

    public String getDescription() {
        return description;
    }
}