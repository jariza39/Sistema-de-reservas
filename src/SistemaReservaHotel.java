import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaReservaHotel {
    Map<Hotel, List<Habitacion>> habitacionesPorHotel;
    List<Reserva> reservas;

    public SistemaReservaHotel() {
        this.habitacionesPorHotel = new HashMap<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarHotel(Hotel hotel) {
        habitacionesPorHotel.put(hotel, hotel.getHabitaciones());
    }

    public List<Habitacion> obtenerHabitacionesDisponibles(Hotel hotel) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitacionesPorHotel.get(hotel)) {
            if (habitacion.estaDisponible()) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

    public Reserva hacerReserva(Hotel hotel, List<Habitacion> habitaciones, Cliente cliente) {
        for (Habitacion habitacion : habitaciones) {
            habitacion.setDisponible(false);
        }
        Reserva reserva = new Reserva(hotel, habitaciones, cliente);
        reservas.add(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) {
        for (Habitacion habitacion : reserva.getHabitaciones()) {
            habitacion.setDisponible(true);
        }
        reservas.remove(reserva);
    }

    public void imprimirReservas() {
        for (Reserva reserva : reservas) {
            System.out.println("Empresa: " + reserva.getCliente().getNombre());
            System.out.println("Hotel: " + reserva.getHotel().getNombre());
            for (Habitacion habitacion : reserva.getHabitaciones()) {
                System.out.println("Habitación " + habitacion.getNumero() + ": " + reserva.getCliente().getNombre() + " (Cédula: " + reserva.getCliente().getCedula() + ")");
            }
        }
    }
}
