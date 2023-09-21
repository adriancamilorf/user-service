package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = IRoleResponseDtoMapper.class
)
public interface IUserResponseMapper {

    @Mapping(target = "roleResponseDto", source = "roleModel")
    UserResponse toUserResponse(UserModel userModel);
}
