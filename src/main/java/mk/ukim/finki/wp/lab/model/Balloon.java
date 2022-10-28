package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Balloon {
    private String name;
    private String description;

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
