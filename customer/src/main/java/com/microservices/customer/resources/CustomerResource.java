package com.microservices.customer.resources;

import com.microservices.customer.dto.CustomerDTO;
import com.microservices.customer.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD REST APIs for Customer in microservices",
        description = "CRUD REST APIs in microservices to CREATE, UPDATE, FETCH AND DELETE customer details"
)
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public interface CustomerResource {

    @GetMapping("/all")
    @Operation(
            summary = "Get All Customers",
            description = "REST API to Get All customers Microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    ResponseEntity<List<CustomerDTO>> getAllCustomers();


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a Customer",
            description = "RESt API to create a Customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Bad Request"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO);


    @GetMapping
    @Operation(
            summary = "Get All Customers by Filters",
            description = "REST API to Get customers by Input Filters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    ResponseEntity<List<CustomerDTO>> getCustomersByFilters(@RequestParam(required = false) String id,
                                                            @Valid @RequestParam(required = false) @Min(value = 3, message = "First name must be at least 3 characters")
                                                            String firstname,
                                                            @RequestParam(required = false) @Min(value = 3, message = "At least three characters required") String lastName,
                                                            @RequestParam(required = false) String state,
                                                            @RequestParam(required = false) @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter The valid Mobile Number") String mobileNumber,
                                                            @RequestParam(required = false) String city,
                                                            @RequestParam(required = false) String country,
                                                            @RequestParam(required = false) @Email(message = "Email to be in valid format") String email,
                                                            @RequestParam(required = false) String createdDateFrom,
                                                            @RequestParam(required = false) String createdDateTo,
                                                            @RequestParam(required = false) String modifiedFromDate,
                                                            @RequestParam(required = false) String modifiedToDate);

}
