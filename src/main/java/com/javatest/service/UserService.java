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
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String randomUserEndpointUrl = "https://randomuser.me/api/";
    private final WebClient webClient = WebClient.create(randomUserEndpointUrl);
    private final ModelMapper modelMapper;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSaveUser() {
        ResponseEntity<ResponseUserList> responseUser = webClient.get()
                .retrieve()
                .toEntity(ResponseUserList.class)
                .block();

        if (responseUser != null && responseUser.getBody() != null) {
            ResponseUser userResponse = responseUser
                    .getBody()
                    .getResults()
                    .stream()
                    .findFirst().get();

            User user = modelMapper.map(userResponse, User.class);
            userRepository.save(user);
        }
    }


}
