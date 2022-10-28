package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
}
