package tp1.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tp1.p3.control.Level;
import tp1.p3.control.exceptions.RecordException;
import tp1.p3.view.Messages;

public class Record {
	private static int scoreRecord;
	private Level level;

	public Record(Level level) throws RecordException {
		scoreRecord = 0;
		this.level = level;
	}

	int getRecord() {
		return scoreRecord;
	}

	public static Record loadRecords(Level level) throws RecordException {
		
		Record record = new Record(level);

		FileReader archivo;
		BufferedReader lector;
		try {
			archivo = new FileReader(Messages.RECORD_FILENAME);

			lector = new BufferedReader(archivo);
			String cadena;
			while ((cadena = lector.readLine()) != null) {
				Scanner scanner = new Scanner(cadena);
				try  {
				String str = scanner.next();
				if (str.equals(level.toString() + ":")) { // comprobamos si esta el nivel
					if (scanner.hasNextInt()) { //comprobamos que hay un numero
						scoreRecord = scanner.nextInt();
					}
				}
				}finally {
					  scanner.close();
				}

			}
			lector.close();

		} catch (Exception e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		return record;
	}

	public void saveRecord(int score) throws RecordException {

		boolean escrito = false;
		try {

			FileReader archivoLector = new FileReader(Messages.RECORD_FILENAME);
			BufferedReader lector = new BufferedReader(archivoLector);

			List<String> linea = new ArrayList<String>();
			String cadena;
			
			while ((cadena = lector.readLine()) != null) {
				Scanner scanner = new Scanner(cadena);
				try  {
					String str = scanner.next();
					if (str.equals(level.toString() + ":")) {
						// Reemplaza la puntuación existente por la nueva
						escrito = true; // ha encontrado su nivel creado entonces no hace falta crearlo
						cadena = level.toString() + ": " + score;

					}
				}finally {
					  scanner.close();
				}
				linea.add(cadena);


			}
			
			lector.close();
			
			FileWriter archivoEscritor = new FileWriter(Messages.RECORD_FILENAME);
			BufferedWriter escritor = new BufferedWriter(archivoEscritor);

			for (String tira : linea) {
				escritor.write(tira);
				escritor.newLine();
			}
			if (!escrito) {
				escritor.write(level.toString() + ": " + score);
				escritor.newLine();
			}
			escritor.close();
			

		} catch (Exception e) {
			
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
			
		}
		
	}
}
