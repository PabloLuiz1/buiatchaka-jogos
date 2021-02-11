package br.edu.fatec.buiatchaka.web.controle.analise;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.buiatchaka.web.service.admin.analise.AnaliseService;

@Controller
@RequestMapping("admin/analise")
public class AnaliseController {
	@Autowired
	private AnaliseService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/admin/analise/index");
		return mv;
	}
	
	@RequestMapping(value = "{dataInicio}-{dataFim}", method = RequestMethod.GET)
	public ModelAndView analisar(@PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFim) {
		ModelAndView mv = null;
		return mv;
	}
	
	@RequestMapping(value = "configurar", method = RequestMethod.GET)
	public ModelAndView configurar() {
		return new ModelAndView("/admin/analise/gerar");
	}
	
	@RequestMapping(value = "configurar", method = RequestMethod.POST)
	public ModelAndView gerarGrafico(@RequestParam String dataInicio, @RequestParam String dataFim) {
		ModelAndView mv = new ModelAndView("/admin/analise/index");
		mv.addObject("dataInicio", dataInicio);
		mv.addObject("dataFim", dataFim);
		return mv;
	}
}
