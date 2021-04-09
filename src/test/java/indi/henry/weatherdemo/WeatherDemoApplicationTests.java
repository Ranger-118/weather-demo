package indi.henry.weatherdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import indi.henry.weatherdemo.entity.CityEntity;
import indi.henry.weatherdemo.entity.WeatherResponse;
import indi.henry.weatherdemo.service.WeatherService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@Rollback
class WeatherDemoApplicationTests {


	private static final String SAMPLE_CITY = "Shenzhen";
	private static final String CITY_NOT_EXIST = "Somewhere";

	private static final String DEFAULT_URI = "/weather";

	private static final String CITY_NOT_EXIST_URI = DEFAULT_URI + "/" + CITY_NOT_EXIST;
	private static final String SAMPLE_CITY_URI = DEFAULT_URI + "/" + SAMPLE_CITY;

	private static final String CITY_NOT_FOUND_ERROR_MESSAGE = "404 Not Found";
	private static final String DELETE_CITY_NOT_EXIST_MESSAGE = "No class indi.henry.weatherdemo.entity.CityEntity entity with id ";

	private static final String NEW_ADDED_CITY = "Zhuhai";

    private MockMvc mvc;

	private WeatherService weatherService;

	private ObjectMapper objectMapper;

	@Autowired
	public WeatherDemoApplicationTests(MockMvc mvc,
									   WeatherService weatherService,
									   ObjectMapper objectMapper) {
		this.mvc = mvc;
		this.weatherService = weatherService;
		this.objectMapper = objectMapper;
	}

	@Test
	void testGetAllCity_AllGet() throws Exception {

		List<CityEntity> dbResult = weatherService.getWeatherAllInfo();

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(DEFAULT_URI)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse();

		List<CityEntity> resultList = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<CityEntity>>() {});

		assertEquals(dbResult, resultList);
	}

	@Test
	void testGetOne_Get() throws Exception {

		WeatherResponse dbResult = weatherService.getWeatherInfo(SAMPLE_CITY);

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(SAMPLE_CITY_URI)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse();

		WeatherResponse result = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<WeatherResponse>() {});

		assertEquals(dbResult, result);
	}

	@Test
	void testGetNotExistOne_ExpectationFailed() throws Exception {

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(CITY_NOT_EXIST_URI)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isExpectationFailed())
		.andReturn().getResponse();

		assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains(CITY_NOT_FOUND_ERROR_MESSAGE));
	}

	@Test
	void testAddOneCity_GetTheAdded() throws Exception {

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(DEFAULT_URI).contentType(MediaType.APPLICATION_JSON).content(NEW_ADDED_CITY)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse();

		WeatherResponse dbResult = weatherService.getWeatherInfo(NEW_ADDED_CITY);

		WeatherResponse result = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<WeatherResponse>() {});

		assertEquals(dbResult, result);
	}

	@Test
	void testAddOneCityNotExist_ExpectationFailed() throws Exception {

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(DEFAULT_URI).contentType(MediaType.APPLICATION_JSON).content(CITY_NOT_EXIST)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isExpectationFailed())
		.andReturn().getResponse();

		assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains(CITY_NOT_FOUND_ERROR_MESSAGE));
	}

	@Test
	void testDeleteOneCity_Success() throws Exception {

		WeatherResponse newAdded = weatherService.addWeatherCity(SAMPLE_CITY);

		assertNotNull(newAdded);

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete(SAMPLE_CITY_URI).contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse();

		Boolean result = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), new TypeReference<Boolean>() {});

		assertTrue(result);
	}


	@Test
	void testDeleteOneCity_ExpectationFailed() throws Exception {

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete(SAMPLE_CITY_URI).contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isExpectationFailed())
		.andReturn().getResponse();

		assertEquals(response.getContentAsString(StandardCharsets.UTF_8), DELETE_CITY_NOT_EXIST_MESSAGE.concat(SAMPLE_CITY).concat(" exists!"));
	}
}
