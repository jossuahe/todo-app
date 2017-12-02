package co.devhack.todoapp.presentation.presenter;

/**
 * Created by jossuahe on 30/11/2017.
 */

public interface LoginContract {

    interface View{

        void goToSignupFragment();

        void goToMainActivity();

        void showMessageError(Exception error);
    }

    interface UserActionListener{

        void onLogin(String email, String password, boolean remember);

    }
}
