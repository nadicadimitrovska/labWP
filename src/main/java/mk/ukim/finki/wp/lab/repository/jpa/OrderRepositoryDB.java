package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.impl.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface
OrderRepositoryDB extends JpaRepository<Order,Long> {
    List<Order>findAllByUser(User user);
    List<Order>findByDateCreatedBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
    Optional<Order>findByUser(User user);


}
