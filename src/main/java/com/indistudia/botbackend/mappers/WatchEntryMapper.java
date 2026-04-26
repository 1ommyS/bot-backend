package com.indistudia.botbackend.mappers;

import com.indistudia.botbackend.controller.dto.WatchEntryDto;
import com.indistudia.botbackend.domain.WatchEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = MediaDtoMapper.class)
public interface WatchEntryMapper {

    @Mapping(target = "userId", source = "user.id")
    WatchEntryDto toDto(WatchEntry watchEntry);

    List<WatchEntryDto> toDtoList(List<WatchEntry> watchEntries);
}
