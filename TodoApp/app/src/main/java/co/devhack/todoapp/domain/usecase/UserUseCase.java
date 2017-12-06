package co.devhack.todoapp.domain.usecase;

import android.telecom.Call;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by jossuahe on 02/12/2017.
 */

public interface UserUseCase {

    void login(String email, String password, boolean remember, Callback<User> callback);

    void signUp(String fullName, String email, String passwords, Callback<User> callback);

    void recoveryPass(String email, Callback<User> callback);
}
