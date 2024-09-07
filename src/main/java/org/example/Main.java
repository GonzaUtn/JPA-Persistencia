package org.example;

import Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("SISTEMA FACTURACION");

        try {
            // Persistir una nueva entidad
            entityManager.getTransaction().begin();

            Factura factura1= Factura.builder().numero(0001).fecha("06/08/24").build();
            Factura factura2= Factura.builder().numero(0025).fecha("25/09/24").build();



            Domicilio domicilio1= Domicilio.builder().nombreCalle("Adolfo Calle").numero(7653).build();
            Domicilio domicilio2= Domicilio.builder().nombreCalle("Juan B. Justo").numero(5600).build();



            Cliente cliente1 = Cliente.builder()
                    .nombre("Gonzalo")
                    .apellido("Moyano")
                    .dni(42422123)
                    .build();

            Cliente cliente2 = Cliente.builder()
                    .nombre("Bianca")
                    .apellido("Poblete")
                    .dni(43556987)
                    .build();


            domicilio1.setCliente(cliente1);
            cliente1.setDomicilio(domicilio1);

            domicilio2.setCliente(cliente2);
            cliente2.setDomicilio(domicilio2);




            factura1.setCliente(cliente1);
            factura2.setCliente(cliente2);




            Categoria categoria1 = Categoria.builder().denominacion("Remeras").build();
            Categoria categoria2 = Categoria.builder().denominacion("Pantalones").build();
            Categoria categoria3 = Categoria.builder().denominacion("Camperas").build();


            Articulo art1 = Articulo.builder().cantidad(20).denominacion("Remera Corta negra Basic").precio(100).build();
            Articulo art2 = Articulo.builder().cantidad(20).denominacion("Remera larga Basic").precio(100).build();
            Articulo art3 = Articulo.builder().cantidad(10).denominacion("Pantalon chino negro Basic").precio(180).build();
            Articulo art4 = Articulo.builder().cantidad(25).denominacion("Pantalon Jean Oversiza").precio(200).build();
            Articulo art5 = Articulo.builder().cantidad(10).denominacion("Camperon Gris").precio(300).build();
            Articulo art6 = Articulo.builder().cantidad(10).denominacion("Campera polar basic").precio(150).build();
            Articulo art7 = Articulo.builder().cantidad(30).denominacion("Remera Corta c/Estampado").precio(120).build();
            Articulo art8 = Articulo.builder().cantidad(10).denominacion("Campera deportiva ").precio(250).build();



            art1.getCategoria().add(categoria1);
            art2.getCategoria().add(categoria1);
            art3.getCategoria().add(categoria2);
            art4.getCategoria().add(categoria2);
            art5.getCategoria().add(categoria3);
            art6.getCategoria().add(categoria3);
            art7.getCategoria().add(categoria1);
            art8.getCategoria().add(categoria3);

            categoria1.getArticulo().add(art1);
            categoria1.getArticulo().add(art2);
            categoria1.getArticulo().add(art7);
            categoria2.getArticulo().add(art3);
            categoria2.getArticulo().add(art4);
            categoria3.getArticulo().add(art5);
            categoria3.getArticulo().add(art6);
            categoria3.getArticulo().add(art8);




            DetalleFactura detalle1 = DetalleFactura.builder().build();
            DetalleFactura detalle2 = DetalleFactura.builder().build();
            DetalleFactura detalle3 = DetalleFactura.builder().build();
            DetalleFactura detalle4 = DetalleFactura.builder().build();
            DetalleFactura detalle5 = DetalleFactura.builder().build();
            DetalleFactura detalle6 = DetalleFactura.builder().build();


            detalle1.setArticulo(art1);
            detalle1.setCantidad(5);
            detalle1.setSubtotal(4500);
            art1.getDetalle().add(detalle1);

            detalle2.setArticulo(art5);
            detalle2.setCantidad(3);
            detalle2.setSubtotal(900);
            art5.getDetalle().add(detalle2);

            detalle3.setArticulo(art6);
            detalle3.setCantidad(5);
            detalle3.setSubtotal(900);
            art6.getDetalle().add(detalle3);

            factura1.getDetalle().add(detalle1);
            factura1.getDetalle().add(detalle2);
            factura1.getDetalle().add(detalle3);
            factura1.setTotal(6300);
            detalle1.setFactura(factura1);
            detalle2.setFactura(factura1);
            detalle3.setFactura(factura1);

            entityManager.persist(factura1);
            //-------------------------

            detalle4.setArticulo(art2);
            detalle4.setCantidad(4);
            detalle4.setSubtotal(400);
            art2.getDetalle().add(detalle4);

            detalle5.setArticulo(art3);
            detalle5.setCantidad(9);
            detalle5.setSubtotal(1620);
            art3.getDetalle().add(detalle5);

            detalle6.setArticulo(art7);
            detalle6.setCantidad(5);
            detalle6.setSubtotal(600);
            art7.getDetalle().add(detalle6);



            factura2.getDetalle().add(detalle4);
            factura2.getDetalle().add(detalle5);
            factura2.getDetalle().add(detalle6);

            factura2.setTotal(2620);
            detalle4.setFactura(factura2);
            detalle5.setFactura(factura2);
            detalle6.setFactura(factura2);

            entityManager.persist(factura2);




            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida



        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
