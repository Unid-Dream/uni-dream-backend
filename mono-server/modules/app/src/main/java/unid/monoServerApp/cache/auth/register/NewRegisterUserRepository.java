package unid.monoServerApp.cache.auth.register;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewRegisterUserRepository extends CrudRepository<NewRegisterUser, UUID> {
    NewRegisterUser findByEmailIgnoreCase(String email);
}
