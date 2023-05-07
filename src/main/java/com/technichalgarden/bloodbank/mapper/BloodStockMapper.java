package com.technichalgarden.bloodbank.mapper;

import org.mapstruct.Mapper;

import com.technichalgarden.bloodbank.dto.BloodStockDTO;
import com.technichalgarden.bloodbank.model.BloodStock;

/**
 * Mapper for the entity {@link BloodStock} and its DTO {@link BloodStockDTO}
 *
 */

@Mapper(componentModel = "spring", uses = {})
public interface BloodStockMapper extends EntityMapper<BloodStockDTO, BloodStock> {

}
