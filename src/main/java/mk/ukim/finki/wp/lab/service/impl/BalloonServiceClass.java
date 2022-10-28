package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.repository.BalloonRepository;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalloonServiceClass implements BalloonService {

    private final BalloonRepository balloonRepository;

    public BalloonServiceClass(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findaAllByNameOrDescription(text);
    }
}
