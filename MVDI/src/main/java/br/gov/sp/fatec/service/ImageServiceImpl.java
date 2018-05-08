package br.gov.sp.fatec.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;

import br.gov.sp.fatec.model.Image;
import br.gov.sp.fatec.repository.ImageRepository;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public void setImageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public Image getImageById(String id) {
		return this.imageRepository.findOneById(id);
	}

	@Override
	public Iterable<Image> getAllId(int page) {
		Pageable pageable = new PageRequest((page - 1), 72);
		return this.imageRepository.getAllPagedImagesId(pageable).getContent();
	}

	@Override
	public String getFilePath(String id) {
		Image image = this.imageRepository.findOneById(id);
		if (image == null)
			return null;
		return image.getPath();
	}

	@Override
	public Iterable<Image> getImage(int day, int month, int year, int hour, int minute, float second, List<String> wave,
			List<String> stoke, String cycle, int page) {

		Pageable pagination = new PageRequest((page - 1), 500);
		return this.imageRepository
				.findImages(getDBObject(day, month, year, hour, minute, second, wave, stoke, cycle), pagination)
				.getContent();
	}

	private DBObject getDBObject(int day, int month, int year, int hour, int minute, float second, List<String> wave,
			List<String> stoke, String cycle) {

		Query query = new Query();
		query.addCriteria(Criteria.where("date.DAY").is(day));
		query.addCriteria(Criteria.where("date.MONTH").is(month));
		query.addCriteria(Criteria.where("date.YEAR").is(year));

		if (!cycle.equals("0"))
			query.addCriteria(Criteria.where("data.CYCLE").is(cycle));
		if (!wave.get(0).equals("0"))
			query.addCriteria(Criteria.where("data.WAZE_LEN").in(wave));
		if (!stoke.get(0).equals("0"))
			query.addCriteria(Criteria.where("data.ST_PARAM").in(stoke));
		if (hour > -1)
			query.addCriteria(Criteria.where("time.HOUR").is(hour));
		if (minute > -1)
			query.addCriteria(Criteria.where("time.MINUTE").is(minute));
		if (second > -1)
			query.addCriteria(Criteria.where("time.SECOND").is(Float.toString(second)));

		return query.getQueryObject();
	}

	@Override
	public String getPathZip(int day, int month, int year, int hour, int minute, float second, List<String> wave,
			List<String> stoke, String cycle, int page) {

		Iterable<Image> images = getImage(day, month, year, hour, minute, second, wave, stoke, cycle, page);

		return zipFile(images);
	}
	
	private String zipFile(Iterable<Image> images) {

		Date date = Calendar.getInstance().getTime();
		String pathZip = "/home/inpe/zip/" + Long.toString(date.getTime()) + ".zip";

		try {

			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(pathZip));

			for (Image image : images) {

				File fileImage = new File(image.getPath());

				zipOut.putNextEntry(new ZipEntry(fileImage.getName()));
				FileInputStream fis = new FileInputStream(fileImage);

				int content;
				while ((content = fis.read()) != -1) {
					zipOut.write(content);
				}

				zipOut.closeEntry();
			}
			zipOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return pathZip;
	}

}
