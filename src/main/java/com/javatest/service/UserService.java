package com.javatest.service;

import com.javatest.domain.randomuser.ResponseUser;
import com.javatest.domain.randomuser.ResponseUserList;
import com.javatest.domain.user.User;
import com.javatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String randomUserEndpointUrl = "https://randomuser.me/api/?seed=foobar";
    private final WebClient webClient = WebClient.create(randomUserEndpointUrl);
    private final ModelMapper modelMapper;

    @Scheduled(fixedRate = 10000)
    private void fetchAndSaveUser() {
        Optional<ResponseUser> userResponse = fetchUserFromRandomApi();
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

    private void createOrEditUser(User user) {
        Optional<User> existingUser = userRepository.findByPhoneOrCell(user.getPhone(), user.getCell()).stream().findFirst();
        existingUser.ifPresent(userToUpdate -> {
            user.setId(userToUpdate.getId());

        });
        userRepository.save(user);
    }


}
