package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepositoryDB;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

//    private final ManufacturerRepository manufacturerRepository;
//
//    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
//        this.manufacturerRepository = manufacturerRepository;
//    }
    private final ManufacturerRepositoryDB manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepositoryDB manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name,String country, String address){
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name,country,address)));
    }
}
