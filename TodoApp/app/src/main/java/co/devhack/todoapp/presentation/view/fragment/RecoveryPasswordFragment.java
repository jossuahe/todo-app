package co.devhack.todoapp.presentation.view.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.devhack.todoapp.R;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.presentation.presenter.RecoveryPassContract;
import co.devhack.todoapp.presentation.presenter.RecoveryPassPresenter;
/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryPasswordFragment extends DialogFragment implements RecoveryPassContract.View, View.OnClickListener {

    private RecoveryPassContract.UserActionListener mActionsListener;
    private TextInputLayout tilEmail;
    private Button btnRecuperar;


    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }

    public static RecoveryPasswordFragment getInstance(){

        return new RecoveryPasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery_pass, container, false);

        mActionsListener = new RecoveryPassPresenter(this);
        //TODO Que hace con este preenter

        tilEmail = view.findViewById(R.id.tilEmail);
        btnRecuperar = view.findViewById(R.id.btnRecuperar);

        btnRecuperar.setOnClickListener(this);

        return view;
    }

    public void goToLoginFragment() {

        getFragmentManager().popBackStack();
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void onRecoveryPass(){
            try {
                boolean result = true;

                String email = tilEmail.getEditText().getText().toString();

                if (Utilities.isEmpty(email)) {
                    tilEmail.setError(getString(R.string.is_required));
                    tilEmail.setErrorEnabled(true);
                    result = false;
                } else {
                    tilEmail.setError(null);
                    tilEmail.setErrorEnabled(false);
                }
                //Si la validaciones no generaron errores
                if (result) {
                    mActionsListener.onRecoveryPass(email);
                }
            } catch (Exception e) {

            }
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRecuperar:
                onRecoveryPass();
                break;
        }

    }


}
