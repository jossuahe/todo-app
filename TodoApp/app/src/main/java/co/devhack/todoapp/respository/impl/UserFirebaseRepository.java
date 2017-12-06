package co.devhack.todoapp.respository.impl;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.helpers.Callback;
import co.devhack.todoapp.respository.UserRepository;

import static android.content.ContentValues.TAG;
/**
 * Created by jossuahe on 30/11/2017.
 */

public class UserFirebaseRepository implements UserRepository  {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private TextView txtNoEnvioCorreo;
    //private FirebaseUser user; //= FirebaseAuth.getInstance().getCurrentUser();

    public UserFirebaseRepository(){
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance()
                .getReference("users");
    }

    @Override
    public void login(String email, String password, final Callback<User> callback) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            //cuando se dice child se hace referencia a un Uid de la Database
                            mDatabase.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    User user = dataSnapshot.getValue(User.class); //este value es nuestro user desde la bd
                                    callback.success(user);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    callback.error(databaseError.toException());
                                }
                            });
                        }else{
                            callback.error(task.getException());
                        }
                    }
                });
    }

    @Override
    public void signUp(final User user, final Callback<User> callback) {

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Respuesta de la creacion del usuario en FirebaseAuthentication
                        if(task.isSuccessful() && task.getResult() != null){
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            user.setPassword(null);
                            mDatabase.child(user.getId()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Respuesta de la creacion del usuario en FirebaseDatabase
                                    if(task.isSuccessful()){
                                        callback.success(user);
                                    }else{
                                        callback.error(task.getException());
                                    }
                                }
                            });
                        }else{
                            callback.error(task.getException());
                        }
                    }
                });

    }

    @Override
    public void recoveryPass(String email, final Callback<User> callback) {

        //String error = "No se logró enviar el correo!";
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //FirebaseUser firebaseUser = task.getResult().getUser();
                        //Respuesta al envio del correo
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                            Log.d(TAG,"Se envio correo para recuperar tu contraseña");
                        } else {
                            callback.error(task.getException());
                        }
                    }
                });
    }   //Recuperación de contraseña
}
