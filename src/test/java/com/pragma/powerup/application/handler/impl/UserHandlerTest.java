package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.exception.InvalidRequestException;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;
    @Mock
    private IUserResponseMapper userResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("ownerRequestDtoProvider")
    void testSaveOwnerWithInvalidData(OwnerRequestDto ownerRequestDto, Class<? extends Exception> expectedException) {
        UserModel userModelSimulated = new UserModel();

        when(userRequestMapper.toUserModel(ownerRequestDto)).thenReturn(userModelSimulated);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                userHandler.saveOwner(ownerRequestDto);
            });
        } else {
            userHandler.saveOwner(ownerRequestDto);
            verify(userServicePort).saveOwner(userModelSimulated);
        }
    }

    private static Stream<Arguments> ownerRequestDtoProvider() {
        return Stream.of(
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        null
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("100732124")
                                .email("adrean@mail.com")
                                .phone("31376235")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243a")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("")
                                .lastName("perez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .birthdate(LocalDate.of(2000, 3, 13))
                                .documentNumber("1007321243")
                                .email("adreanmail.com")
                                .phone("3137623509")
                                .password("1211312323")
                                .lastName("perez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        OwnerRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("perez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        null,
                        InvalidRequestException.class
                )
        );
    }

    @Test
    void getUserById(){
        Long userId = 1L;
        UserModel user = new UserModel();
        user.setId(userId);
        when(userServicePort.getUserById(userId)).thenReturn(user);
        UserResponse userResponse = new UserResponse();
        when(userResponseMapper.toUserResponse(any(UserModel.class))).thenReturn(userResponse);
        UserResponse result = userHandler.getUserById(userId);
        verify(userServicePort, times(1)).getUserById(userId);
        verify(userResponseMapper, times(1)).toUserResponse(user);
        Assertions.assertEquals(userResponse, result);
    }

    @ParameterizedTest
    @MethodSource("userRequestDtoProvider")
    void testSaveEmployeeWithInvalidData(UserRequestDto userRequestDto, Class<? extends Exception> expectedException) {
        UserModel userModelSimulated = new UserModel();

        when(userRequestMapper.toUserModel(userRequestDto)).thenReturn(userModelSimulated);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                userHandler.saveEmployee(userRequestDto);
            });
        } else {
            userHandler.saveEmployee(userRequestDto);
            verify(userServicePort).saveEmployee(userModelSimulated);
        }
    }

    @ParameterizedTest
    @MethodSource("userRequestDtoProvider")
    void testSaveClientWithInvalidData(UserRequestDto userRequestDto, Class<? extends Exception> expectedException) {
        UserModel userModelSimulated = new UserModel();

        when(userRequestMapper.toUserModel(userRequestDto)).thenReturn(userModelSimulated);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                userHandler.saveClient(userRequestDto);
            });
        } else {
            userHandler.saveClient(userRequestDto);
            verify(userServicePort).saveEmployee(userModelSimulated);
        }
    }

    private static Stream<Arguments> userRequestDtoProvider() {
        return Stream.of(
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        null
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("100732124")
                                .email("adrean@mail.com")
                                .phone("31376235")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243a")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("")
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("Rodriguez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("12323122123")
                                .lastName("")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243")
                                .email("adrean@mail.com")
                                .phone("3137623509")
                                .password("")
                                .lastName("perez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        UserRequestDto.builder()
                                .name("adrian")
                                .documentNumber("1007321243")
                                .email("adreanmail.com")
                                .phone("3137623509")
                                .password("1211312323")
                                .lastName("perez")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        null,
                        InvalidRequestException.class
                )
        );
    }

}
