package com.dev.simular_transacao.infrastucture.configs;

import com.dev.simular_transacao.infrastucture.model.Users;
import com.dev.simular_transacao.infrastucture.model.UserType;
import com.dev.simular_transacao.infrastucture.model.Wallet;
import com.dev.simular_transacao.infrastucture.repository.UserRepository;
import com.dev.simular_transacao.infrastucture.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class PopularTabelaUsuario {

    @Bean
    CommandLineRunner popularBanco(UserRepository userRepository, WalletRepository walletRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                Users usuario1 = new Users(null, "Carlos Silva", "carlos@email.com", "101111111111",
                        encoder.encode("123456"), null, UserType.CLIENTE);

                Users usuario2 = new Users(null, "Ana Souza", "ana@email.com", "22222222222",
                        encoder.encode("123456"), null, UserType.CLIENTE);

                Users lojista = new Users(null, "Loja Exemplo", "loja@email.com", "33333333333",
                        encoder.encode("123456"), null, UserType.VENDEDOR);

                userRepository.saveAll(List.of(usuario1, usuario2, lojista));


                Wallet carteira1 = new Wallet(null, new BigDecimal("1000.00"), usuario1);
                Wallet carteira2 = new Wallet(null, new BigDecimal("2000.00"), usuario2);
                Wallet carteira3 = new Wallet(null, new BigDecimal("5000.00"), lojista);

                walletRepository.saveAll(List.of(carteira1, carteira2, carteira3));

                System.out.println("Usuários e carteiras populados com sucesso!");
            }
        };
    }
}