package tp1.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
				if (str.equals(level.toString() + ":")) {
					if (scanner.hasNextInt()) {
						record = scanner.nextInt();
						// El número leído es "num"
					}
				}

			}
			lector.close();

		} catch (Exception e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
	}

	public void saveRecord(String fileName, int score) throws RecordException {
		System.out.print(level.toString());
		boolean escrito = false;
		try {
			FileReader archivoLector = new FileReader(fileName);
			FileWriter archivoEscritor = new FileWriter(fileName, false); // false indica que se sobrescribe el archivo completamente
			BufferedReader lector = new BufferedReader(archivoLector);

			BufferedWriter escritor = new BufferedWriter(archivoEscritor);

			String linea; // NO ENTRA EN EL BUCLE
			System.out.print("NO   ENTRA EN EL BUCLE     ");
			while ((linea = lector.readLine()) != null) {
				System.out.print("ENTRA EN EL BUCLE     ");
				Scanner scanner = new Scanner(linea);
				if (scanner.hasNext()) {
					String str = scanner.next();
					if (str.equals(level.toString() + ":")) {
						// Reemplaza la puntuación existente por la nueva
						escrito = true;
						escritor.write(level.toString() + ": " + score);
					} else {
						// Copia la línea tal cual al nuevo archivo
						escritor.write(linea);
						escritor.newLine();
						escritor.write("A VER SI VA");
					}

				}
				System.out.print("ENTRA EN EL BUCLE     ");
				escritor.newLine(); // Agrega una nueva línea al archivo
			}
			if (!escrito) {
				escritor.write(level.toString() + ": " + score);
				escritor.newLine();
			}

			lector.close();
			escritor.close();
			record = score; // acutalizar en el juego el record sin necesidad de cargar el archivo de nuevo

		} catch (Exception e) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}
	}
}
