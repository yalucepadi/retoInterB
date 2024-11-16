package com.reto.client.model.proxy.model.response;

import com.reto.client.model.proxy.model.request.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private String code;
    private int status;
    private String comment;
    private Product data;
}
