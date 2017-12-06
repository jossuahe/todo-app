package co.devhack.todoapp.helpers;

/**
 * Created by jossuahe on 02/12/2017.
 */

public interface Callback <T>{

    void success(T result);

    void error(Exception error);


}
