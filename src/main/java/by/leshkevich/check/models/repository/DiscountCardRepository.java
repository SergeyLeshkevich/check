package by.leshkevich.check.models.repository;

import by.leshkevich.check.models.entities.DiscountCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends CrudRepository<DiscountCard,Long> {
}
