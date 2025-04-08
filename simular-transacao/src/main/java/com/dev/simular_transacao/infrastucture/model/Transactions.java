package com.dev.simular_transacao.infrastucture.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="transactions")
@Table
@Builder
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @JoinColumn(name="receiver_id")
    @ManyToOne
    private Users receiver;

    @JoinColumn(name="payer_id")
    @ManyToOne
    private Users payer;

    private LocalDateTime time;

    @PrePersist
    void prePersist(){
        time = LocalDateTime.now();
    }

}
