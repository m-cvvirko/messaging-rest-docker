package pl.marek.messaging.consumer;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.marek.messaging.consumer.model.domain.Client;

import java.util.List;

public interface PayloadRepository extends CrudRepository<Client, String> {

    @Query(value = "{'client' : ?0 }",
            fields = "{ 'id':1, 'title':1, 'name':1, 'surname':1}")
    List<Client> findByClientInfoNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Override
    @RestResource(exported = false)
    <S extends Client> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Client> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String string);

    @Override
    @RestResource(exported = false)
    void delete(Client client);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Client> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
