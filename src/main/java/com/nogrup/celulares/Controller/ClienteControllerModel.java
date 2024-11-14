package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Entity.Cliente;
import com.nogrup.celulares.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/aplicacion")
public class ClienteControllerModel {

    @Autowired
    private ClienteService servicioCliente;

    @GetMapping("/cliente")
    public String ListaCliente(Model model) {
        List<Cliente> clientes = servicioCliente.listarClientes();
        model.addAttribute("clientes", clientes);
        return "Clientes/ListaClientes";
    }


}
