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
	private int record;
	private Level level;

	public Record(Level level) throws RecordException {
		record = 0;
		this.level = level;
	}

	int getRecord() {
		return record;
	}

	public void loadRecords(String fileName) throws RecordException {

		FileReader archivo;
		BufferedReader lector;
		try {
			archivo = new FileReader(fileName);

			lector = new BufferedReader(archivo);
			String cadena;
			while ((cadena = lector.readLine()) != null) {
				Scanner scanner = new Scanner(cadena);
				String str = scanner.next();
				if (str.equals(level.toString() + ":")) { // comprobamos si esta el nivel
					if (scanner.hasNextInt()) { //comprobamos que hay un numero
						record = scanner.nextInt();
					}
				}

			}
			lector.close();

		} catch (Exception e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
	}

	public void saveRecord(String fileName, int score) throws RecordException {

		boolean escrito = false;
		try {

			FileReader archivoLector = new FileReader(fileName);
			BufferedReader lector = new BufferedReader(archivoLector);

			List<String> linea = new ArrayList();
			String cadena;
			int i = 0;
			while ((cadena = lector.readLine()) != null) {
				Scanner scanner = new Scanner(cadena);
				String str = scanner.next();
				if (str.equals(level.toString() + ":")) {
					// Reemplaza la puntuación existente por la nueva
					escrito = true; // ha encontrado su nivel creado entonces no hace falta crearlo
					cadena = level.toString() + ": " + score;

				}

				linea.add(cadena);

				i++;
			}
			lector.close();
			for (String tira : linea) {
				System.out.print(tira);
			}
			FileWriter archivoEscritor = new FileWriter(fileName);
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
			record = score; // actualiza el nuevo record del nivel

		} catch (Exception e) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}
	}
}
