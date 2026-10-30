/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemaventas;
import java.util.Scanner;


/**
 *
 * @author JOSE MIGUEL
 */
public class SistemaVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           Scanner sc = new Scanner(System.in);
   
        int totalVentas = 0;         
        int totalProductos = 0;      
        double totalVendido = 0;      
 
        int ventasEfectivo = 0;      
        int ventasTarjeta = 0;
        int ventasTransferencia = 0;
 
        double dineroEfectivo = 0;    
        double dineroTarjeta = 0;
        double dineroTransferencia = 0;
 
        double ventaMayor = 0;        
        double ventaMenor = 0;        
 
        // ------------------------------------------------------------
        // VARIABLES DEL MENU
        // ------------------------------------------------------------
        int opcion;
        String confirmar = "N";
 
        // ------------------------------------------------------------
        // MENU PRINCIPAL
        // ------------------------------------------------------------
        do {
            System.out.println();
            System.out.println("=====================================");
            System.out.println("   SISTEMA DE VENTAS Y FACTURACION   ");
            System.out.println("=====================================");
            System.out.println("1. Registrar una venta");
            System.out.println("2. Consultar caja");
            System.out.println("3. Mostrar estadisticas");
            System.out.println("4. Salir");
            System.out.print("Elija una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 
 
            switch (opcion) {
 
                // ====================================================
                // OPCION 1: REGISTRAR UNA VENTA
                // ====================================================
                case 1:
                    System.out.println();
                    System.out.println("--- REGISTRAR UNA VENTA ---");
 
                    // Validar que el numero de productos sea mayor que cero
                    System.out.print("Cuantos productos tendra la compra?: ");
                    int cantidadProductos = sc.nextInt();
                    sc.nextLine();
                    while (cantidadProductos <= 0) {
                        System.out.println("Error: el numero de productos debe ser mayor que cero.");
                        System.out.print("Cuantos productos tendra la compra?: ");
                        cantidadProductos = sc.nextInt();
                        sc.nextLine();
                    }
 
                    double subtotalVenta = 0;      
                    int unidadesVenta = 0;         
 
                    // Ingreso de productos 
                    for (int i = 1; i <= cantidadProductos; i++) {
                        System.out.println();
                        System.out.println("Producto " + i + " de " + cantidadProductos);
 
                        System.out.print("Codigo: ");
                        String codigo = sc.nextLine();
 
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
 
                        // Validacion de precios que sean mayor que cero
                        System.out.print("Precio (Q): ");
                        double precio = sc.nextDouble();
                        while (precio <= 0) {
                            System.out.println("Error: el precio debe ser mayor que cero.");
                            System.out.print("Precio (Q): ");
                            precio = sc.nextDouble();
                        }
 
                        // Validar la cantidad de dinero que sea mayor que cero
                        System.out.print("Cantidad: ");
                        int cantidad = sc.nextInt();
                        while (cantidad <= 0) {
                            System.out.println("Error: la cantidad debe ser mayor que cero.");
                            System.out.print("Cantidad: ");
                            cantidad = sc.nextInt();
                        }
                        sc.nextLine();
 
                        double subtotalProducto = precio * cantidad;
                        System.out.println(codigo + " - " + nombre + " | Subtotal: Q"
                                + String.format("%.2f", subtotalProducto));
 
                        subtotalVenta = subtotalVenta + subtotalProducto;
                        unidadesVenta = unidadesVenta + cantidad;
                    }
 
                    //Calculo del descuento
                    int porcentajeDescuento;
                    if (subtotalVenta < 200) {
                        porcentajeDescuento = 0;
                    } else if (subtotalVenta < 500) {
                        porcentajeDescuento = 5;
                    } else if (subtotalVenta < 1000) {
                        porcentajeDescuento = 8;
                    } else {
                        porcentajeDescuento = 10;
                    }
 
                    double descuento = subtotalVenta * porcentajeDescuento / 100;
                    double montoConDescuento = subtotalVenta - descuento;
                    double iva = montoConDescuento * 0.12;
                    double total = montoConDescuento + iva;
 
                    System.out.println();
                    System.out.println("Subtotal: Q" + String.format("%.2f", subtotalVenta));
                    System.out.println("Descuento (" + porcentajeDescuento + "%): Q"
                            + String.format("%.2f", descuento));
                    System.out.println("IVA (12%): Q" + String.format("%.2f", iva));
                    System.out.println("TOTAL A PAGAR: Q" + String.format("%.2f", total));
 
                    //Formas de pago
                    System.out.println();
                    System.out.println("Formas de pago:");
                    System.out.println("1) Efectivo");
                    System.out.println("2) Tarjeta");
                    System.out.println("3) Transferencia");
                    System.out.print("Elija la forma de pago: ");
                    int formaPago = sc.nextInt();
                    sc.nextLine();
 
                   
                    while (formaPago < 1 || formaPago > 3) {
                        System.out.println("Error: opcion de pago no valida.");
                        System.out.print("Elija la forma de pago (1-3): ");
                        formaPago = sc.nextInt();
                        
                    }
 
                    String nombrePago = "";
                    double efectivoRecibido = 0;
                    double cambio = 0;
 
                    switch (formaPago) {
                        case 1:
                            nombrePago = "Efectivo";
                            System.out.print("Efectivo recibido (Q): ");
                            efectivoRecibido = sc.nextDouble();
                            sc.nextLine();
                            // Si el pisto no alcanza, se vuelve a pedir 
                            while (efectivoRecibido < total) {
                                System.out.println("Dinero insuficiente. Faltan Q"
                                        + String.format("%.2f", total - efectivoRecibido));
                                System.out.print("Dinero recibido (Q): ");
                                efectivoRecibido = sc.nextDouble();
                                sc.nextLine();
                            }
                            cambio = efectivoRecibido - total;
                            ventasEfectivo++;
                            dineroEfectivo = dineroEfectivo + total;
                            break;
                        case 2:
                            nombrePago = "Tarjeta";
                            ventasTarjeta++;
                            dineroTarjeta = dineroTarjeta + total;
                            break;
                        case 3:
                            nombrePago = "Transferencia";
                            ventasTransferencia++;
                            dineroTransferencia = dineroTransferencia + total;
                            break;
                    }
 
                    System.out.println();
                    System.out.println("=====================================");
                    System.out.println("          FACTURA DE LA VENTA        ");
                    System.out.println("=====================================");
                    System.out.println("Cantidad de productos: " + unidadesVenta);
                    System.out.println("Subtotal:              Q" + String.format("%.2f", subtotalVenta));
                    System.out.println("Descuento:             Q" + String.format("%.2f", descuento));
                    System.out.println("IVA:                   Q" + String.format("%.2f", iva));
                    System.out.println("Total:                 Q" + String.format("%.2f", total));
                    System.out.println("Forma de pago:         " + nombrePago);
                    if (formaPago == 1) {
                        System.out.println("Dinero recibido:     Q" + String.format("%.2f", efectivoRecibido));
                        System.out.println("Cambio:                Q" + String.format("%.2f", cambio));
                    }
                    System.out.println("=====================================");
 
                    //Actualizar contadores y acumuladores
                    totalVentas++;
                    totalProductos = totalProductos + unidadesVenta;
                    totalVendido = totalVendido + total;
 
                    // Mayor y menor venta
                    if (totalVentas == 1) {
                        ventaMayor = total;
                        ventaMenor = total;
                    } else {
                        if (total > ventaMayor) {
                            ventaMayor = total;
                        }
                        if (total < ventaMenor) {
                            ventaMenor = total;
                        }
                    }
                    break;
 
                // ====================================================
                // OPCION 2: CONSULTAR CAJA
                // ====================================================
                case 2:
                    System.out.println();
                    System.out.println("--- CAJA ---");
                    System.out.println("Ventas realizadas:       " + totalVentas);
                    System.out.println("Productos vendidos:      " + totalProductos);
                    System.out.println("Total vendido:           Q" + String.format("%.2f", totalVendido));
                    System.out.println("Ventas en efectivo:      " + ventasEfectivo
                            + " (Q" + String.format("%.2f", dineroEfectivo) + ")");
                    System.out.println("Ventas con tarjeta:      " + ventasTarjeta
                            + " (Q" + String.format("%.2f", dineroTarjeta) + ")");
                    System.out.println("Ventas por transferencia: " + ventasTransferencia
                            + " (Q" + String.format("%.2f", dineroTransferencia) + ")");
                    break;
 
                // ====================================================
                // OPCION 3: MOSTRAR ESTADISTICAS
                // ====================================================
                case 3:
                    System.out.println();
                    System.out.println("--- ESTADISTICAS ---");
                    if (totalVentas == 0) {
                        System.out.println("No existen ventas registradas.");
                    } else {
                        double promedio = totalVendido / totalVentas;
                        System.out.println("Numero total de ventas:         " + totalVentas);
                        System.out.println("Total de productos vendidos:    " + totalProductos);
                        System.out.println("Total de dinero vendido:        Q" + String.format("%.2f", totalVendido));
                        System.out.println("Promedio de dinero por venta:   Q" + String.format("%.2f", promedio));
                        System.out.println("Ventas en efectivo:             " + ventasEfectivo);
                        System.out.println("Ventas con tarjeta:             " + ventasTarjeta);
                        System.out.println("Ventas por transferencia:       " + ventasTransferencia);
                        System.out.println("Venta de mayor valor:           Q" + String.format("%.2f", ventaMayor));
                        System.out.println("Venta de menor valor:           Q" + String.format("%.2f", ventaMenor));
                    }
                    break;
 
                // ====================================================
                // OPCION 4: SALIR (con confirmacion)
                // ====================================================
                case 4:
                   System.out.print("Esta seguro que desea salir? (S/N): ");
                    confirmar = sc.nextLine();
                    
                    if (!confirmar.equalsIgnoreCase("S")) {
                        confirmar = "N";
                        System.out.println("Regresando al menu...");
                    } else {
                        System.out.println("Gracias por usar el sistema. Hasta pronto!");
                    }
                    break;
 
                // ====================================================
                // OPCION NO VALIDA
                // ====================================================
                default:
                    System.out.println("Opcion no valida. Elija un numero del 1 al 4.");
                    break;
            }
 
        } while (!(opcion == 4 && confirmar.equalsIgnoreCase("S")));
 
        
    }
}
 