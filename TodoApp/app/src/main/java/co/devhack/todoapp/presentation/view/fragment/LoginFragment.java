package co.devhack.todoapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import co.devhack.todoapp.R;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.presentation.presenter.LoginContract;
import co.devhack.todoapp.presentation.presenter.LoginPresenter;
import co.devhack.todoapp.presentation.presenter.SingUpContract;
import co.devhack.todoapp.presentation.view.activity.AuthActivity;
import co.devhack.todoapp.presentation.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener{

    private LoginContract.UserActionListener mActionsListener;

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextView tvForgotPassword;
    private Switch swRemember;
    private Button btnStart;
    private Button btnNotHaveAccount;

    //presionando las teclas Ctrl + d - duplica una linea de codigo

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getInstance(){

        return new LoginFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mActionsListener = new LoginPresenter(this);

        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        swRemember = view.findViewById(R.id.swRemember);
        btnStart = view.findViewById(R.id.btnStart);
        btnNotHaveAccount = view.findViewById(R.id.btnNotHaveAccount);

        btnStart.setOnClickListener(this);
        btnNotHaveAccount.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                onLogin();
                break;

            case R.id.btnNotHaveAccount:
                goToSignupFragment();
                break;

            case R.id.tvForgotPassword:
                goToRecoveryPassword();
                break;
        }
    }

    @Override
    public void goToSignupFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(SignUpFragment.getInstance(),true);
    }

    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        // si quisiera iniciar un servicio y no una actividad entonces:
        //getContext().startService(intent);
    }

    public void goToRecoveryPassword(){

        RecoveryPasswordFragment recoveryPasswordFragment = RecoveryPasswordFragment.getInstance();
        recoveryPasswordFragment.show(getFragmentManager(), null);
    }

    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void onLogin(){
        try {
            boolean result = true;
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            boolean remember = swRemember.isChecked();

            //para email
            if(Utilities.isEmpty(email)){
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true); //usado para que muestre el espacio en la parte inferior
                result=false;
            }else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }
            //para password
            if(Utilities.isEmpty(password)){
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true); //usado para que muestre el espacio en la parte inferior
                result=false;
            }else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            //Si la valicacion no genera errores
            //El metodo onLogin lo busca en LoginContract
            //androidSalipa ---- plugin para hacer validaciones
            if(result){
                mActionsListener.onLogin(email,password,remember);
            }

        }catch (Exception e){

        }
    }


}
