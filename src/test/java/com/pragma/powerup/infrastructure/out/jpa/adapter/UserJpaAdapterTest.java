package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.exception.DocumentNumberAlreadyExistException;
import com.pragma.powerup.infrastructure.exception.EmailAlreadyExistException;
import com.pragma.powerup.infrastructure.exception.PhoneAlreadyExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

class UserJpaAdapterTest {

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUserSuccess() {
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
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());

        userJpaAdapter.saveUser(userModel);

        verify(userRepository, times(1)).save(any());

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).findByDocumentNumber(anyString());
        verify(userRepository, times(1)).findByPhone(anyString());
    }

    @Test
    void testSaveUserEmailAlreadyExist() {
        UserEntity userEntity = new UserEntity();
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
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(userEntity)) ;

        Assertions.assertThrows(EmailAlreadyExistException.class, () -> {
            userJpaAdapter.saveUser(userModel);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void testSaveOwnerDocumentNumberAlreadyExist() {
        UserEntity userEntity = new UserEntity();
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
        when(userRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(DocumentNumberAlreadyExistException.class, () -> {
            userJpaAdapter.saveUser(userModel);
        });
        verify(userRepository, never()).save(any());
    }

    @Test
    void testSaveOwnerPhoneAlreadyExist() {
        UserEntity userEntity = new UserEntity();
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
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(PhoneAlreadyExistException.class, () -> {
            userJpaAdapter.saveUser(userModel);
        });

        verify(userRepository, never()).save(any());
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        UserModel userModel = new UserModel();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);
        UserModel result = userJpaAdapter.getUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userEntityMapper, times(1)).toUserModel(userEntity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userModel, result);
    }

    @Test
    void testGetUserById_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        UserModel result = userJpaAdapter.getUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        Assertions.assertNull(result);
    }

}