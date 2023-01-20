package ru.eltech.project.api.services;

import ru.eltech.project.api.data.Client;

public interface AuthService {
    String auth(String login, String password);

    String authAdmin(String login, String password);

    String registration(Client client, String login, String password);
}
