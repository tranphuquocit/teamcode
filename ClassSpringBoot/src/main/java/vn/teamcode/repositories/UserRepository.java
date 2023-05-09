package vn.teamcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teamcode.models.UserModel;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByUserName(String userName);
}
