/**
 * Created by jossuahe on 02/12/2017.
 */

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

import co.devhack.todoapp.R;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.presentation.presenter.SignUpPresenter;
import co.devhack.todoapp.presentation.presenter.SingUpContract;
import co.devhack.todoapp.presentation.view.activity.MainActivity;

public class SignUpFragment extends Fragment implements SingUpContract.View, View.OnClickListener {

    private SingUpContract.UserActionsListener mActionsListener;
    private TextInputLayout tilFullName;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnSignUp;
    private Button btnHaveAccount;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment getInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_singup, container, false);

        mActionsListener = new SignUpPresenter(this);

        tilFullName = view.findViewById(R.id.tilFullName);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnHaveAccount = view.findViewById(R.id.btnHaveAccount);

        //Recibir eventos de los botones
        btnSignUp.setOnClickListener(this);
        btnHaveAccount.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                signUp();
                break;

            case R.id.btnHaveAccount:
                goToLoginFragment();
                break;
        }
    }


    public void goToLoginFragment() {
        getFragmentManager().popBackStack();
    }


    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        // si quisiera iniciar un servicio y no una actividad entonces:
        //getContext().startService(intent);

    }


    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void signUp() {
        try {
            boolean result = true;
            String fullName = tilFullName.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            if (Utilities.isEmpty(fullName)) {
                tilFullName.setError(getString(R.string.is_required));
                tilFullName.setErrorEnabled(true);
                result = false;
            } else {
                tilFullName.setError(null);
                tilFullName.setErrorEnabled(false);
            }

            if (Utilities.isEmpty(email)) {
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            if (Utilities.isEmpty(password)) {
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true);
                result = false;
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            //Si la validaciones no generaron errores
            if (result) {
                mActionsListener.onSignUp(fullName, email, password);
            }
        } catch (Exception e) {

        }
    }

}