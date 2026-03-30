package cibertec.Habitaciones_Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationServiceTest {

    private HotelReservationService service;

    @BeforeEach
    public void Inicializar() {
        service = new HotelReservationService();
    }
    @Test
    @DisplayName("Campos Vacios")
    public void CamposVacios() {
        String res1 = service.registrarReserva("", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Debe ingresar los datos requeridos", res1);
        String res2 = service.registrarReserva("101", "", LocalDate.now().plusDays(1));
        assertEquals("Debe ingresar los datos requeridos", res2);
        String res3 = service.registrarReserva("101", "Juan Perez", null);
        assertEquals("Debe ingresar los datos requeridos", res3);
    }
    @Test
    @DisplayName("Validacion Numero Habitacion")
    public void ValidacionNumeroHabitacion() {
        String res1 = service.registrarReserva("401", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Ingrese una habitación valida", res1);
        String res2 = service.registrarReserva("12", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Ingrese una habitación valida", res2);
        String res3 = service.registrarReserva("1A2", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Ingrese una habitación valida", res3);
    }
    @Test
    @DisplayName("Validacion Nombre Cliente")
    public void ValidacionNombreCliente() {
        String res1 = service.registrarReserva("101", "Ana", LocalDate.now().plusDays(1));
        assertEquals("Recuerde que el nombre del cliente debe contener al menos cuatro caracteres", res1);
        String res2 = service.registrarReserva("101", "Juan1", LocalDate.now().plusDays(1));
        assertEquals("Recuerde que el nombre del cliente debe contener al menos cuatro caracteres", res2);
    }
    @Test
    @DisplayName("Validacion Fecha")
    public void ValidacionFecha() {
        LocalDate hoy = LocalDate.now();
        LocalDate ayer = hoy.minusDays(1);
        String res1 = service.registrarReserva("101", "Juan", ayer);
        assertEquals("Debe ingresar una fecha valida", res1);
        String res2 = service.registrarReserva("101", "Juan", hoy);
        assertEquals("Debe ingresar una fecha valida", res2);
    }
    @Test
    @DisplayName("Registro Exitoso")
    public void RegistroExitoso() {
        LocalDate futuro = LocalDate.now().plusDays(5);
        String resultado = service.registrarReserva("205", "Carlos", futuro);
        assertEquals("El registro ha sido exitoso", resultado);
    }
    @Test
    @DisplayName("Validacion Numero Habitacion Demasiados Caracteres")
    public void ValidacionNumeroHabitacionDemasiadosCaracteres() {
        String res1 = service.registrarReserva("1001", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Ingrese una habitación valida", res1);
        String res2 = service.registrarReserva("20500", "Juan Perez", LocalDate.now().plusDays(1));
        assertEquals("Ingrese una habitación valida", res2);
    }
}