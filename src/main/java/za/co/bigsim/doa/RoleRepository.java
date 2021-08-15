package za.co.bigsim.doa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.bigsim.domain.ERole;
import za.co.bigsim.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
