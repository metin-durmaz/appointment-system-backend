package com.turksat.appointmentsystem.service.implementations;

import com.turksat.appointmentsystem.model.dto.AppointmentDto;
import com.turksat.appointmentsystem.model.dto.PeriodDto;
import com.turksat.appointmentsystem.model.entity.Appointment;
import com.turksat.appointmentsystem.model.entity.Period;
import com.turksat.appointmentsystem.model.repository.PeriodRepository;
import com.turksat.appointmentsystem.service.PeriodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final ModelMapper modelMapper;

    public PeriodServiceImpl(PeriodRepository periodRepository, ModelMapper modelMapper) {
        this.periodRepository = periodRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * It saves period to database.
     *
     * @param periodDto
     * @return
     */
    // CREATE
    @Override
    public PeriodDto save(PeriodDto periodDto) {
        Period period=this.periodRepository.save(modelMapper.map(periodDto, Period.class));
        return modelMapper.map(period,PeriodDto.class);
    }

    /**
     * It retrieves a period from database with the period ID.
     *
     * @param id
     * @return
     */
    // READ
    @Override
    public PeriodDto getById(Long id) {
        PeriodDto periodDto=modelMapper.map(this.periodRepository.getById(id),PeriodDto.class);

        if(periodDto==null)
            throw new IllegalArgumentException("There is no period with this id !");

        return periodDto;

    }

    /**
     * It updates a period in the database with the period ID.
     *
     * @param id
     * @param periodDto
     * @return
     */
    // UPDATE
    @Override
    public PeriodDto update(Long id, PeriodDto periodDto) {
        Period periodDb=periodRepository.getById(id);
        if(!periodRepository.existsById(id))
            throw new IllegalArgumentException("There is no period with this id !");

        periodDb.setStatus(periodDto.getStatus());
        periodDb.setSlice(periodDto.getSlice());

        periodRepository.save(periodDb);

        return modelMapper.map(periodDb,PeriodDto.class);
    }

    /**
     * It deletes a period from the database with the period ID.
     *
     * @param id
     * @return
     */
    // DELETE
    @Override
    public Boolean delete(Long id) {

        if(!this.periodRepository.existsById(id))
            throw new IllegalArgumentException("There is no period with this id !");

        this.periodRepository.delete(this.periodRepository.getById(id));

        return Boolean.TRUE;
    }
}
