package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepositoryDB extends JpaRepository<Balloon,Long> {

    List<Balloon> findAllByNameOrDescription(String name,String description);
    List<Balloon> searchAllByNameOrDescription(String text,String desc);
}
