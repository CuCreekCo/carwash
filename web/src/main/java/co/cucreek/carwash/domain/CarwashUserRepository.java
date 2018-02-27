package co.cucreek.carwash.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jljdavidson on 2/15/18.
 */
@Repository
public interface CarwashUserRepository extends CrudRepository<CarwashUser, Long> {

    public CarwashUser findByEmail(String email);
}
