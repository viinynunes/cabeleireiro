package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Reservation;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDTO extends RepresentationModel<ReservationDTO> implements Serializable {
    private Long id;
    private String total;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "GMT-3")
    private Date registrationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "GMT-3")
    private Date scheduleDate;
    private Boolean enabled;

    private User user;

    private List<String> itemId = new ArrayList<>();

    private List<Item> itemList = new ArrayList<>();

    public ReservationDTO(){

    }

    public ReservationDTO(Long id, String total, Date registrationDate, Date scheduleDate, Boolean enabled, User user) {
        this.id = id;
        this.total = total;
        this.registrationDate = registrationDate;
        this.scheduleDate = scheduleDate;
        this.enabled = enabled;
        this.user = user;
    }

    public ReservationDTO(Reservation reservation){
        id = reservation.getId();
        total = reservation.getTotal();
        registrationDate = reservation.getRegistrationDate();
        scheduleDate = reservation.getScheduleDate();
        enabled = reservation.getEnabled();
        user = reservation.getUser();

        itemList = reservation.getItemList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getItemId() {
        return itemId;
    }

    public void setItemId(List<String> itemId) {
        this.itemId = itemId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItemList(Item item){
        this.itemList.add(item);
    }

    public void removeItemList(Item item){
        this.itemList.remove(item);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total){
        this.total = total;
    }
}
