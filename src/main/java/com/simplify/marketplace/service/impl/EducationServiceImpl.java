package com.simplify.marketplace.service.impl;

import com.simplify.marketplace.domain.Education;
import com.simplify.marketplace.repository.EducationRepository;
import com.simplify.marketplace.service.EducationService;
import com.simplify.marketplace.service.dto.EducationDTO;
import com.simplify.marketplace.service.mapper.EducationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Education}.
 */
@Service
public class EducationServiceImpl implements EducationService {

    private final Logger log = LoggerFactory.getLogger(EducationServiceImpl.class);

    private final EducationRepository educationRepository;

    private final EducationMapper educationMapper;

    public EducationServiceImpl(EducationRepository educationRepository, EducationMapper educationMapper) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
    }

    @Override
    public EducationDTO save(EducationDTO educationDTO) {
        log.debug("Request to save Education : {}", educationDTO);
        Education education = educationMapper.toEntity(educationDTO);
        education = educationRepository.save(education);
        return educationMapper.toDto(education);
    }

    @Override
    public Optional<EducationDTO> partialUpdate(EducationDTO educationDTO) {
        log.debug("Request to partially update Education : {}", educationDTO);

        return educationRepository
            .findById(educationDTO.getId())
            .map(
                existingEducation -> {
                    educationMapper.partialUpdate(existingEducation, educationDTO);

                    return existingEducation;
                }
            )
            .map(educationRepository::save)
            .map(educationMapper::toDto);
    }

    @Override
    public Page<EducationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Educations");
        return educationRepository.findAll(pageable).map(educationMapper::toDto);
    }

    @Override
    public Optional<EducationDTO> findOne(String id) {
        log.debug("Request to get Education : {}", id);
        return educationRepository.findById(id).map(educationMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Education : {}", id);
        educationRepository.deleteById(id);
    }
}
