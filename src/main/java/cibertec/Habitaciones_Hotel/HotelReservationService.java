package cibertec.Habitaciones_Hotel;
import java.time.LocalDate;

public class HotelReservationService {

     public String registrarReserva(String numeroHabitacion, String nombreCliente, LocalDate fechaReserva) {

        if (camposVacios(numeroHabitacion, nombreCliente, fechaReserva)) {
            return "Debe ingresar los datos requeridos";
        }
        if (!validarHabitacion(numeroHabitacion)) {
            return "Ingrese una habitación valida";
        }
        if (!validarNombre(nombreCliente)) {
            return "Recuerde que el nombre del cliente debe contener al menos cuatro caracteres";
        }
        if (!validarFecha(fechaReserva)) {
            return "Debe ingresar una fecha valida";
        }
        return "El registro ha sido exitoso";
    }
    private boolean camposVacios(String hab, String nom, LocalDate fec) {
        return hab == null || hab.trim().isEmpty() ||
                nom == null || nom.trim().isEmpty() ||
                fec == null;
    }
    private boolean validarHabitacion(String habitacion) {
        return habitacion.matches("^[1-3][0-9]{2}$");
    }

    private boolean validarNombre(String nombre) {
        if (nombre.length() < 4) return false;
        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }
    private boolean validarFecha(LocalDate fecha) {
        return fecha.isAfter(LocalDate.now());
    }
}