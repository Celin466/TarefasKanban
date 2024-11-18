package com.example.TarefasKanban.repository;

import com.example.TarefasKanban.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, String> {
    UserDetails findByLogin(String login);
}
