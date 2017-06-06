package br.gov.sp.fatec.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Image;
import br.gov.sp.fatec.service.ImageService;
import br.gov.sp.fatec.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Search Image by Id")
public class ImageController {

	private final int BUFFER_SIZE = 4096;
	private final String mimeType = "application/fts";
	@Autowired
	private ImageService imageService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String started() {
		return "Welcome to API";
	}

	@ApiOperation(value = "Método que retorna todas as imagens de forma páginada.", 
			notes = "O parâmetro 'page' é como se fosse páginas e o valor constitui de "
					+"em qual página o usuário quer receber. Este método retorna 500 ids de"
					+" imagens. Para mostrar mais 500 ids de imagens, é preciso acrescentar"
					+ " o valor no parâmetro 'page'. Ex: page = 1 - 1 a 500; "
					+ "page = 2 - 501 - 1000 ",response = Image.class)
	@RequestMapping(value = "images/{page}", method = RequestMethod.GET,
	produces = {MediaType.APPLICATION_JSON_VALUE })
	@JsonView(View.Main.class) @ResponseBody
	public ResponseEntity<Iterable<Image>> getAllId(@PathVariable("page") int page){
		if (page > 0)
			return new ResponseEntity<Iterable<Image>>(
					this.imageService.getAllId(page), HttpStatus.OK);
		else
			return new ResponseEntity<Iterable<Image>>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Este método retorna todos os dados "
			+ "do HEADER de uma imagem fits.", 
			notes = "", response = Image.class)
	@RequestMapping(value = "/image/id/{id:.+}", method = RequestMethod.GET)
	@ResponseBody @JsonView(View.All.class)
	public ResponseEntity<Image> getImageById(@PathVariable("id") String id) {
		try {

			Image image = this.imageService.getImageById(id);
			if (image == null)
				return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<Image>(image, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}	
	}


	@ApiOperation(value = "Este método disponibiliza o download de uma imagem fits,"
			+ " desde que, o id da imagem seja passado.", notes = "")
	@RequestMapping(value = "/image/download/{id:.+}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadImage(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("id") String id) throws IOException {
		try {
			String imagePath = this.imageService.getFilePath(id);

			if (imagePath == null) {
				response.sendError(404, "Dados não encontrado");
				OutputStream outStream = response.getOutputStream();
				outStream.close();
			} else {

				File downloadFile = new File(imagePath); 
				FileInputStream inputStream = new FileInputStream(downloadFile);	   				

				response.setContentType(this.mimeType);
				response.setContentLength((int) downloadFile.length());
				response.setHeader("Content-Disposition", String.format(
						"attachment; filename=\"%s\"", downloadFile.getName()));
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (IOException ex) {
			response.sendError(417, "Falha na expectativa de algum retorno");
			OutputStream outStream = response.getOutputStream();
			outStream.close();
		}
	}

	@ApiOperation(value = "Método que retorna os ids das imagens dentro de um ciclo, "
			+ "comprimento de onda, parâmetro de Stoke em um periodo do dia.", notes = ""
					+ "Os parâmetros são constituídos pelo mês, dia e ano (padrão US), ciclo "
					+ "(opcional), comprimento de onda(opcional), parâmetro de Stoke (opcional) "
					+ "hora (opcional), minuto (opcional), segundo (opcional) e page(opcional). "
					+ "O parâmetro de Stoke não é obrigatório então omitindo esse dado, estará "
					+ "supondo que pode pesquisar por quaisquer imagens independente de qual são:"
					+ " 'Q', 'U', 'V' e 'I'. O parâmetro 'page' é como se fosse páginas, e o valor"
					+ " que recebe constitui qual página o usuário quer receber. Este método "
					+ "retorna 500 ids de imagens. Para mostrar mais 500 ids de imagens, será "
					+ "necessário acrescentar o valor no parâmetro 'page'. Ex: page = 1 - 1 a 500;"
					+ " page = 2 - 501 - 1000 ", response = Image.class)
	@RequestMapping(value = "/images/date/{month:[0-9]{1,2}+}/{day:[0-9]{1,2}+}"
			+ "/{year:[0-9]{4}+}/param", method = RequestMethod.GET)
	@ResponseBody @JsonView(View.Main.class)
	public ResponseEntity<Iterable<Image>> getImageByParam(
			@PathVariable("day") int day, @PathVariable("month") int month, 
			@PathVariable("year") int year, 
			@RequestParam(value = "cycle",defaultValue = "0") String cycle, 
			@RequestParam(value = "w",defaultValue = "0") List<String> wave_L, 
			@RequestParam(value = "st",defaultValue = "0") List<String> stoke_Param, 
			@RequestParam(value = "hour",defaultValue = "-1") int hour, 
			@RequestParam(value = "minute",defaultValue = "-1") int minute,
			@RequestParam(value = "second",defaultValue = "-1") float second, 
			@RequestParam(value = "page", defaultValue = "1") int page) {

		if (page > 0)
			return new ResponseEntity<Iterable<Image>>(
					this.imageService.getImage(day, month, year, hour, minute,
							second, wave_L, stoke_Param, cycle, page), HttpStatus.OK);
		return new ResponseEntity<Iterable<Image>>(HttpStatus.BAD_REQUEST);
	}	



	@RequestMapping(value = "/images/download/date/{month:[0-9]{1,2}+}/{day:[0-9]{1,2}+}"
			+ "/{year:[0-9]{4}+}/param", method = RequestMethod.GET)
	@ResponseBody
	public void getZip(
			@PathVariable("day") int day, @PathVariable("month") int month, 
			@PathVariable("year") int year, HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value = "cycle",defaultValue = "0") String cycle, 
			@RequestParam(value = "w",defaultValue = "0") List<String> wave_L, 
			@RequestParam(value = "st",defaultValue = "0") List<String> stoke_Param, 
			@RequestParam(value = "hour",defaultValue = "-1") int hour, 
			@RequestParam(value = "minute",defaultValue = "-1") int minute,
			@RequestParam(value = "second",defaultValue = "-1") float second, 
			@RequestParam(value = "page", defaultValue = "1") int page) throws IOException {

		try{

			String pathZip = null;
			if (page > 0)
				pathZip = this.imageService.getPathZip(day, month, year, hour, minute,
						second, wave_L, stoke_Param, cycle, page);


			if (pathZip == null) {
				response.sendError(404, "Dados não encontrado");
				OutputStream outStream = response.getOutputStream();
				outStream.close();
			} else {

				File downloadFile = new File(pathZip); 
				FileInputStream inputStream = new FileInputStream(downloadFile);	   				

				response.setContentType("application/zip");
				response.setContentLength((int) downloadFile.length());
				response.setHeader("Content-Disposition", String.format(
						"attachment; filename=\"%s\"", downloadFile.getName()));
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
				
				Files.delete(Paths.get(pathZip));
			}
		} catch (IOException ex) {
			response.sendError(417, "Falha na expectativa de algum retorno");
			OutputStream outStream = response.getOutputStream();
			outStream.close();
		}
	}	



}
