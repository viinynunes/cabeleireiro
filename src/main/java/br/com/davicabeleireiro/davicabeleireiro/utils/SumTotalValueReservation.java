package br.com.davicabeleireiro.davicabeleireiro.utils;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;

import java.util.List;

public class SumTotalValueReservation {

    public static Double sumTotal(List<Item> itemList){
        double total = 0.0;
        for (Item item : itemList){
            total += item.getPrice();
        }
        return total;
    }

}
