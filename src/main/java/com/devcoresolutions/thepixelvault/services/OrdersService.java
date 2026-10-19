package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.OrdersRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.Orders;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.models.UsersId;
import com.devcoresolutions.thepixelvault.repository.OrdersRepository;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
     private final UsersRepository usersRepository;

    public List<Orders> getAllByOrdersId(UsersId usersId){
        if(!usersRepository.existsById(usersId)) throw new ResourceNotFoundException("Usuario con el id " +
                usersId + " no encontrado");
        return ordersRepository.findByUsersId(usersId);
    }

    public Orders getOrderByUserAndOrderId(UsersId usersId, Long ordersId) {
        if (!usersRepository.existsById(usersId)) {
            throw new ResourceNotFoundException("Usuario con el id " + usersId + " no encontrado");
        }

        return ordersRepository.findByIdAndUsers_Id(ordersId, usersId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Orden con id " + ordersId + " no encontrada para el usuario con id " + usersId));
    }

    public Orders createOrderByUserId(UsersId usersId, OrdersRequest ordersRequest){

        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + usersId + "no encontrado")
        );
        //creando objeto para mapear el dto
        Orders orders = new Orders();
        orders.setOrderDate(ordersRequest.orders_date());
        orders.setTotal(ordersRequest.total());
        orders.setUsers(users);
        //guardando pedido
        return ordersRepository.save(orders);
    }

}
