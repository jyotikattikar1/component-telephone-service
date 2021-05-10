package com.zeller.processor.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.belong.common.APIResponse;
import com.belong.common.ErrorCode;
import com.belong.telephone.TelephoneProcessorApplication;
import com.belong.telephone.controller.TelephoneProcessorController;
import com.belong.telephone.dto.CustomerData;
import com.belong.telephone.dto.CustomerDetailsDTO;
import com.belong.telephone.dto.TelephoneDetailDTO;
import com.belong.telephone.exception.ResourceNotFoundException;
import com.belong.telephone.service.CustomerDetailsService;
import com.belong.telephone.service.TelephoneService;
/**
 * 
 * @author jyotikattikar
 *
 */
@WebMvcTest(TelephoneProcessorController.class)
@ContextConfiguration(classes = { TelephoneProcessorApplication.class, TelephoneProcessorConfigTest.class })
public class TelephoneProcessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelephoneService telephoneService; 

    @MockBean
	private CustomerDetailsService customerDetailsService;
    

    @Test
    public void getAllTelephoneTest_Success() throws UnsupportedEncodingException, Exception {
        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
        APIResponse<CustomerDetailsDTO> responseBody = new APIResponse<CustomerDetailsDTO>();
        responseBody.setData(customerDetailsDTO);
        responseBody.setStatus(ErrorCode.OK.value());
        
        when(telephoneService.getAllCustomerPhoneDeatils(null, null, null, false))
                .thenReturn(responseBody);
        mockMvc.perform(get("/v1/telephone")).andExpect(status().isOk());
    }
    
    @Test
    public void getTelephoneDetailsTest_ByCustomerId() throws UnsupportedEncodingException, Exception {
    	CustomerData customerData = new CustomerData();
        APIResponse<CustomerData> responseBody = new APIResponse<CustomerData>();
        responseBody.setData(customerData);
        responseBody.setStatus(ErrorCode.OK.value());
        
        when(telephoneService.getTelephoneDetails(Mockito.anyString()))
                .thenReturn(responseBody);
        mockMvc.perform(get("/v1/telephone/customerId")).andExpect(status().isOk());
    }
    
    @Test
    public void getAllTelephone_ByCustomerIdTest_CustomerIdNotFound() throws UnsupportedEncodingException, Exception {
    	CustomerData customerData = new CustomerData();
        APIResponse<CustomerData> responseBody = new APIResponse<CustomerData>();
        responseBody.setData(customerData);
        responseBody.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.value());
        
        when(telephoneService.getTelephoneDetails(Mockito.anyString()))
                .thenThrow(new ResourceNotFoundException("{\"errorCode\" : \"404\", \"errorMessage\" : \"Customer id does not exists\"}"));
        mockMvc.perform(get("/v1/telephone/customerId")).andExpect(status().is4xxClientError());
    }

    @Test
    public void activateTelephonePhoneNumberTest() throws UnsupportedEncodingException, Exception {
    	TelephoneDetailDTO telephoneDetailDTO = new TelephoneDetailDTO();
    	telephoneDetailDTO.setActive(true);
    	telephoneDetailDTO.setTelephone(11111111L);
        APIResponse<TelephoneDetailDTO> responseBody = new APIResponse<TelephoneDetailDTO>();
        responseBody.setData(telephoneDetailDTO);
        responseBody.setStatus(ErrorCode.OK.value());
        
        when(telephoneService.activateTelephonePhoneNumber(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(responseBody);
        mockMvc.perform(put("/v1/telephone/customerId/activate?telephone=1111111113")).andExpect(status().isOk());
    }
   

}
