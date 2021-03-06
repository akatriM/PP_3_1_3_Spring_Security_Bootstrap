package main.java.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
