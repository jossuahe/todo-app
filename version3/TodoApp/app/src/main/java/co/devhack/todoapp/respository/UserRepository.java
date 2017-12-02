package co.devhack.todoapp.respository;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by jossuahe on 30/11/2017.
 */

public interface UserRepository {

    void login(String email, String password, Callback<User> callback);

    void signUp(User user, Callback<User> callback);

}
