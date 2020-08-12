package pl.marek.messaging.consumer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.marek.messaging.consumer.model.json.Clients;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface PayloadRepository extends CrudRepository<Clients, Integer> {

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends Clients> S save(S s);

    //@Override
    @RestResource(exported = false)
    List<Clients> findByDate(Date date);
}
