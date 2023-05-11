package vn.teamcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.teamcode.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByUserName(String userName);
    List<UserModel> findByIsShow(boolean isShow);
}
