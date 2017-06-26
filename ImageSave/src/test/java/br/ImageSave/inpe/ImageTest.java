package br.ImageSave.inpe;

import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.inpe.filesystem.MoveImage;
import br.inpe.log.FileSystemResult;
import br.inpe.log.Log;
import br.inpe.model.ConvertTime;
import br.inpe.model.Image;
import br.inpe.model.ImageFits;
import br.inpe.repository.ImageRepository;
import br.inpe.repository.ImageRepositoryTest;
import junit.framework.TestCase;
import nom.tam.fits.FitsException;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ImageRepositoryTest-context.xml" })
public class ImageTest {

	private static final String PATH = "/home/inpe/Test/test.fts";
	private static final String logPath = "/home/inpe/Test/logTest.txt";
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private ImageRepositoryTest imageRepositoryTest;
	@Autowired
	private MoveImage move;
	
	public void setMoveImage(MoveImage move) {
		this.move = move;
	}
	
	public void setImageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	public void setImageRepositoryTest(ImageRepositoryTest imageRepositoryTest) {
		this.imageRepositoryTest = imageRepositoryTest;
	}
	
	@Test
	public void testSaveIsOk() throws FitsException, 
									ParseException, IOException {
		ImageFits imageFits = new ImageFits(PATH);
		Image image = imageFits.getImage();	
		this.imageRepository.insert(image);
		Image imageLocal = this.imageRepositoryTest.findOne(image.getId());
		
		assertTrue(imageLocal != null);
		assertTrue(imageLocal.getId().equals(image.getId()));
		assertTrue(imageLocal.getDate().equals(image.getDate()));
	}
	
	@Test
	public void testMoveIsOk() throws IOException {
		String newPath = move.moveImage(PATH);
		assertTrue(Files.exists(Paths.get(newPath)));
	}
	
	@Test
	public void testSetResultPath() throws IOException{
		String path1 = "/home/inpe/path1";
		String path2 = "/home/inpe/path2";
		Log log = new Log(logPath);
		
		log.setLogPathOriginalAndDestination(path1, path2);
		List<String> logs = getLog(logPath);
		assertTrue(logs.get(logs.size()-2).equals(path1));
		assertTrue(logs.get(logs.size()-1).equals(path2));
	}
	
	@Test
	public void testSetResultCreate() throws IOException{
		
		Log log = new Log(logPath);
		log.setLogSucessful(FileSystemResult.CREATE_SUCCESSFUL);
		List<String> logs = getLog(logPath);
		
		assertTrue(!logs.get(logs.size()-1).equals(FileSystemResult.DELETE_SUCCESSFUL.toString()));
		assertTrue(logs.get(logs.size()-1).equals(FileSystemResult.CREATE_SUCCESSFUL.toString()));
	
	}
	
	@Test
	public void testSetResultMove() throws IOException{
		
		Log log = new Log(logPath);
		log.setLogSucessful(FileSystemResult.MOVE_SUCCESSFUL);
		List<String> logs = getLog(logPath);
		
		assertTrue(!logs.get(logs.size()-1).equals(FileSystemResult.CREATE_SUCCESSFUL.toString()));
		assertTrue(logs.get(logs.size()-1).equals(FileSystemResult.MOVE_SUCCESSFUL.toString()));
	
		
	}
	
	@Test
	public void testSetResultDelete() throws IOException{
		
		Log log = new Log(logPath);
		log.setLogSucessful(FileSystemResult.DELETE_SUCCESSFUL);
		List<String> logs = getLog(logPath);
		
		assertTrue(!logs.get(logs.size()-1).equals(FileSystemResult.CREATE_SUCCESSFUL.toString()));
		assertTrue(logs.get(logs.size()-1).equals(FileSystemResult.DELETE_SUCCESSFUL.toString()));
	
		
	}
	
	private List<String> getLog(String path) throws IOException{
		return Files.readAllLines(Paths.get(path));
	}
	
	@Test
	public void testConvertTimeDate() throws ParseException{
		
		String timeJuliano = "2011-07-06T02:36:17.12";
		ConvertTime ct = new ConvertTime();
		Document doc = ct.getDate(timeJuliano);
		
		assertTrue(doc.get("DAY").equals(06));
		assertTrue(doc.get("MONTH").equals(07));
		assertTrue(doc.get("YEAR").equals(2011));
	}

	@Test
	public void testConvertTimeTime() throws ParseException{
		
		String timeJuliano = "2011-07-06T02:36:17.12";
		ConvertTime ct = new ConvertTime();
		Document doc = ct.getTime(timeJuliano);

		assertTrue(doc.get("HOUR").equals(02));
		assertTrue(doc.get("MINUTE").equals(36));
		assertTrue(doc.get("SECOND").equals("17.12"));
	}

}
