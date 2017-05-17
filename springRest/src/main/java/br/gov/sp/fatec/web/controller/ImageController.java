package br.gov.sp.fatec.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.ImagesCollection;
import br.gov.sp.fatec.service.ImageService;
import br.gov.sp.fatec.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Search Image by Id")
public class ImageController {

	private final int BUFFER_SIZE = 4096;
	@Autowired
	private ImageService imageService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@ApiOperation(value = "Método que retorna todas as imagens de forma páginada.", notes = ""
			+ "O parâmetro 'page' é como se fosse"
			+ " páginas, e o valor constitui qual página o usuário quer receber. Este método retorna 500 ids de imagens. "
			+ "Para mostrar mais 500 ids de imagens, será necessário acrescentar o valor no parâmetro 'page'. "
			+ "Ex: page = 1 - 1 a 500; page = 2 - 501 - 1000 ", response = String.class)
	@RequestMapping(value = "/images/{page}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@JsonView(View.Main.class)
	@ResponseBody
	public ResponseEntity<Iterator<ImagesCollection>> getAllId(@PathVariable("page") int page) {
		if (page > 0)
			return new ResponseEntity<Iterator<ImagesCollection>>(this.imageService.getAllId(page), HttpStatus.OK);
		else
			return new ResponseEntity<Iterator<ImagesCollection>>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value = "Este método retorna todos os dados do HEADER de uma imagem fits.", notes = "", response = String.class)
	@RequestMapping(value = "/image/id/{id:.+}", method = RequestMethod.GET)
	@JsonView(View.All.class)
	@ResponseBody
	public ResponseEntity<ImagesCollection> getImageById(@PathVariable("id") String id) {
		try {

			ImagesCollection image = this.imageService.getImageById(id);

			if (image == null)
				return new ResponseEntity<ImagesCollection>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<ImagesCollection>(image, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<ImagesCollection>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Este método disponibiliza o download de uma imagem fits, desde que, o id da imagem seja passado.", notes = "")
	@RequestMapping(value = "/image/download/{id:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> downloadImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {
		try {
			ImagesCollection image = this.imageService.getFilePathSystem(id);

			if (image == null)
				return new ResponseEntity<String>("Dados não encontrados", HttpStatus.NOT_FOUND);
		
			ServletContext context = request.getSession().getServletContext();

			File downloadFile = new File(image.getPath());
			FileInputStream inputStream = new FileInputStream(downloadFile);

			String mimeType = context.getMimeType(image.getPath());

			if (mimeType == null) {
				mimeType = "application/fits";
			}

			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
			
			
			return new ResponseEntity<String>("Imagem " + id + " enviada com sucesso", HttpStatus.OK);

		} catch (IOException ex) {
			return new ResponseEntity<String>("Erro" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
