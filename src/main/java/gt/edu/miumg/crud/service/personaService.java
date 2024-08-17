package gt.edu.miumg.crud.service;

import gt.edu.miumg.crud.model.Persona;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class personaService {

    private final String archivoPersonas = "personas.dat";


    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoPersonas))) {
            boolean continuar = true;
            while (continuar) {
                try {
                    personas.add((Persona) ois.readObject());
                } catch (EOFException e) {
                    continuar = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personas;
    }


    public Persona guardarPersona(Persona persona) {
        List<Persona> personas = listarPersonas();
        personas.add(persona);
        escribirArchivo(personas);
        return persona;
    }


    public Persona modificarPersona(int index, Persona persona) {
        List<Persona> personas = listarPersonas();
        if (index < personas.size()) {
            personas.set(index, persona);
            escribirArchivo(personas);
            return persona;
        }
        return null;
    }


    public boolean eliminarPersona(int index) {
        List<Persona> personas = listarPersonas();
        if (index < personas.size()) {
            personas.remove(index);
            escribirArchivo(personas);
            return true;
        }
        return false;
    }


    private void escribirArchivo(List<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPersonas))) {
            for (Persona persona : personas) {
                oos.writeObject(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
