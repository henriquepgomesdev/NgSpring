package org.example.user.dto;

import org.example.user.domain.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
