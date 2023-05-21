package siem.kwetter.kweet;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kweet extends PanacheEntity {

    @Column(nullable = false)
    public String message;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
