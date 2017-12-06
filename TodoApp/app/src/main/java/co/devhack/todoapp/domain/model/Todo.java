package co.devhack.todoapp.domain.model;

/**
 * Created by jossuahe on 05/12/2017.
 */

public class Todo {

    private Integer id;
    private String description;
    private Date finishDate;
    private Boolean finished;
    private String image;
    private int color;

    public Todo(){

    }

    public Todo(String description, Date finishDate, Boolean finished, String image, int color) {
        this.id = id;
        this.description = description;
        this.finishDate = finishDate;
        this.finished = finished;
        this.image = image;
        this.color = color;
    }
}
