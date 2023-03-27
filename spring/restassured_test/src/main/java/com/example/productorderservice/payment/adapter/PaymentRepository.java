package com.example.productorderservice.payment.adapter;

import com.example.productorderservice.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
interface PaymentRepository extends JpaRepository<Payment, Long> {
}
