package com.cyz.boot;
import com.cyz.boot.filter.TraceFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author:cyz
 * @Date:2020/4/3 16:39
 * @Description:
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FilterApplication.class)
public class FilterTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new TraceFilter())
                .build();
    }

    @Test
    public void testInsert() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .post("/hello")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"user1\",\"sex\":1,\"birthday\":\"2000-01-01\"}")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
