package com.javatest.service;

import com.javatest.controller.UserController;
import com.javatest.domain.randomuser.ResponseUser;
import com.javatest.domain.randomuser.ResponseUserList;
import com.javatest.domain.user.User;
import com.javatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@AllArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final EmailService emailService;

    private final ModelMapper modelMapper;
    private final WebClient webClient;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSaveUser() {
        logger.info("Starting fetchAndSaveUser");
        logger.info("Fetching user from RandomApi");
        Optional<ResponseUser> userResponse = fetchUserFromRandomApi();
        logger.info("User Fetched: {}", userResponse.toString());
        userResponse.ifPresent(userRandom -> {
            User user = modelMapper.map(userRandom, User.class);
            createOrEditUser(user);
        });
    };

    private Optional<ResponseUser> fetchUserFromRandomApi() {
        Optional<ResponseUser> responseUser = Optional.empty();

        ResponseEntity<ResponseUserList> responseUserList = webClient.get()
                .retrieve()
                .toEntity(ResponseUserList.class)
                .block();

        if (responseUserList != null && responseUserList.getBody() != null) {
            responseUser =  responseUserList
                    .getBody()
                    .getResults()
                    .stream()
                    .findFirst();
        }
        return responseUser;
    }

    public void createOrEditUser(User user) {
        logger.info("Checking if user already exists by Phone ({}) or Cell {}", user.getPhone(), user.getCell());
        Optional<User> existingUser = userRepository.findByPhoneOrCell(user.getPhone(), user.getCell()).stream().findFirst();
        existingUser.ifPresent(userToUpdate -> {
            logger.info("User already exists");
            user.setId(userToUpdate.getId());
            logger.info("Sending email about User Data Change");
            emailService.sendEmail(user.toString());
        });
        logger.info("Saving User to Database");
        userRepository.save(user);
        logger.info("User saved");
    }

    public List<User> getUsers(int limit, int offset) {
        logger.info("Fetching Users from Database: Parameters - Limit: {} - Offset: {}", limit, offset);
        return userRepository.findAll(limit, offset);
    }


}
