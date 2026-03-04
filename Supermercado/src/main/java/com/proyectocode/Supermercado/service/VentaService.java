package com.proyectocode.Supermercado.service;

import com.proyectocode.Supermercado.dto.DetalleVentaDTO;
import com.proyectocode.Supermercado.dto.VentaDTO;
import com.proyectocode.Supermercado.exception.NotFoundException;
import com.proyectocode.Supermercado.mapper.Mapper;
import com.proyectocode.Supermercado.model.DetalleVenta;
import com.proyectocode.Supermercado.model.Producto;
import com.proyectocode.Supermercado.model.Sucursal;
import com.proyectocode.Supermercado.model.Venta;
import com.proyectocode.Supermercado.repository.ProductoRepository;
import com.proyectocode.Supermercado.repository.SucursalRepository;
import com.proyectocode.Supermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }
        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        //Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar sucursal
        Sucursal sucursal = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
        if (sucursal == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());
        venta.setEstado(ventaDto.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(ventaDto.getTotal());

        //Lista de detalles
        //--> Acá estan los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {
            //Buscar producto por id
            Producto p = productoRepository.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado" + detDTO.getNombreProd());}

         //Crear detalle
         DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantidadProd(detDTO.getCantProd());
            detalleVent.setVenta(venta);

            detalles.add(detalleVent);
            totalCalculado =  totalCalculado + (detDTO.getPrecio()*detDTO.getCantProd());
        }

        //Seteamos la lista de detalle venta
        venta.setDetalle(detalles);

        //Guardamos en la BD
        venta = ventaRepository.save(venta);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(venta);
        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //Buscar si la venta existe para actualizarla
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if(ventaDto.getFecha()!=null) {
            v.setFecha(ventaDto.getFecha());
        }

        if (ventaDto.getEstado()!=null) {
            v.setEstado(ventaDto.getEstado());
        }

        if (ventaDto.getTotal()!=null) {
            v.setTotal(ventaDto.getTotal());
        }

        if (ventaDto.getIdSucursal()!=null) {
            Sucursal suc =  sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepository.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepository.delete(v);
    }
}
