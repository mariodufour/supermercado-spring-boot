package com.proyectocode.Supermercado.mapper;

import com.proyectocode.Supermercado.dto.DetalleVentaDTO;
import com.proyectocode.Supermercado.dto.ProductoDTO;
import com.proyectocode.Supermercado.dto.SucursalDTO;
import com.proyectocode.Supermercado.dto.VentaDTO;
import com.proyectocode.Supermercado.model.Producto;
import com.proyectocode.Supermercado.model.Sucursal;
import com.proyectocode.Supermercado.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalle().stream().map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProducto().getId())
                        .nombreProd(det.getProducto().getNombre())
                        .cantProd(det.getCantidadProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantidadProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s) {
        if (s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}