package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRoleEntityMapper.class}
)
public interface IUserEntityMapper {

    @Mapping(target = "idRole", source = "roleModel")
    UserEntity toUserEntity(UserModel userModel);

    @Mapping(target = "roleModel", source = "idRole")
    UserModel toUserModel(UserEntity userEntity);
}
