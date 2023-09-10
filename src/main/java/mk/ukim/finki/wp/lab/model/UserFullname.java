package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
public class UserFullname implements Serializable {

    private String name;
    private String surname;

    public UserFullname() {
    }
}
