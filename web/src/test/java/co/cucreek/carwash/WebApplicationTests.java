package co.cucreek.carwash;

import co.cucreek.carwash.service.AsyncJpaWrapper;
import co.cucreek.carwash.service.UserService;
import co.cucreek.carwash.exceptions.UserExistsException;
import co.cucreek.carwash.domain.CarwashUser;
import co.cucreek.carwash.domain.CarwashUserRepository;
import co.cucreek.carwash.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    CarwashUserRepository carwashUserRepository;

    @Autowired
    AsyncJpaWrapper asyncJpaWrapper;

    private User createUser() {
        User user = new User();
        user.setEmail("jljdavidson@gmail.com");
        user.setPassword("12345678");
        return user;
    }

    @Test
    public void testMono() {
        CarwashUser carwashUser = new CarwashUser();
        carwashUser.setEmail("123123");
        carwashUser.setPassword("123123123");

        Mono<CarwashUser> userIdMono = asyncJpaWrapper.async(() ->
                carwashUserRepository.save(carwashUser));

        StepVerifier.create(userIdMono).expectNextCount(1L)
                .expectNextMatches(carwashUser1 -> carwashUser1.getId() != null);

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRegisterUser() {
        User user = createUser();
        StepVerifier.create(userService.registerUser(user))
                .expectNextMatches(m -> m.getId().equals(1L));
    }
    @Test
    public void testReRegisterUser() {
        User user = createUser();
        StepVerifier.create(userService.registerUser(user))
                .expectNextMatches(m -> m.getId().equals(1L));

        user.setId(null);
        StepVerifier.create(userService.registerUser(user))
                .expectError(UserExistsException.class);

    }

}
