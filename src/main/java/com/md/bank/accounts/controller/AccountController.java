package com.md.bank.accounts.controller;

import com.md.bank.accounts.Constant.ConstantUtils;
import com.md.bank.accounts.dto.CustomerDTO;
import com.md.bank.accounts.dto.ResponseDTO;
import com.md.bank.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in Bank",
        description = "CRUD REST APIs in BANK to CREATE, UPDATE, FETCH AND DELETE accounts details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;
    @Operation(
            summary = "Create Accounts REST API",
            description = "REST API to create new Customer & Account inside BANK"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        iAccountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(ConstantUtils.STATUS_201, ConstantUtils.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Accounts details REST API",
            description = "REST API to fetch accounts inside BANK"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobileNumber must be 10 digits")
            String mobileNumber){
        CustomerDTO customerDTO = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @Operation(
            summary = "Update Accounts details REST API",
            description = "REST API to update accounts inside BANK"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated = iAccountService.updateAccount(customerDTO);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(ConstantUtils.STATUS_200, ConstantUtils.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(ConstantUtils.STATUS_500, ConstantUtils.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Delete Accounts details REST API",
            description = "REST API to delete accounts inside BANK"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "mobileNumber must be 10 digits")
            String mobileNumber){
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(ConstantUtils.STATUS_200, ConstantUtils.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(ConstantUtils.STATUS_500, ConstantUtils.MESSAGE_500));
        }
    }
}
