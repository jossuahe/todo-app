package co.devhack.todoapp.presentation.presenter;

/**
 * Created by jossuahe on 30/11/2017.
 */

public interface AuthContract {

    //Interface que implementara el Fragment o Activity
    interface View{

        void goToLoginFragment();

        void goMainActivity();

    }

    //Interface que implementara el presenter
    interface UserActionListener{

        void goToFirstFragment();

    }
}
