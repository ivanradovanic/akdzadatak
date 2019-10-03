package proba;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Podaci {
	private static final Logger log = LoggerFactory.getLogger(Zadatak.class);

	private String ime;
	private String prezime;
	private String spol;
	private String datumRodenja;
	private String mjesto;
	private String drzava;

	public List<Podaci> loadFromCSV(String file) {
		List<Podaci> podaci = new ArrayList<Podaci>();
		Path pathToFile = Paths.get(file);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			log.info("Starting to read a file {}", file);
			// read the first line from the text file
			String line;

			// loop until all lines are read
			while ((line = br.readLine()) != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				Podaci podatak = new Podaci();
				podatak.ime = attributes[0];
				podatak.prezime = attributes[1];
				podatak.spol = attributes[2];
				podatak.datumRodenja = attributes[3];
				podatak.mjesto = attributes[4];
				podatak.drzava = attributes[5];

				podaci.add(podatak);

				log.info("Read a line of file: {}", line);

			}

			log.info("Successfully read a file!");
			return podaci;
		} catch (IOException e) {
			log.error("IOException while reading file: {}", e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
