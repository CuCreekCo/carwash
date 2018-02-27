package co.cucreek.carwash.service;

import co.cucreek.carwash.domain.CarwashUser;
import co.cucreek.carwash.domain.CarwashUserRepository;
import co.cucreek.carwash.domain.model.User;
import co.cucreek.carwash.exceptions.UserExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author jljdavidson on 2/10/18.
 */
@Component
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final CarwashUserRepository carwashUserRepository;

    private final AsyncJpaWrapper asyncJpaWrapper;

    @Autowired
    public UserService(CarwashUserRepository carwashUserRepository, AsyncJpaWrapper asyncJpaWrapper) {
        this.carwashUserRepository = carwashUserRepository;
        this.asyncJpaWrapper = asyncJpaWrapper;
    }

    public Mono<User> registerUser(User user) {
        System.out.println("registerUser:" + Thread.currentThread());
        return asyncJpaWrapper.async(() -> {
            System.out.println("async:" + Thread.currentThread());
            if (carwashUserRepository.findByEmail(user.getEmail()) != null) {
                throw new UserExistsException(); //IS this the right way to get the exceptions out of here?
            } else {
                CarwashUser u = new CarwashUser();
                u.setEmail(user.getEmail());
                u.setPassword(user.getPassword());
                //WTF goes on with transactional?
                u = carwashUserRepository.save(u);
                if (u.getId() != null) {
                    user.setId(u.getId());
                    return user;
                } else {
                    throw new Exception("Error creating CarwashUser");
                }
            }
        });
    }
}

