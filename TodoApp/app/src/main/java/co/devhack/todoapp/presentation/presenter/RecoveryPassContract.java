package co.devhack.todoapp.presentation.presenter;

/**
 * Created by jossuahe on 04/12/2017.
 */

public interface RecoveryPassContract {

    //Interface que implementara el Fragment o Activity
    interface View{

        void goToLoginFragment();

        void showMessageError(Exception error);

    }

    //Interface que implementara el presenter

    interface UserActionListener{
        void onRecoveryPass(String email);
    }
}
