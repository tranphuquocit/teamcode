package vn.teamcode.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.teamcode.models.UserModel;
import vn.teamcode.repositories.UserRepository;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                UserModel user1 = new UserModel("tuandz", "Nguyen van tuan", "Thach that", 2002);
//                UserModel user2 = new UserModel("minhminh", "Nguyen van minh", "Cau giay", 2002);
//                UserModel user3 = new UserModel("hoang", "Nguyen van hoang", "Thanh oai", 2003);
//                logger.info("insert data: " + userRepository.save(user1));
//                logger.info("insert data: " + userRepository.save(user2));
//                logger.info("insert data: " + userRepository.save(user3));
            }
        };
    }
}
