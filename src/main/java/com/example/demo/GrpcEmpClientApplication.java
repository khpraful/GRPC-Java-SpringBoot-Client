package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emp.grpc.EmployeeOuterClass.Employee;
import com.emp.grpc.EmployeeOuterClass.EmployeeResponse;
import com.emp.grpc.EmployeeServiceGrpc;
import com.emp.grpc.EmployeeServiceGrpc.EmployeeServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcEmpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcEmpClientApplication.class, args);
		
ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		
		
		EmployeeServiceBlockingStub employeeStub = EmployeeServiceGrpc.newBlockingStub(channel);
		
		Employee employee = Employee.newBuilder().setEmpID(100).setEmpName("John").setEmpDept("Engineering").build();
		EmployeeResponse response = employeeStub.getEmployee(employee);
		
		System.out.println ("Message: " + response.getMessage());
		
	}

}
