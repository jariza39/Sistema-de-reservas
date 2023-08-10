import java.util.List;

public class Reserva {
    private Hotel hotel;
    private List<Habitacion> habitaciones;
    private Cliente cliente;

    public Reserva(Hotel hotel, List<Habitacion> habitaciones, Cliente cliente) {
        this.hotel = hotel;
        this.habitaciones = habitaciones;
        this.cliente = cliente;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
