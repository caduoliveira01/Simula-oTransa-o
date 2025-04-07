package com.dev.simular_transacao.controller;

import java.math.BigDecimal;

public record TransationDTO(BigDecimal value, Long payer,Long payee) {
}
