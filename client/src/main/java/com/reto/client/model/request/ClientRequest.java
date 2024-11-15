package com.reto.client.model.request;

import com.reto.client.model.proxy.model.ProductDto;
import com.reto.client.utils.EncryptionService;
import com.reto.client.utils.SecureIdGenerator;
import com.reto.client.utils.TimestampService;
import com.reto.client.utils.ValidationService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Builder

@AllArgsConstructor

@Data
@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo_unico")
})
public class ClientRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_unico", length = 100, nullable = false, unique = true)
    private String codigoUnico;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<ProductDto> productosFinancieros = new ArrayList<>();

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private AuthRequest auth;

    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (codigoUnico == null) {
            SecureIdGenerator generator= new SecureIdGenerator();
            codigoUnico = generator.generateSecureId();
        }
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<ProductDto> getProductosFinancieros() {
        return productosFinancieros;
    }

    public void setProductosFinancieros(List<ProductDto> productosFinancieros) {
        this.productosFinancieros = productosFinancieros;
    }

    public AuthRequest getAuth() {
        return auth;
    }

    public void setAuth(AuthRequest auth) {
        this.auth = auth;
    }


    public void addProductoFinanciero(ProductDto producto) {
        if (this.productosFinancieros == null) {
            this.productosFinancieros = new ArrayList<>();
        }
        this.productosFinancieros.add(producto);
    }
}