package tp1.p3.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import tp1.p3.control.Level;
import tp1.p3.control.exceptions.RecordException;

public class Record {
	private int record;
	private Level level;

	public Record(Level level) throws RecordException {
		record = 0;
		loadRecords("C:\\record.txt");
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
			if (archivo.ready()) {
				lector =  new BufferedReader(archivo);
				String cadena;
				while((cadena = lector.readLine())!= null) {
					if (Level.valueOfIgnoreCase(cadena) == level) {
						cadena = lector.readLine();
						record = Integer.parseInt(cadena);
					}
				}
			}
			else {
				System.out.print("NO SE PREPARO");
			}
			
		} catch (Exception e) {
			throw new RecordException("Error al cargar el fichero de records: " + e.getMessage());
		}
	}

	public void saveRecord(String fileName, int record) throws RecordException {
		try {
			// Guardar los records del mapa en el fichero
		} catch (Exception e) {
			throw new RecordException("Error al guardar el fichero de records: " + e.getMessage());
		}
	}
}
