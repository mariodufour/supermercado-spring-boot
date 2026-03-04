package com.proyectocode.Supermercado.controller;

import com.proyectocode.Supermercado.dto.VentaDTO;
import com.proyectocode.Supermercado.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas(){
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO dto){
        VentaDTO created = ventaService.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId())).body(created);
    }

    @PutMapping
    public VentaDTO actualizar(@PathVariable Long id, @RequestBody VentaDTO dto){
        //Actualiza fecha, estado, idSucursal, total y reemplaza el detalle

        return ventaService.actualizarVenta(id, dto);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
