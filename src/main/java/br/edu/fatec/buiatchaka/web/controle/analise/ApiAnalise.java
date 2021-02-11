package br.edu.fatec.buiatchaka.web.controle.analise;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.edu.fatec.buiatchaka.sistema.logging.Log;
import br.edu.fatec.buiatchaka.web.service.admin.analise.AnaliseService;

@RestController
@RequestMapping("admin/api/analise")
public class ApiAnalise {
	@Autowired
	private AnaliseService service;
	
	@RequestMapping(value = "{dataInicio}/{dataFim}", method = RequestMethod.GET)
	public ResponseEntity<?> process (@PathVariable String dataInicio, @PathVariable String dataFim){
		String headerJson = "{\"quantidade\": \"#quantidade\", \"mes\": \"#mes\", \"categoria\": \"#categoria\"}";
		List<Object> pedidos = service.processar(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));
		String jsonCompleto;
		String json = new Gson().toJson(pedidos).toString();
//		String quantidade = json.substring(2, 3);
////		jsonCompleto = headerJson.replace("#quantidade", json.substring(2, 3));
////		jsonCompleto = headerJson.replace("#mes", json.substring(4, 9));
//		jsonCompleto = headerJson.replace("#quantidade", json.substring(2, 3).
//				replace("#mes", json.substring(4, 9)).replace("#categoria", json.substring(11, json.length() - 3)));
//		Log.loggar(jsonCompleto);
		return ResponseEntity.ok().body(json);
	}
}
