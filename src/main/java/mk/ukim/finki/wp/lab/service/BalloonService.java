package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon>listAll();
    List<Balloon> searchByNameOrDescription(String text,String desc);//dodadeno vo lab3
    Optional<Balloon>findById(Long id);
    Optional<Balloon>save(String name, String description, Long manufacturerId);

    void deleteById(Long id);

}
