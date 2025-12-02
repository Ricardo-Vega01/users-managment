package pruebaAdeA.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidoPaterno;

    @Column(nullable = false)
    private String apellidoMaterno;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate fechaAlta;

    private LocalDate fechaBaja;

    private LocalDate fechaModificacion;

    private LocalDate fechaVigencia;

    private LocalDate fechaRevocado;

    @Column(nullable = false)
    private int intentos = 0;

    @Column(nullable = false)
    private int noAccesos = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private StatusUserModel status = StatusUserModel.A;

    // Relations
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private AreaModel area;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientModel cliente;

    // Empty Constructor
    public UserModel(){}

    // Setters y Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public LocalDate getFechaRevocado() {
        return fechaRevocado;
    }

    public void setFechaRevocado(LocalDate fechaRevocado) {
        this.fechaRevocado = fechaRevocado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getNoAccesos() {
        return noAccesos;
    }

    public void setNoAccesos(int noAccesos) {
        this.noAccesos = noAccesos;
    }

    public StatusUserModel getStatus() {
        return status;
    }

    public void setStatus(StatusUserModel status) {
        this.status = status;
    }

    public AreaModel getArea() {
        return area;
    }

    public void setArea(AreaModel area) {
        this.area = area;
    }

    public ClientModel getCliente() {
        return cliente;
    }

    public void setCliente(ClientModel cliente) {
        this.cliente = cliente;
    }
}

