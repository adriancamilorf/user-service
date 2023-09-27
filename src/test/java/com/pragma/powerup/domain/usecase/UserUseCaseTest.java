package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.UnderAgeException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveOwner() throws Exception {
        RoleModel roleModel = RoleModel.builder()
                .id(2L)
                .build();

        when(passwordEncoder.encodePassword("12323122123")).thenReturn("encodedPassword");

        UserModel userModel = UserModel.builder()
                .name("adrian")
                .birthdate(LocalDate.of(2000, 3, 13))
                .documentNumber("1007321243")
                .roleModel(roleModel)
                .email("adrean@mail.com")
                .phone("3137623509")
                .password("12323122123")
                .lastName("Rodriguez")
                .build();

        userUseCase.saveOwner(userModel);

        verify(passwordEncoder).encodePassword("12323122123");

        verify(userPersistencePort, Mockito.times(1)).saveUser(userModel);
    }

    @Test
    void testSaveOwnerFailSave()  {
        RoleModel roleModel = RoleModel.builder()
                .id(2L)
                .build();

        UserModel userModel = UserModel.builder()
                .name("adrian")
                .birthdate(LocalDate.of(2020, 3, 13))
                .documentNumber("1007321243")
                .roleModel(roleModel)
                .email("adrean@mail.com")
                .phone("3137623509")
                .password("12323122123")
                .lastName("Rodriguez")
                .build();

        Assertions.assertThrows(UnderAgeException.class, () -> {
            userUseCase.saveOwner(userModel);
        });
    }

    @Test
    void getUserById(){
        Long id = 2L;
        userUseCase.getUserById(id);
        verify(userPersistencePort, Mockito.times(1)).getUserById(id);
    }

    @Test
    void testSaveEmployee() throws Exception {
        RoleModel roleModel = RoleModel.builder()
                .id(4L)
                .build();

        when(passwordEncoder.encodePassword("12323122123")).thenReturn("encodedPassword");

        UserModel userModel = UserModel.builder()
                .name("adrian")
                .documentNumber("1007321243")
                .roleModel(roleModel)
                .email("adrean@mail.com")
                .phone("3137623509")
                .password("12323122123")
                .lastName("Rodriguez")
                .build();

        userUseCase.saveEmployee(userModel);

        verify(passwordEncoder).encodePassword("12323122123");

        verify(userPersistencePort, Mockito.times(1)).saveUser(userModel);
    }

}
