package com.indistudia.botbackend.mappers;

import com.indistudia.botbackend.controller.dto.MediaDto;
import com.indistudia.botbackend.domain.Media;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MediaDtoMapper {

    MediaDto toDto(Media media);

    List<MediaDto> toDtoList(List<Media> media);
}
