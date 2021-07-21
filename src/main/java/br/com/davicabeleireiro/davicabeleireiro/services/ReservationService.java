package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.ReservationDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Reservation;
import br.com.davicabeleireiro.davicabeleireiro.repository.ItemRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.ReservationRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.UserRepository;
import br.com.davicabeleireiro.davicabeleireiro.utils.SumTotalValueReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public ReservationDTO create(ReservationDTO dto){

        if (reservationRepository.checkAvailableReservationDate(dto.getScheduleDate()) != null){
            throw new ResourceAlreadyExists("The date "+ dto.getScheduleDate() + " is already used");
        }

        List<Item> itemList = getItemList(dto);
        itemList.forEach(dto::addItemList);

        dto.setUser(userRepository.findById(dto.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("ID not found")));

        dto.setRegistrationDate(new Date());
        dto.setEnabled(true);

        dto.setTotal(String.format("%.2f", SumTotalValueReservation.sumTotal(dto.getItemList())));

        return new ReservationDTO(reservationRepository.save(new Reservation(dto)));
    }

    public ReservationDTO update(ReservationDTO dto){

        if (reservationRepository.checkAvailableReservationDate(dto.getScheduleDate()) != null){
            if (reservationRepository.checkAvailableReservationDateFromID(dto.getScheduleDate(), dto.getUser().getId()) == null){
                throw new ResourceAlreadyExists("The date "+dto.getScheduleDate() + " is already used");
            }
        }
        var entity = reservationRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID not found"));

        var itemList = getItemList(dto);
        entity.removeAllItemList();
        itemList.forEach(entity::addItemList);
        entity.setUser(userRepository.findById(dto.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("ID not found")));
        entity.setRegistrationDate(new Date());
        entity.setScheduleDate(dto.getScheduleDate());

        double t = SumTotalValueReservation.sumTotal(entity.getItemList());

        entity.setTotal(String.format("2.f", t));
        entity.setEnabled(true);



        return new ReservationDTO(reservationRepository.save(entity));
    }

    @Transactional
    public ReservationDTO disableReservation(Long id){
        reservationRepository.disableReservation(id);

        var entity =  reservationRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("ID not found"));

        return new ReservationDTO(entity);
    }

    public List<ReservationDTO> findAll(){
        var entityList = reservationRepository.findAll();
        List<ReservationDTO> dtoList = new ArrayList<>();

        entityList.forEach(x -> dtoList.add(new ReservationDTO(x)));

        return dtoList;
    }

    public List<ReservationDTO> findByEnabledTrue(){
        var entityEnabledList = reservationRepository.findByEnabledTrue();

        List<ReservationDTO> dtoList = new ArrayList<>();
        entityEnabledList.forEach(x -> dtoList.add(new ReservationDTO(x)));
        return dtoList;
    }

    public List<ReservationDTO> findByEnabledFalse(){
        var entityEnabledList = reservationRepository.findByEnabledFalse();

        List<ReservationDTO> dtoList = new ArrayList<>();
        entityEnabledList.forEach(x -> dtoList.add(new ReservationDTO(x)));
        return dtoList;
    }

    public List<ReservationDTO> findByUser(String username){
        var entityList = reservationRepository.findByUser(username);

        List<ReservationDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ReservationDTO(x)));
        return dtoList;
    }

    public List<ReservationDTO> findByUserId(Long id){
        var entityList = reservationRepository.findByUserID(id);

        List<ReservationDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ReservationDTO(x)));
        return dtoList;
    }

    private List<Item> getItemList(ReservationDTO dto){
        List<Item> itemList = new ArrayList<>();

        for (String item: dto.getItemId()) {
            itemList.add(itemRepository.findById(Long.parseLong(item)).orElseThrow(() -> new ResourceNotFoundException("" +
                    "No items found for the ID: "+ item)));
        }
        return itemList;
    }
}
