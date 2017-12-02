package co.devhack.todoapp.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import co.devhack.todoapp.R;
import co.devhack.todoapp.presentation.presenter.AuthContract;
import co.devhack.todoapp.presentation.presenter.AuthPresenter;
import co.devhack.todoapp.presentation.view.fragment.LoginFragment;

public class AuthActivity extends AppCompatActivity
        implements AuthContract.View{

    private AuthContract.UserActionLister mActionsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mActionsListener = new AuthPresenter(this);
        mActionsListener.goToFirstFragment();

    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void goToLoginFragment(){
        replaceFragment(LoginFragment.getInstance(), true);
        //el tdo sirve para apoyarse y saber que tiene codigo por trabajar

    }

    public void goMainActivity(){

    }

}
