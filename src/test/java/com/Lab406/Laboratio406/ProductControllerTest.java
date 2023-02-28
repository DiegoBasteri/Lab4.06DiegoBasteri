package com.Lab406.Laboratio406;


import com.Lab406.Laboratio406.dto.ProductDTO;
import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import com.Lab406.Laboratio406.repositories.DepartmentRepository;
import com.Lab406.Laboratio406.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WebApplicationContext context;

    @Autowired
    DepartmentRepository departmentRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    Product product1;
    Department department1;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        department1 = departmentRepository.save(new Department("Test Department"));
        product1 = productRepository.save(new Product(department1, "product Test", 200));
    }



    // TEST TO CREATE NEW PRODUCT

    @Test
    public void create_product() throws Exception {
        ProductDTO product = new ProductDTO("test", 1, 100);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/new-product")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));

        assertEquals(product.getName(), productRepository.findByName(product.getName()));
    }



    // TEST TO DECREASE QUANTITY OK

    @Test
    public void decrease_quantity_test_OK() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(patch("/decrease-quantity")
                .param("productId", product1.getProductId().toString())
                .param("quantity", "100")).andExpect(status().isOk()).andReturn();

        assertEquals(100, productRepository.findById(product1.getProductId()).get().getQuantity());
    }

    // TEST TO DECREASE QUANTITY THROWING EXCEPTION

    @Test
    public void decrease_quantity_FAIL() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(patch("/decrease-quantity")
                .param("productId", product1.getProductId().toString())
                .param("quantity", "250")).andExpect(status().isBadRequest()).andReturn();
        //System.err.println(mvcResult.getResolvedException().getMessage());
        assertTrue(mvcResult.getResolvedException().getMessage().contains("Not enough stock"));
    }

    // TEST TO FIND ALL PRODUCTS BY DEPARTMENT

    @Test
    public void find_product_by_Department_OK() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(get("/product-department/tools"))
                .andExpect(status().isOk()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());

        List<Product> productList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(productRepository.findByDepartmentId(1).size(), productList.size());
    }

    //  TEST TO FIND PRODUCTS BY DEPARTMENT OF NON EXISTING DEPARTMENT
    @Test
    public void find_product_by_Department_FAIL() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(get("/product-department/tools"))
                .andExpect(status().isOk()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());

        List<Product> productList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(productRepository.findAll().size(), productList.size());
    }

    // TEST TO FIND PRODUCT BY ID OF AN EXISTING PRODUCT

    @Test
    public void find_productBy_id_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/product/" + product1.getProductId()))
                .andExpect(status().isOk()).andReturn();
        //converting response in to a JSON to retrive the name for the test
        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(json.get("name"), productRepository.findById(product1.getProductId()).get().getName());
    }

    // TEST TO FIND A PRODUCT BY ID THAT DOES NOT EXIST

    @Test
    public void find_product_id_FAIL() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/product/100" ))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("product deoes not exist"));
    }

    // TEST TO DELETE A PRODUCT BY ID

    @Test
    public void delete_product_id_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/product/" + product1.getProductId()))
                .andExpect(status().isOk()).andReturn();
        assertFalse(productRepository.findById(product1.getProductId()).isPresent());
    }

    // TEST TO DELETE A PRODUCT BY ID THAT DOES NOT EXIST

    @Test
    public void delete_product_id_FAIL() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/product/100"))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("product does not exist"));
    }


}



