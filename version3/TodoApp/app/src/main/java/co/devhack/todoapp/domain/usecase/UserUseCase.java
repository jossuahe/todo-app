package co.devhack.todoapp.domain.usecase;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by jossuahe on 02/12/2017.
 */

public interface UserUseCase {

    void login(String email, String password, boolean remember, Callback<User> Callback);

    void signUp(String fullName, String email, String passwords, Callback<User> Callback);
}
