package com.recila.reader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recila.reader.dto.ReciclaDTO;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			try {
				RestTemplate restTemplate = new RestTemplate();
				Map<String, String> map = (Map<String, String>)restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Map.class);
				
				System.out.println("Status API: " + map.get("type"));
				
				ObjectMapper mapper = new ObjectMapper();
				TypeReference<Collection<ReciclaDTO>> typeReference = new TypeReference<Collection<ReciclaDTO>>() {};
				
				File file = new File("/home/ronald/workspace/spring-recicla/recila.reader/recicla.json");
				List<ReciclaDTO> list = mapper.readValue(file, typeReference);
				
				Date inicio = stringToDate("2019-08-14 00:00:00");
				Date fim = stringToDate("2019-08-15 00:00:00");
				String bairro = "";
				
				final List<ReciclaDTO> filtered = getDataBetween(list, inicio, fim, bairro);
				
				Integer pesoTotal = getPesoTotal(filtered);
				Integer qtdTotal = getQtdTotal(filtered);
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				
				String msg = "No período entre " + formatter.format(inicio) + " e " + formatter.format(fim) + ", ";
				if (bairro != null && !bairro.isEmpty()) {
					msg += "no bairro " + bairro + ", ";
				} 
				
				msg += "no peso total foi " + pesoTotal + ", e a quantidade total foi " + qtdTotal;
				
				System.out.println(msg);
				
			} catch (Exception e) {
				System.out.println("Erro: " + e);
			}
		};
	}
	
	private Integer getPesoTotal(List<ReciclaDTO> list) {
		Integer pesoTotal = 0;
		
		for (ReciclaDTO dto : list) {
			pesoTotal += dto.getPeso();
		}
		
		return pesoTotal;
	}
	
	private Integer getQtdTotal(List<ReciclaDTO> list) {
		Integer qtdTotal = 0;
		
		for (ReciclaDTO dto : list) {
			qtdTotal += dto.getQuantidade();
		}
		
		return qtdTotal;
	}
	
	private List<ReciclaDTO> getDataBetween(List<ReciclaDTO> list, Date inicio, Date fim, String bairro) {
		List<ReciclaDTO> auxList = new ArrayList<>();
		
		for(ReciclaDTO dto : list) {
			Date data = stringToDate(dto.getCarimboDeDatahora());
			
			// Valida se está entre as datas
			if(data.after(inicio) && data.before(fim)) {
				
				// Valida se não passou nenhum bairro, ignora e adiciona de qualquer bairro
				if (bairro == null || bairro.isEmpty()) {
					auxList.add(dto);					
				} else {
					
					// Se passou bairro, somente adicionar os bairro correspondentes
					if (bairro.equals(dto.getBairro())) {
						auxList.add(dto);
					}
				}
			}
		}
		
		return auxList;
	}

	private Date stringToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			return formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
