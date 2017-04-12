package com.webpilot;

import com.webpilot.domain.Registration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Title: RegistrationControllerTest
 * Description: RegistrationController integration test
 * Date: 4/11/17
 *
 * @author RGH
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class RegistrationControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private String userName = "bdussault";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void userNameNotFound() throws Exception {
//        mockMvc.perform(get("/george")
//                .content(this.json(new Registration()))
//                .contentType(contentType))
//                .andExpect(status().isNotFound());
//    }

    @Test
    public void readSingleUser() throws Exception {
        mockMvc.perform(get("/ace"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));


    }

    @Test
    public void readAllUsers() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void addUser() throws Exception {
        String registrationJson = json(new Registration("ace","ace@gmail.com", new Date()));
        mockMvc.perform(post("/add").contentType(contentType).content(registrationJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/ace"))
                .andExpect(status().isOk());
    }



    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
