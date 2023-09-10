package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.exceptions.BalloonNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepositoryDB;
import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepositoryDB;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceClass implements BalloonService {

//    private final BalloonRepository balloonRepository;
//    private final ManufacturerRepository manufacturerRepository;

//    public BalloonServiceClass(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
//        this.balloonRepository = balloonRepository;
//        this.manufacturerRepository = manufacturerRepository;
//    }
    private final BalloonRepositoryDB balloonRepository;
    private final ManufacturerRepositoryDB manufacturerRepository;

    public BalloonServiceClass(BalloonRepositoryDB balloonRepository, ManufacturerRepositoryDB manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
//    public List<Balloon> listAll() {
//        return balloonRepository.findAllBalloons();
//    }
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
//    public List<Balloon> searchByNameOrDescription(String text) {
//        return balloonRepository.findaAllByNameOrDescription(text);
//    }
    public List<Balloon> searchByNameOrDescription(String text,String desc) {
        return balloonRepository.searchAllByNameOrDescription(text,desc);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
//    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
//        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
//        return this.balloonRepository.save(name,description,manufacturer);
//    }
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
        return Optional.of( this.balloonRepository.save(new Balloon(name,description,manufacturer)));
    }

    @Override
    public Optional<Balloon> edit(Long id,String name, String description, Long manufacturerId) {
        Balloon balloon= this.balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));

        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));

        balloon.setName(name);
        balloon.setDescription(description);
        balloon.setManufacturer(manufacturer);

        return Optional.of( this.balloonRepository.save(balloon));

//        return Optional.of( this.balloonRepository.save(new Balloon(name,description,manufacturer)));
    }


    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);

    }
}
