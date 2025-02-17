package com.payment.strip_payment.service;

import com.payment.strip_payment.dto.ProductRequest;
import com.payment.strip_payment.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class StripService {
    @Value("${stripe.secretKey}")
    private String secretKey;

//strip-API
    //->productName ,amount, quantity, currency
    //-> return sessionId and url

 public StripeResponse checkoutProducts(ProductRequest productRequest){
     Stripe.apiKey=secretKey;
     SessionCreateParams.LineItem.PriceData.ProductData productData =  SessionCreateParams.LineItem.PriceData.ProductData.builder()
             .setName(productRequest.getName()).build();

     SessionCreateParams.LineItem.PriceData priceData =  SessionCreateParams.LineItem.PriceData.builder()
             .setCurrency(productRequest.getCurrency()==null?"USD":productRequest.getCurrency())
             .setUnitAmount(productRequest.getAmount())
             .setProductData(productData)
             .build();

     SessionCreateParams.LineItem lineItem= SessionCreateParams.LineItem.builder()
             .setQuantity(productRequest.getQuantity())
             .setPriceData(priceData)
             .build();


     SessionCreateParams params=  SessionCreateParams.builder()
             .setMode(SessionCreateParams.Mode.PAYMENT)
             .setSuccessUrl("http://localhost:8080/succes")
             .setCancelUrl("http://localhost:8080/done")
             .addLineItem(lineItem)
             .build();

     Session session=null;

     try {
            session = Session.create(params);
        } catch (StripeException ex) {
            System.err.println("Stripe API Error: " + ex.getMessage());
            throw new RuntimeException("Payment session creation failed", ex);
        }

        // Handle null session properly
        if (session == null) {
            throw new RuntimeException("Failed to create Stripe session.");
        }
     return   StripeResponse.builder()
             .status("SUCCESS")
             .message("Payment session created")
             .sessionId(session.getId())
             .sessionUrl(session.getUrl())
             .build();


 }


}
//@Service
//public class StripService {
//
//    @Value("${stripe.secretKey}")
//    private String secretKey;
//
//    public StripeResponse checkoutProducts(ProductRequest productRequest) {
//        Stripe.apiKey = secretKey;  // Ensure secretKey is correctly injected
//
//        // Validate product request
//        if (productRequest.getAmount() <= 0 || productRequest.getQuantity() <= 0) {
//            throw new IllegalArgumentException("Invalid product amount or quantity");
//        }
//
//        SessionCreateParams.LineItem.PriceData.ProductData productData =
//                SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                        .setName(productRequest.getName())
//                        .build();
//
//        SessionCreateParams.LineItem.PriceData priceData =
//                SessionCreateParams.LineItem.PriceData.builder()
//                        .setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
//                        .setUnitAmount(productRequest.getAmount())
//                        .setProductData(productData)
//                        .build();
//
//        SessionCreateParams.LineItem lineItem =
//                SessionCreateParams.LineItem.builder()
//                        .setQuantity(productRequest.getQuantity())
//                        .setPriceData(priceData)
//                        .build();
//
//        SessionCreateParams params =
//                SessionCreateParams.builder()
//                        .setMode(SessionCreateParams.Mode.PAYMENT)
//                        .setSuccessUrl("http://localhost:8080/success")
//                        .setCancelUrl("http://localhost:8080/cancel")
//                        .addLineItem(lineItem)
//                        .build();
//
//        Session session = null;
//
//        try {
//            session = Session.create(params);
//        } catch (StripeException ex) {
//            System.err.println("Stripe API Error: " + ex.getMessage());
//            throw new RuntimeException("Payment session creation failed", ex);
//        }
//
//        // Handle null session properly
//        if (session == null) {
//            throw new RuntimeException("Failed to create Stripe session.");
//        }
//
//        // Ensure StripeResponse class supports builder pattern
//        return StripeResponse.builder()
//                .status("SUCCESS")
//                .message("Payment session created")
//                .sessionId(session.getId())
//                .sessionUrl(session.getUrl())
//                .build();
//    }
//}
//
