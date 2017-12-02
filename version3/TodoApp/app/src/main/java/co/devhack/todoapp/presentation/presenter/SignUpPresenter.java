package co.devhack.todoapp.presentation.presenter;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.domain.usecase.impl.UserUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.presentation.view.fragment.SignUpFragment;

/**
 * Created by jossuahe on 30/11/2017.
 */

public class SignUpPresenter implements SingUpContract.UserActionsListener {

    private SingUpContract.View view;
    private UserUseCase userUseCase;

    public SignUpPresenter(SingUpContract.View view){
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onSignUp(String fullName, String email, String password) {
        userUseCase.signUp(fullName, email, password, new Callback<User>() {
            @Override
            public void success(User result) {
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                view.showMessageError(error);
            }
        });

    }
}
