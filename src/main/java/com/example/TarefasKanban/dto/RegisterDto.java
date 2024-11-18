package com.example.TarefasKanban.dto;

import com.example.TarefasKanban.model.UserRole;

public record RegisterDto(String login, String password, UserRole role) {

}
