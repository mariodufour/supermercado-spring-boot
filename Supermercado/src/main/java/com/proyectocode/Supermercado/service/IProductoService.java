package com.proyectocode.Supermercado.service;

import com.proyectocode.Supermercado.dto.ProductoDTO;
import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO ventaDto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO ventaDto);
    void eliminarProducto(Long id);
}
