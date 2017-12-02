package co.devhack.todoapp.domain.usecase.impl;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.respository.UserRepository;
import co.devhack.todoapp.respository.impl.UserFirebaseRepository;

/**
 * Created by jossuahe on 02/12/2017.
 */

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;

    public UserUseCaseImpl(){
        this.userRepository = new UserFirebaseRepository();
    }

    @Override
    public void login(String email, String password, final boolean remember, final Callback<User> callback) {
        userRepository.login(email, password, new Callback<User>() {
            @Override
            public void success(User user) {

                if (user != null && remember){
                    //TODO GUARDAR EMAIL EN SharedPreferences
                }
                callback.success(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });


    }

    @Override
    public void signUp(String fullName, String email, String password, final Callback<User> callback) {

        final User user = new User(fullName, email, password);
        userRepository.signUp(user, new Callback<User>() {
            @Override
            public void success(User result) {
                callback.success(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });

    }
}
