package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ReservationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String total;
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
    private Date registrationTime;
    private Boolean enabled;
    private Date scheduleTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservation_service", joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemList = new ArrayList<>();

    public Reservation(){

    }

    public Reservation(Long id, String total, Date registrationTime, Date scheduleTime, Boolean enabled, Client client) {
        this.id = id;
        this.total = total;
        this.registrationTime = registrationTime;
        this.scheduleTime = scheduleTime;
        this.enabled = enabled;
        this.client = client;
    }

    public Reservation(ReservationDTO dto){
        id = dto.getId();
        total = dto.getTotal();
        registrationTime = dto.getRegistrationTime();
        scheduleTime = dto.getScheduleTime();
        enabled = dto.getEnabled();
        client = dto.getClient();

        itemList = dto.getItemList();

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

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItemList(Item item){
        this.itemList.add(item);
    }

    public void removeAllItemList(){
        this.itemList.clear();
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal(){
        return total;
    }

}
