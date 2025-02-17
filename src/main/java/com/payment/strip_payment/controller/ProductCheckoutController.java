package com.payment.strip_payment.controller;

import com.payment.strip_payment.dto.ProductRequest;
import com.payment.strip_payment.dto.StripeResponse;
import com.payment.strip_payment.service.StripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductCheckoutController {

    @Autowired
    private StripService stripService;

    @PostMapping("/checkout")
   public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest){
       StripeResponse response=stripService.checkoutProducts(productRequest);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
   }


}
