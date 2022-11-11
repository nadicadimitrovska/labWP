package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

   // public List<Balloon>balloons=new ArrayList<>(10);

    public List<Balloon>findAllBalloons(){
        return DataHolder.balloons;

    }

    public List<Balloon>findaAllByNameOrDescription(String text){
        return DataHolder.balloons.stream().filter(r->r.getName().equals(text) || r.getDescription().equals(text)).collect(Collectors.toList());
    }

    public Optional<Balloon>findById(Long id){
        return DataHolder.balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Optional<Balloon>save(String name, String description, Manufacturer manufacturer){
        DataHolder.balloons.removeIf(i->i.getName().equals(name));
        Balloon balloon=new Balloon(name,description,manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id){
        DataHolder.balloons.removeIf(i->i.getId().equals(id));
    }

}
