package com.liquorlogic.userservice;

import com.liquorlogic.userservice.entity.User;
import com.liquorlogic.userservice.enums.Role;
import com.liquorlogic.userservice.enums.Status;
import com.liquorlogic.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class,args);
    }

    @Bean
    public CommandLineRunner loadDate(UserRepository repository) {
        return args -> {
            List<User> allByType = repository.findByType(Role.admin);
            if (allByType.isEmpty()) {

                User user = new User();
                user.setFirstName("Dulanjana");
                user.setLastName("Lakshan");
                user.setEmail("dulanjana20013@gmail.com");
                user.setHeading("Owner Of Cypso Lab's");
                user.setAbout("Owner Of Cypso Lab's");
                user.setContact("0712280020");
                user.setCreate(new Date());
                user.setUpdate(new Date());
                user.setStatus(Status.active);
                user.setType(Role.admin);
                user.setUsername("Admin");
                user.setPassword("Admin@Cypso");
                user.setResetToken(0);

                repository.save(user);
            }
        };
    }
}