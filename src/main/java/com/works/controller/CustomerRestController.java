package com.works.controller;

import com.works.dto.CustomerLoginRequestDto;
import com.works.dto.CustomerRegisterRequestDto;
import com.works.entity.Customer;
import com.works.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody CustomerRegisterRequestDto customerRegisterRequestDto){
        return customerService.register(customerRegisterRequestDto);
    }

    @PostMapping("/login")//RequestBody json formatini cevirir /Valid gelen datayi dtoya gore validate eder
    public ResponseEntity login(@Valid @RequestBody CustomerLoginRequestDto customerLoginRequestDto){
        return customerService.login(customerLoginRequestDto);
    }

}