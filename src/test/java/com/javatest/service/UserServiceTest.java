package com.javatest.service;

import com.javatest.domain.randomuser.ResponseUser;
import com.javatest.domain.randomuser.ResponseUserList;
import com.javatest.domain.user.User;
import com.javatest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService = new UserService(userRepository, emailService, modelMapper, webClient);
    }

    @Test
    public void testFetchAndSaveUser() {
        ResponseUser responseUser = new ResponseUser();
        ResponseUserList responseUserList = new ResponseUserList();
        responseUserList.setResults(Collections.singletonList(responseUser));

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(ResponseUserList.class)).thenReturn(Mono.just(ResponseEntity.ok(responseUserList)));

        userService.fetchAndSaveUser();

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateOrEditUser_NewUser() {
        User user = new User();
        user.setPhone("123456789");
        user.setCell("987654321");

        when(userRepository.findByPhoneOrCell(user.getPhone(), user.getCell())).thenReturn(Collections.emptyList());

        userService.createOrEditUser(user);

        verify(userRepository, times(1)).save(user);
        verify(emailService, never()).sendEmail(anyString());
    }

    @Test
    public void testCreateOrEditUser_ExistingUser() {
        User user = new User();
        user.setPhone("123456789");
        user.setCell("987654321");

        User existingUser = new User();
        existingUser.setId(1L);

        when(userRepository.findByPhoneOrCell(user.getPhone(), user.getCell())).thenReturn(Collections.singletonList(existingUser));

        userService.createOrEditUser(user);

        assertEquals(existingUser.getId(), user.getId());
        verify(userRepository, times(1)).save(user);
        verify(emailService, times(1)).sendEmail(anyString());
    }

    @Test
    public void testGetUsers() {
        List<User> users = Collections.singletonList(new User());
        when(userRepository.findAll(10, 0)).thenReturn(users);

        List<User> result = userService.getUsers(10, 0);

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll(10, 0);
    }
}