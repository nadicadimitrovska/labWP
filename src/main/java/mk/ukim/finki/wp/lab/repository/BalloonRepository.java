package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.List;
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

}
