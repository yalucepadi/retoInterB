package com.reto.client.model.request;

import com.reto.client.model.proxy.model.ProductDto;
import com.reto.client.utils.SecureIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder

@AllArgsConstructor

@Data
@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo_unico")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_unico", length = 100, nullable = false)
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

    // Relación OneToMany con la entidad Product
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")  // Agrega la clave foránea en la tabla `product`
    private List<ProductDto> productosFinancieros = new ArrayList<>();

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Auth auth;

    public Cliente() {
        SecureIdGenerator generator = new SecureIdGenerator();
        this.codigoUnico = generator.generateSecureId();
        this.fechaCreacion = LocalDateTime.now();
    }

    public void addProductoFinanciero(ProductDto producto) {
        if (this.productosFinancieros == null) {
            this.productosFinancieros = new ArrayList<>();
        }
        this.productosFinancieros.add(producto);
    }
}

