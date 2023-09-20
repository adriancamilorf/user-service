package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import com.pragma.powerup.application.handler.impl.UserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("userRequestDtoProvider")
    void testSaveOwnerWithInvalidData(NewUserRequestDto newUserRequestDto, Class<? extends Exception> expectedException) {
        UserModel userModelSimulated = new UserModel();

        when(userRequestMapper.toUserModel(newUserRequestDto)).thenReturn(userModelSimulated);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                userHandler.saveOwner(newUserRequestDto);
            });
        } else {
            userHandler.saveOwner(newUserRequestDto);
            verify(userServicePort).saveOwner(userModelSimulated);
        }
    }

    private static Stream<Arguments> userRequestDtoProvider() {
        return Stream.of(
                Arguments.of(
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
                        NewUserRequestDto.builder()
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
}
