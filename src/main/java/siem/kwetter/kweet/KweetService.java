package siem.kwetter.kweet;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.naming.directory.InvalidAttributesException;
import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class KweetService {

    @Inject
    KweetRepository kweetRepository;

    public List<Kweet> getKweets(String message) {
        if(StringUtils.isNotBlank(message)) {
            return kweetRepository.findByMessage(message);
        }

        return kweetRepository.listAll();
    }

    public Optional<Kweet> findKweetById(long id) {
        return kweetRepository.findByIdOptional(id);
    }

    @Transactional
    public void create(Kweet kweet) throws InvalidAttributesException {
        if (kweet.id != null) {
            throw new InvalidAttributesException("Id must not be filled");
        }
        Validate.notNull(kweet, "Kweet can not be null");
        Validate.notBlank(kweet.getMessage(), "Message can not be empty");

        kweetRepository.persist(kweet);
    }

    @Transactional
    public Kweet update(Kweet kweet) {
        return kweetRepository.update(kweet).orElseThrow(() -> new InvalidParameterException("Kweet not found"));
    }

    @Transactional
    public boolean delete(long id) {
        return kweetRepository.deleteById(id);
    }
}
