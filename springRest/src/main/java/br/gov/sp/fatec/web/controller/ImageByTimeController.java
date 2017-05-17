package br.gov.sp.fatec.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import br.gov.sp.fatec.model.ImagesCollection;
import br.gov.sp.fatec.service.ImageDateService;
import br.gov.sp.fatec.view.View;

@RestController
@Api(value = "Search Image by Date, Cycle, Wave Lenght and Stoke Parameter")
public class ImageByTimeController {

	@Autowired
	private ImageDateService imageDateService;

	public void setImageDateService(ImageDateService imageDateService) {
		this.imageDateService = imageDateService;
	}

	@ApiOperation(value = "Método que retorna os ids de todas as imagens de uma dia de observação.", notes = ""
			+ "Os parâmetros são constituídos pelo mês, dia, ano (padrão US) e page. O parâmetro 'page' é como se fosse"
			+ " páginas, e o valor constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)
	@RequestMapping(value = "/images/date/{month:[0-9]+}/{day:[0-9]{1,2}+}/{year:[0-9]{4}+}/{page:[0-9]+}", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Main.class)
	public ResponseEntity<Iterator<ImagesCollection>> getImageByDayMonthYear(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year, @PathVariable("page") int page) {

		try {
			return new ResponseEntity<Iterator<ImagesCollection>>(this.imageDateService.getImageByDayMonthYear
					(day, month, year, page),  HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<Iterator<ImagesCollection>>(HttpStatus.OK);
		}
	}


	@ApiOperation(value = "Método que retorna os ids das imagens dentro de um ciclo de observação em um dia.", notes = ""
			+ "Os parâmetros são constituídos pelo mês, dia e ano (padrão US) e page."
			+ "O parâmetro 'page' é como se fosse"
			+ " páginas, e o valor constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)
	@RequestMapping(value = "/images/date/{month:[0-9]+}/{day:[0-9]{1,2}+}/{year:[0-9]{4}+}/cycle/{cycle:[0-9]{1}}/{page:[0-9]+}", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Main.class)
	public ResponseEntity<Iterator<ImagesCollection>> getImageByDayMonthYearWithCycle(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year, @PathVariable("cycle") String cycle, @PathVariable("page") int page) {

		try {
			return new ResponseEntity<Iterator<ImagesCollection>>(this.imageDateService.getImageByDayMonthYearWithCycle(day, 
					month, year, cycle, page), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<Iterator<ImagesCollection>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Método que retorna os ids das imagens dentro de um ciclo, comprimento de onda e parâmetro de Stoke(opcional) em um dia."
			, notes = ""
			+ "Os parâmetros são constituídos pelo mês, dia e ano (padrão US), ciclo, comprimento de onda,"
			+ " parâmetro de Stoke (opcional) e page(opcional)."
			+ "O parâmetro de Stoke não é obrigatório então omitindo esse dado, "
			+ "estará supondo que pode pesquisar por quaisquer imagens independente "
			+ "de qual parâmetro de Stoke ele conter."
			+ "Os valores do parâmetro de Stoke são: 'Q', 'U', 'V' e 'I'.  "
			+ "O parâmetro 'page' é como se fosse"
			+ " páginas, e o valor que recebe constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)

	@RequestMapping(value = "/images/date/{month:[0-9]+}/{day:[0-9]{1,2}+}/{year:[0-9]{4}+}/cycle/{cycle:[0-9]{1}}/"
			+ "w_lenght", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Main.class)
	public ResponseEntity<Iterator<ImagesCollection>> getImageByWaze_L(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year, @PathVariable("cycle") String cycle,
			@RequestParam(value = "w", defaultValue = "0") List<String> waze_L,
			@RequestParam(value = "st", defaultValue = "0") List<String> stoke_Param,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		try {

			if (stoke_Param.get(0).equals("0"))
				return new ResponseEntity<Iterator<ImagesCollection>>(this.imageDateService.getImageByWaze(day, month, year, 
						cycle, waze_L, page), HttpStatus.OK);

			return new ResponseEntity<Iterator<ImagesCollection>>(this.imageDateService.getImageByWazeAndStoke(day, month, 
					year, cycle, waze_L, stoke_Param, page), HttpStatus.OK);
			
		} catch (IOException e) {
			return new ResponseEntity<Iterator<ImagesCollection>>(HttpStatus.BAD_REQUEST);// e.printStackTrace();
		}
	}

}
