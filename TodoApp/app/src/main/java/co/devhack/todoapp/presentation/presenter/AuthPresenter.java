package co.devhack.todoapp.presentation.presenter;

/**
 * Created by jossuahe on 30/11/2017.
 */

public class AuthPresenter implements AuthContract.UserActionListener{

    private AuthContract.View view;

    public AuthPresenter(AuthContract.View view) {
        this.view = view;
    }

    //con alt+insert se inserta el constructor

    @Override
    public void goToFirstFragment() {

        view.goToLoginFragment();

    }
}
