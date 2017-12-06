package co.devhack.todoapp.presentation.presenter;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.domain.usecase.UserUseCase;
import co.devhack.todoapp.domain.usecase.impl.UserUseCaseImpl;
import co.devhack.todoapp.helpers.Callback;

/**
 * Created by jossuahe on 04/12/2017.
 */

public class RecoveryPassPresenter implements RecoveryPassContract.UserActionListener {

    private RecoveryPassContract.View view;
    private UserUseCase userUseCase;

    public RecoveryPassPresenter(RecoveryPassContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onRecoveryPass(String email) {
        userUseCase.recoveryPass(email, new Callback<User>() {
            @Override
            public void success(User result) {

                view.goToLoginFragment();
            }

            @Override
            public void error(Exception error) {

                view.showMessageError(error);
            }
        });
    }
}