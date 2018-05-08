/*package br.gov.sp.fatec.web.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
import br.gov.sp.fatec.model.Image;
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


	@ApiOperation(value = "Método que retorna os ids das imagens dentro de um ciclo, "
		+ "comprimento de onda, parâmetro de Stoke em um periodo do dia.", notes = ""
		+ "Os parâmetros são constituídos pelo mês, dia e ano (padrão US), "
		+ " ciclo (opcional), comprimento de onda(opcional),"
		+ " parâmetro de Stoke (opcional) hora (opcional), minuto (opcional),"
		+ " segundo (opcional) e page(opcional). O parâmetro de Stoke "
		+ "não é obrigatório então omitindo esse dado, estará supondo"
		+ " que pode pesquisar por quaisquer imagens independente de qual"
		+ " parâmetro de Stoke ele conter. Os valores do parâmetro de Stoke"
		+ "são: 'Q', 'U', 'V' e 'I'. O parâmetro 'page' é como se fosse"
		+ " páginas, e o valor que recebe constitui qual página o usuário "
		+ "quer receber. Este método retorna 500 ids de imagens. "
		+ "Para mostrar mais 500 ids de imagens, será necessário "
		+ "acrescentar o valor no parâmetro 'page'. "
		+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 "
			, response = String.class)

	@RequestMapping(value = "/images/date/{month:[0-9]{1,2}+}/{day:[0-9]{1,2}+}"
			+ "/{year:[0-9]{4}+}/param", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.Main.class)
	public ResponseEntity<Iterator<Image>> getImageByWaze_L(
			@PathVariable("day") int day, 
			@PathVariable("month") int month, 
			@PathVariable("year") int year, 
			@RequestParam(value = "cycle", defaultValue = "0") String cycle,
			@RequestParam(value = "w", defaultValue = "0") List<String> wave_L,
			@RequestParam(value = "st", defaultValue = "0") List<String> stoke_Param,
			@RequestParam(value = "hour", defaultValue = "-1") int hour,
			@RequestParam(value = "minute", defaultValue = "-1") int minute,
			@RequestParam(value = "second", defaultValue = "-1") float second,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("date.DAY").is(day));
			query.addCriteria(Criteria.where("date.MONTH").is(month));
			query.addCriteria(Criteria.where("date.YEAR").is(year));

			if(!cycle.equals("0"))
				query.addCriteria(Criteria.where("data.CYCLE").is(cycle));
			if(!wave_L.get(0).equals("0"))
				query.addCriteria(Criteria.where("data.WAZE_LEN").in(wave_L));
			if(!stoke_Param.get(0).equals("0"))
				query.addCriteria(Criteria.where("data.ST_PARAM").in(stoke_Param));
			if (hour > -1)
				query.addCriteria(Criteria.where("time.HOUR").is(hour));
			if (minute > -1)
				query.addCriteria(Criteria.where("time.MINUTE").is(minute));
			if (second > -1)
				query.addCriteria(Criteria.where("time.SECOND").is(
													Float.toString(second)));

			if (page > 0)
				return  new ResponseEntity<Iterator<Image>>(
								this.imageDateService.getImage
									(query.getQueryObject(), page), HttpStatus.OK);
			else
				return  new ResponseEntity<Iterator<Image>>(HttpStatus.BAD_REQUEST);
		}

		catch(IllegalArgumentException ia ){
			return  new ResponseEntity<Iterator<Image>>(HttpStatus.BAD_REQUEST);
		}
	}

	//	@ApiOperation(value = "Método que retorna os ids de todas as imagens de uma dia de observação.", notes = ""
	//			+ "Os parâmetros são constituídos pelo mês, dia, ano (padrão US) e page. O parâmetro 'page' é como se fosse"
	//			+ " páginas, e o valor constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
	//			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
	//			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)
	//	@RequestMapping(value = "/images/date/{month:[0-9]{1,2}+}/{day:[0-9]{1,2}+}/{year:[0-9]{4}+}/{page:[0-9]+}", method = RequestMethod.GET)
	//	@ResponseBody
	//	@JsonView(View.Main.class)
	//	public ResponseEntity<Iterator<Image>> getImageByDayMonthYear(@PathVariable("day") int day,
	//			@PathVariable("month") int month,
	//			@PathVariable("year") int year, @PathVariable("page") int page) {
	//
	//		try {
	//			return new ResponseEntity<Iterator<Image>>(this.imageDateService.getImageByDayMonthYear
	//					(day, month, year, page),  HttpStatus.OK);
	//		} catch (IOException e) {
	//			return new ResponseEntity<Iterator<Image>>(HttpStatus.OK);
	//			
	//		}
	//	}

	//
	//	@ApiOperation(value = "Método que retorna os ids das imagens dentro de um ciclo de observação em um dia.", notes = ""
	//			+ "Os parâmetros são constituídos pelo mês, dia e ano (padrão US) e page."
	//			+ "O parâmetro 'page' é como se fosse"
	//			+ " páginas, e o valor constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
	//			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
	//			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)
	//	@RequestMapping(value = "/images/date/{month:[0-9]{1,2}+}/{day:[0-9]{1,2}+}/{year:[0-9]{4}+}/cycle/{cycle:[0-9]{1}}/{page:[0-9]+}", method = RequestMethod.GET)
	//	@ResponseBody
	//	@JsonView(View.Main.class)
	//	public ResponseEntity<Iterator<Image>> getImageByDayMonthYearWithCycle(@PathVariable("day") int day, @PathVariable("month") int month,
	//			@PathVariable("year") int year, @PathVariable("cycle") String cycle, @PathVariable("page") int page) {
	//
	//		return new ResponseEntity<Iterator<Image>>(this.imageDateService.getImageByDayMonthYearWithCycle(day, 
	//				month, year, cycle, page), HttpStatus.OK);
	//	}

}
*/