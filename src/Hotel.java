import java.util.List;

public class Hotel {
    private String nombre;
    private List <Habitacion> habitaciones;

    public Hotel(String nombre, List<Habitacion> habitaciones) {
        this.nombre = nombre;
        this.habitaciones = habitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
    
}
