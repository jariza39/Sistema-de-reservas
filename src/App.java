// Crear un sistema de reservas de hotel que permita a los clientes corporativos reservar 
//múltiples habitaciones en diferentes hoteles para hospedar a su personal. Debes tener clases como Hotel, 
//Habitación, Cliente, y Reserva. Implementa métodos para listar habitaciones disponibles, realizar reservas y 
//cancelar reservas. Al final de la ejecución del programa debe mostrar la siguiente info:

// Nombre de la empresa que reservó
// Nombre y cédula de quien ocupará cada habitación y número de la habitación que ocupará. Esto para cada hotel.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static SistemaReservaHotel sistemaReservaHotel = new SistemaReservaHotel();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel1 = new Hotel("Hotel 1", crearHabitaciones(10));
        Hotel hotel2 = new Hotel("Hotel 2", crearHabitaciones(20));
        sistemaReservaHotel.agregarHotel(hotel1);
        sistemaReservaHotel.agregarHotel(hotel2);

        boolean salir = false;
        while (!salir) {
            System.out.println("Bienvenido al sistema de reservas de hotel");
            System.out.println("1. Ver habitaciones disponibles");
            System.out.println("2. Hacer una reserva");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Ver reservas");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1:
                    verHabitacionesDisponibles();
                    break;
                case 2:
                    hacerReserva();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    verReservas();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static List<Habitacion> crearHabitaciones(int cantidad) {
        List<Habitacion> habitaciones = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            habitaciones.add(new Habitacion(i));
        }
        return habitaciones;
    }

    private static void verHabitacionesDisponibles() {
        System.out.print("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();
        Hotel hotel = buscarHotelPorNombre(nombreHotel);
        if (hotel == null) {
            System.out.println("Hotel no encontrado");
            return;
        }
        List<Habitacion> habitacionesDisponibles = sistemaReservaHotel.obtenerHabitacionesDisponibles(hotel);
        System.out.println("Habitaciones disponibles en " + hotel.getNombre() + ":");
        for (Habitacion habitacion : habitacionesDisponibles) {
            System.out.println("Habitación " + habitacion.getNumero());
        }
    }

    private static void hacerReserva() {
        System.out.print("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();
        Hotel hotel = buscarHotelPorNombre(nombreHotel);
        if (hotel == null) {
            System.out.println("Hotel no encontrado");
            return;
        }
        List<Habitacion> habitacionesDisponibles = sistemaReservaHotel.obtenerHabitacionesDisponibles(hotel);
        System.out.println("Habitaciones disponibles en " + hotel.getNombre() + ":");
        for (Habitacion habitacion : habitacionesDisponibles) {
            System.out.println("Habitación " + habitacion.getNumero());
        }
        System.out.print("Ingrese el número de habitaciones que desea reservar: ");
        int cantidadHabitaciones = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número
        if (cantidadHabitaciones > habitacionesDisponibles.size()) {
            System.out.println("No hay suficientes habitaciones disponibles");
            return;
        }
        List<Habitacion> habitacionesAReservar = new ArrayList<>();
        for (int i = 0; i < cantidadHabitaciones; i++) {
            System.out.print("Ingrese el número de la habitación " + (i + 1) + ": ");
            int numeroHabitacion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número
            Habitacion habitacion = buscarHabitacionPorNumero(habitacionesDisponibles, numeroHabitacion);
            if (habitacion == null) {
                System.out.println("Habitación no encontrada");
                return;
            }
            habitacionesAReservar.add(habitacion);
        }
        System.out.print("Ingrese el nombre de la empresa que hace la reserva: ");
        String nombreEmpresa = scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente que ocupará las habitaciones: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese la cédula del cliente que ocupará las habitaciones: ");
        String cedulaCliente = scanner.nextLine();
        Cliente cliente = new Cliente(nombreCliente, cedulaCliente);
        Reserva reserva = sistemaReservaHotel.hacerReserva(hotel, habitacionesAReservar, cliente);
        System.out.println("Reserva realizada con éxito");
        System.out.println("Empresa: " + nombreEmpresa);
        System.out.println("Hotel: " + reserva.getHotel().getNombre());
        for (Habitacion habitacion : reserva.getHabitaciones()) {
            System.out.println("Habitación " + habitacion.getNumero() + ": " + reserva.getCliente().getNombre() + " (Cédula: " + reserva.getCliente().getCedula() + ")");
        }
    }

    private static void cancelarReserva() {
        System.out.print("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();
        Hotel hotel = buscarHotelPorNombre(nombreHotel);
        if (hotel == null) {
            System.out.println("Hotel no encontrado");
            return;
        }
        System.out.print("Ingrese el número de la habitación: ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número
        Reserva reserva = buscarReservaPorHotelYNumeroHabitacion(hotel, numeroHabitacion);
        if (reserva == null) {
            System.out.println("Reserva no encontrada");
            return;
        }
        sistemaReservaHotel.cancelarReserva(reserva);
        System.out.println("Reserva cancelada con éxito");
    }

    private static void verReservas() {
        sistemaReservaHotel.imprimirReservas();
    }

    private static Hotel buscarHotelPorNombre(String nombre) {
        for (Hotel hotel : sistemaReservaHotel.habitacionesPorHotel.keySet()) {
            if (hotel.getNombre().equals(nombre)) {
                return hotel;
            }
        }
        return null;
    }

    private static Habitacion buscarHabitacionPorNumero(List<Habitacion> habitaciones, int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }

    private static Reserva buscarReservaPorHotelYNumeroHabitacion(Hotel hotel, int numeroHabitacion) {
        for (Reserva reserva : sistemaReservaHotel.reservas) {
            if (reserva.getHotel().equals(hotel)) {
                for (Habitacion habitacion : reserva.getHabitaciones()) {
                    if (habitacion.getNumero() == numeroHabitacion) {
                        return reserva;
                    }
                }
            }
        }
        return null;
    }

}
