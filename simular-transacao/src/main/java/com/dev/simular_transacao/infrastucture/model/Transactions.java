package com.dev.simular_transacao.infrastucture.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="transactions")
@Table
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @JoinColumn(name="receiver_id")
    @ManyToOne
    private User receiver;

    @JoinColumn(name="payer_id")
    @ManyToOne
    private User payer;

    private LocalDateTime time;

    @PrePersist
    void prePersist(){
        time = LocalDateTime.now();
    }

}
