package siem.kwetter.kweet;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apache.commons.text.WordUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class KweetRepository implements PanacheRepository<Kweet>{
    public void persist(Kweet kweet) {
        var message = WordUtils.capitalize(kweet.getMessage());

        kweet.setMessage(message);

        PanacheRepository.super.persist(kweet);
    }
    @Override
    public Kweet findById(Long id) {
        return PanacheRepository.super.findById(id);
    }

    public List<Kweet> findByMessage(String message) {
        return this.list("message", WordUtils.capitalize(message));
    }

    public Optional<Kweet> update(Kweet kweet) {
        final var id = kweet.id;
        var savedOpt = this.findByIdOptional(id);
        if (savedOpt.isEmpty()) {
            return Optional.empty();
        }

        var saved = savedOpt.get();
        saved.setMessage(kweet.getMessage());

        return Optional.of(saved);
    }

}
