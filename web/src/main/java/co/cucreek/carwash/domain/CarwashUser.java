package co.cucreek.carwash.domain;

import javax.persistence.*;

/**
 * @author jljdavidson on 2/15/18.
 */
@Entity
@Table(name = "carwash_user")
public class CarwashUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carwash_user_id_seq")
    @SequenceGenerator(name = "carwash_user_id_seq", sequenceName = "carwash_user_id_seq")
    private Long id;

    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarwashUser carwashUser = (CarwashUser) o;

        return email != null ? email.equals(carwashUser.email) : carwashUser.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
