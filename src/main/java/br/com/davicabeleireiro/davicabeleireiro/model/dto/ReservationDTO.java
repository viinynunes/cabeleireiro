package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDTO {
    private Long id;
    private String total;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
    private Date registrationTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
    private Date scheduleTime;
    private Boolean enabled;

    private Client client;

    private List<String> itemId = new ArrayList<>();

    private List<Item> itemList = new ArrayList<>();

    public ReservationDTO(){

    }

    public ReservationDTO(Long id, String total, Date registrationTime, Date scheduleTime, Boolean enabled, Client client) {
        this.id = id;
        this.total = total;
        this.registrationTime = registrationTime;
        this.scheduleTime = scheduleTime;
        this.enabled = enabled;
        this.client = client;
    }

    public ReservationDTO(Reservation reservation){
        id = reservation.getId();
        total = reservation.getTotal();
        registrationTime = reservation.getRegistrationTime();
        scheduleTime = reservation.getScheduleTime();
        enabled = reservation.getEnabled();
        client = reservation.getClient();

        itemList = reservation.getItemList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
