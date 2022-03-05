package com.eep.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.eep.service.CamioneroService;
import com.epp.model.Camionero;
import org.springframework.stereotype.Service;

@Service("camioneroService")
public class CamioneroServiceImpl implements CamioneroService {

    ArrayList<Camionero> camioneros;
    ArrayList<Camionero> coincidentes;

    boolean encontrado;
    boolean asciiValido;
    boolean mensajeUltimoCamionero;

    //Listar camioneros
    public List<Camionero> listarCamioneros() {

        File f = null;
        FileReader ficheroLeer = null;
        BufferedReader ficheroBuf = null;

        try {
            f = new File("src\\main\\java\\com\\eep\\repository\\camionerosdb.txt");
            ficheroLeer = new FileReader(f);
            ficheroBuf = new BufferedReader(ficheroLeer);
            camioneros = new ArrayList();

            String linea;

            while ((linea = ficheroBuf.readLine()) != null) {

                String partes[] = linea.split("#");

                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String apellidos = partes[2];
                String correo = partes[3];
                String telefono = partes[4];
                String nacimiento = partes[5];
                String genero = partes[6];
                String transporteMercancias = partes[7];
                String comentarios = partes[8];
                String contratadoActualmente = partes[9];

                Camionero camionero = new Camionero(id, nombre, apellidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);

                camioneros.add(camionero);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("No se puede leer el archivo." + e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("No se puede leer el archivo." + e.getMessage());
        } finally {
            try {
                if (ficheroLeer != null) {
                    ficheroLeer.close();
                    System.out.println();
                    System.out.println("El programa fue cerrado exitosamente.");
                    return camioneros;
                }
            } catch (IOException e) {
                System.out.println("No se puede cerrar el archivo." + e.getMessage());
            }
        }
        return camioneros;
    }

    //Añadir un nuevo camionero
    public void anyadirCamionero(Camionero camionero, String nombre) {

        listarCamioneros();

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\repository\\camionerosdb.txt", true);

            int generado = (int)(Math.random()*10000+1);
            for (int i = 0; i < camioneros.size(); i++) {
                if (generado == camioneros.get(i).getId()) {
                    generado=(int)(Math.random()*10000+1);
                }
            }

            fw.write(Integer.toString(generado));
            fw.write("#");
            fw.write(camionero.getNombre());
            fw.write("#");
            fw.write(camionero.getApellidos());
            fw.write("#");
            fw.write(camionero.getCorreo());
            fw.write("#");
            fw.write(camionero.getTelefono());
            fw.write("#");
            fw.write(camionero.getNacimiento());
            fw.write("#");
            fw.write(camionero.getGenero());
            fw.write("#");
            fw.write(camionero.getTransporteMercancias());
            fw.write("#");
            fw.write(camionero.getComentarios());
            fw.write("#");
            fw.write(camionero.getContratadoActualmente());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Borrar un camionero previamente buscado por nombre. Esto solo ocurre si en checkNombreExists se retornó true
    public List<Camionero> borrarCamionerosPorNombreYOApellidos(String nombre, String apellidos) {

        listarCamioneros();
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {
                camioneros.remove(i);
                break;
            } else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){
                camioneros.remove(i);
                break;
            } else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){
                camioneros.remove(i);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\repository\\camionerosdb.txt");

            for (int i = 0; i < camioneros.size(); i++) {
                fw.write(Integer.toString(camioneros.get(i).getId()));
                fw.write("#");
                fw.write(camioneros.get(i).getNombre());
                fw.write("#");
                fw.write(camioneros.get(i).getApellidos());
                fw.write("#");
                fw.write(camioneros.get(i).getCorreo());
                fw.write("#");
                fw.write(camioneros.get(i).getTelefono());
                fw.write("#");
                fw.write(camioneros.get(i).getNacimiento());
                fw.write("#");
                fw.write(camioneros.get(i).getGenero());
                fw.write("#");
                fw.write(camioneros.get(i).getTransporteMercancias());
                fw.write("#");
                fw.write(camioneros.get(i).getComentarios());
                fw.write("#");
                fw.write(camioneros.get(i).getContratadoActualmente());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return camioneros;
    }

    //Envío de un objeto camionero al controlador. Esto solo ocurre si en checkNombreExists se retornó true
    public Camionero buscarCamioneroPorNombre(String nombre, String apellidos) {

        listarCamioneros();
        Camionero camionero = null;
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                                          telefono, nacimiento, genero,
                                          transporteMercancias, comentarios,
                                          contratadoActualmente);
                break;
            }else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);
                break;
            }else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);
                break;
            }
        }
        return camionero;
    }

    public Camionero devolverCamioneroPorNombreYOApellidos(String nombre, String apellidos) {

        listarCamioneros();
        Camionero camionero = null;
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);
                break;
            }else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);
                break;
            }else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                                          telefono, nacimiento, genero,
                                          transporteMercancias, comentarios,
                                          contratadoActualmente);
                break;
            }
        }
        return camionero;
    }

    public Camionero devolverCamioneroPorId(int id) {

        listarCamioneros();
        Camionero camionero = null;
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getId()==id) {

                int idObtenido = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(idObtenido, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);
                break;
            }
        }
        return camionero;
    }

    public List<Camionero> camionerosCoincidentes(String nombre, String apellidos){
        coincidentes = new ArrayList();

        listarCamioneros();
        Camionero camionero = null;
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);

                coincidentes.add(camionero);
            }else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);

                coincidentes.add(camionero);
            }else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){

                int id = camioneros.get(i).getId();
                String nombreObtenido = camioneros.get(i).getNombre();
                String apellidosObtenidos = camioneros.get(i).getApellidos();
                String correo = camioneros.get(i).getCorreo();
                String telefono = camioneros.get(i).getTelefono();
                String nacimiento = camioneros.get(i).getNacimiento();
                String genero = camioneros.get(i).getGenero();
                String transporteMercancias = camioneros.get(i).getTransporteMercancias();
                String comentarios = camioneros.get(i).getComentarios();
                String contratadoActualmente = camioneros.get(i).getContratadoActualmente();

                camionero = new Camionero(id, nombreObtenido, apellidosObtenidos, correo,
                        telefono, nacimiento, genero,
                        transporteMercancias, comentarios,
                        contratadoActualmente);

                coincidentes.add(camionero);
            }
        }
        return coincidentes;
    }

    //Modificación del camionero: se remueve el camionero antiguo y se añade el nuevo introducido
    public void modificarCamionero(Camionero camionero) {

        listarCamioneros();
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getId() == camionero.getId()) {

                int id = camionero.getId();
                String nombre = camionero.getNombre();
                String apellidos = camionero.getApellidos();
                String correo = camionero.getCorreo();
                String telefono = camionero.getTelefono();
                String nacimiento = camionero.getNacimiento();
                String genero = camionero.getGenero();
                String transporteMercancias = camionero.getTransporteMercancias();
                String comentarios = camionero.getComentarios();
                String contratadoActualmente = camionero.getContratadoActualmente();

                camionero = new Camionero(id, nombre, apellidos, correo,
                                          telefono, nacimiento, genero,
                                          transporteMercancias, comentarios,
                                          contratadoActualmente);

                camioneros.remove(i);
                camioneros.add(camionero);

                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\repository\\camionerosdb.txt");

            for (int i = 0; i < camioneros.size(); i++) {
                fw.write(Integer.toString(camioneros.get(i).getId()));
                fw.write("#");
                fw.write(camioneros.get(i).getNombre());
                fw.write("#");
                fw.write(camioneros.get(i).getApellidos());
                fw.write("#");
                fw.write(camioneros.get(i).getCorreo());
                fw.write("#");
                fw.write(camioneros.get(i).getTelefono());
                fw.write("#");
                fw.write(camioneros.get(i).getNacimiento());
                fw.write("#");
                fw.write(camioneros.get(i).getGenero());
                fw.write("#");
                fw.write(camioneros.get(i).getTransporteMercancias());
                fw.write("#");
                fw.write(camioneros.get(i).getComentarios());
                fw.write("#");
                fw.write(camioneros.get(i).getContratadoActualmente());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean checkCoincidentes(String nombre, String apellidos) {

        int coincidencias=0;

        listarCamioneros();
        for (int i = 0; i < camioneros.size(); i++) {

            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {
                coincidencias++;
            } else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){
                coincidencias++;
            }else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){
                coincidencias++;
            }

            if(coincidencias>=2){
                encontrado=true;
            }else{
                encontrado=false;
            }
        }
        return encontrado;
    }

    public boolean coincidenciaUnica(String nombre, String apellidos) {

        int coincidencias=0;

        listarCamioneros();
        for (int i = 0; i < camioneros.size(); i++) {

            if (camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==true) {
                coincidencias++;
            } else if(camioneros.get(i).getNombre().equals(nombre)==true && camioneros.get(i).getApellidos().equals(apellidos)==false){
                coincidencias++;
            }else if(camioneros.get(i).getNombre().equals(nombre)==false && camioneros.get(i).getApellidos().equals(apellidos)==true){
                coincidencias++;
            }

            if(coincidencias==1){
                encontrado=true;
            }else{
                encontrado=false;
            }
        }
        return encontrado;
    }

    //Borrar un camionero previamente buscado por nombre. Esto solo ocurre si en checkNombreExists se retornó true
    public List<Camionero> borrarCamionerosId(int id) {

        listarCamioneros();
        for (int i = 0; i < camioneros.size(); i++) {
            if (camioneros.get(i).getId()==id){
                camioneros.remove(i);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\repository\\camionerosdb.txt");

            for (int i = 0; i < camioneros.size(); i++) {
                fw.write(Integer.toString(camioneros.get(i).getId()));
                fw.write("#");
                fw.write(camioneros.get(i).getNombre());
                fw.write("#");
                fw.write(camioneros.get(i).getApellidos());
                fw.write("#");
                fw.write(camioneros.get(i).getCorreo());
                fw.write("#");
                fw.write(camioneros.get(i).getTelefono());
                fw.write("#");
                fw.write(camioneros.get(i).getNacimiento());
                fw.write("#");
                fw.write(camioneros.get(i).getGenero());
                fw.write("#");
                fw.write(camioneros.get(i).getTransporteMercancias());
                fw.write("#");
                fw.write(camioneros.get(i).getComentarios());
                fw.write("#");
                fw.write(camioneros.get(i).getContratadoActualmente());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return camioneros;
    }

    public boolean checkASCII(Camionero camionero){

        String nombre=camionero.getNombre();
        int contador=0;

        for(int i=0; i<nombre.length(); i++){
            contador=contador+nombre.codePointAt(i);
        }

        if(contador>500){
            asciiValido=true;
        }else{
            asciiValido=false;
        }
        return asciiValido;
    }

    public boolean checkAparicionMensajeUltimoCamionero(){

        listarCamioneros();
        if(camioneros.size()==1){
            mensajeUltimoCamionero=true;
        }else{
            mensajeUltimoCamionero=false;
        }
        return mensajeUltimoCamionero;
    }
}