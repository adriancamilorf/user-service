package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.UnderAgeException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveOwner(UserModel owner) {
        if(isOver18YearsOld(owner.getBirthdate())) {
            RoleModel roleOwner = new RoleModel();
            String passwordEncode= passwordEncoder.encodePassword(owner.getPassword());
            owner.setPassword(passwordEncode);
            roleOwner.setId(2L);
            owner.setRoleModel(roleOwner);
            userPersistencePort.saveOwner(owner);
        }else{
            throw new UnderAgeException();
        }
    }

    @Override
    public UserModel getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    private boolean isOver18YearsOld(LocalDate birthdate) {
        LocalDate now = LocalDate.now();
        int age = Period.between(birthdate, now).getYears();
        return age >= 18;
    }
}
