package com.eep.component;

import com.epp.model.Camionero;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component("camioneroComponent")
public class CamioneroComponent {

    ArrayList<Camionero> camioneros;

    private static final Log LOG = LogFactory.getLog(CamioneroComponent.class);

    public void camioneroAnyadido(Camionero camionero) {

        String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        LOG.info("ALTA - "+camionero.getNombre()+" | "+camionero.getApellidos()+" | "+camionero.getCorreo()+" | "+camionero.getTelefono()+" | "+camionero.getNacimiento()+" | "+camionero.getGenero()+" | "+camionero.getTransporteMercancias()+" | "+camionero.getContratadoActualmente()+" | "+camionero.getComentarios());

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\component\\logs.txt", true);

            fw.write(horaActual);
            fw.write(" - ");
            fw.write("ALTA");
            fw.write(" - ");
            fw.write(camionero.getNombre());
            fw.write(", ");
            fw.write(camionero.getApellidos());
            fw.write(", ");
            fw.write(camionero.getCorreo());
            fw.write(", ");
            fw.write(camionero.getTelefono());
            fw.write(", ");
            fw.write(camionero.getNacimiento());
            fw.write(", ");
            fw.write(camionero.getGenero());
            fw.write(", ");
            fw.write(camionero.getTransporteMercancias());
            fw.write(", ");
            fw.write(camionero.getContratadoActualmente());
            fw.write(", ");
            fw.write(camionero.getComentarios());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void camioneroEliminado(Camionero camionero) {

        LOG.info("BAJA - "+camionero.getNombre()+" | "+camionero.getApellidos()+" | "+camionero.getCorreo()+" | "+camionero.getTelefono()+" | "+camionero.getNacimiento()+" | "+camionero.getGenero()+" | "+camionero.getTransporteMercancias()+" | "+camionero.getContratadoActualmente()+" | "+camionero.getComentarios());

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\component\\logs.txt", true);

            String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            fw.write(horaActual);
            fw.write(" - ");
            fw.write("BAJA");
            fw.write(" - ");
            fw.write(camionero.getNombre());
            fw.write(", ");
            fw.write(camionero.getApellidos());
            fw.write(", ");
            fw.write(camionero.getCorreo());
            fw.write(", ");
            fw.write(camionero.getTelefono());
            fw.write(", ");
            fw.write(camionero.getNacimiento());
            fw.write(", ");
            fw.write(camionero.getGenero());
            fw.write(", ");
            fw.write(camionero.getTransporteMercancias());
            fw.write(", ");
            fw.write(camionero.getContratadoActualmente());
            fw.write(", ");
            fw.write(camionero.getComentarios());
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Listar camioneros
    public List<Camionero> listarCamionerosEnComponente() {

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

    public void listadoCamioneros() {

        String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        listarCamionerosEnComponente();
        LOG.info(horaActual+" - LISTADO:");
        for (int i = 0; i < camioneros.size(); i++) {
            LOG.info(camioneros.get(i).getNombre()+" "+camioneros.get(i).getApellidos());
        }

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\component\\logs.txt", true);

            fw.write(horaActual);
            fw.write(" - ");
            fw.write("LISTADO");
            fw.write(" - ");

            listarCamionerosEnComponente();
            for (int i = 0; i < camioneros.size(); i++) {
                fw.write(camioneros.get(i).getNombre());
                fw.write(" ");
                fw.write(camioneros.get(i).getApellidos());
                if (i != camioneros.size() - 1) {
                    fw.write(", ");
                }
            }
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void camioneroModificado(Camionero camionero) {

        String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        LOG.info(horaActual+" - "+"MODIFICACIONES REALIZADAS:");

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\component\\logs.txt", true);

            fw.write(horaActual);
            fw.write(" - ");
            fw.write("MODIFICACIÓN");
            fw.write(" - ");

            listarCamionerosEnComponente();
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

                    fw.write("USUARIO CON ID: "+camioneros.get(i).getId()+" - ");

                    if (camioneros.get(i).getNombre().equals(nombre) == false) {
                        fw.write("NOMBRE: ");
                        fw.write(camioneros.get(i).getNombre());
                        fw.write(" / ");
                        fw.write(nombre);
                        fw.write(" | ");
                        LOG.info("NOMBRE MODIFICADO: "+camioneros.get(i).getNombre()+" / "+nombre);
                    }

                    if (camioneros.get(i).getApellidos().equals(camionero.getApellidos()) == false) {
                        fw.write("APELLIDOS: ");
                        fw.write(camioneros.get(i).getApellidos());
                        fw.write(" / ");
                        fw.write(apellidos);
                        fw.write(" | ");
                        LOG.info("APELLIDOS MODIFICADOS: "+camioneros.get(i).getApellidos()+" / "+apellidos);
                    }

                    if (camioneros.get(i).getCorreo().equals(camionero.getCorreo()) == false) {
                        fw.write("CORREO: ");
                        fw.write(camioneros.get(i).getCorreo());
                        fw.write(" / ");
                        fw.write(correo);
                        fw.write(" | ");
                        LOG.info("CORREO MODIFICADO: "+camioneros.get(i).getCorreo()+" / "+correo);
                    }

                    if (camioneros.get(i).getTelefono().equals(camionero.getTelefono()) == false) {
                        fw.write("TELÉFONO: ");
                        fw.write(camioneros.get(i).getTelefono());
                        fw.write(" / ");
                        fw.write(telefono);
                        fw.write(" | ");
                        LOG.info("TELÉFONO MODIFICADO: "+camioneros.get(i).getTelefono()+" / "+telefono);
                    }

                    if (camioneros.get(i).getNacimiento().equals(camionero.getNacimiento()) == false) {
                        fw.write("NACIMIENTO: ");
                        fw.write(camioneros.get(i).getNacimiento());
                        fw.write(" / ");
                        fw.write(nacimiento);
                        fw.write(" | ");
                        LOG.info("NACIMIENTO MODIFICADO: "+camioneros.get(i).getNacimiento()+" / "+nacimiento);
                    }

                    if (camioneros.get(i).getGenero().equals(camionero.getGenero()) == false) {
                        fw.write("GÉNERO: ");
                        fw.write(camioneros.get(i).getGenero());
                        fw.write(" / ");
                        fw.write(genero);
                        fw.write(" | ");
                        LOG.info("GÉNERO MODIFICADO: "+camioneros.get(i).getGenero()+" / "+genero);
                    }

                    if (camioneros.get(i).getTransporteMercancias().equals(camionero.getTransporteMercancias()) == false) {
                        fw.write("TRANSPORTE MERCANCÍAS: ");
                        fw.write(camioneros.get(i).getTransporteMercancias());
                        fw.write(" / ");
                        fw.write(transporteMercancias);
                        fw.write(" | ");
                        LOG.info("TRANSPORTE DE MERCANCÍAS: "+camioneros.get(i).getTransporteMercancias()+" / "+transporteMercancias);
                    }

                    if (camioneros.get(i).getComentarios().equals(camionero.getComentarios()) == false) {
                        fw.write("COMENTARIOS: ");
                        fw.write(camioneros.get(i).getComentarios());
                        fw.write(" / ");
                        fw.write(comentarios);
                        fw.write(" | ");
                        LOG.info("COMENTARIOS: "+camioneros.get(i).getComentarios()+" / "+comentarios);
                    }

                    if (camioneros.get(i).getContratadoActualmente().equals(camionero.getContratadoActualmente()) == false) {
                        fw.write("CONTRATADO ACTUALMENTE: ");
                        fw.write(camioneros.get(i).getContratadoActualmente());
                        fw.write(" / ");
                        fw.write(contratadoActualmente);
                        LOG.info("CONTRATADO ACTUALMENTE: "+camioneros.get(i).getContratadoActualmente()+" / "+contratadoActualmente);
                    }
                    break;
                }
            }
            fw.write("\n");
            fw.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void operacionRegistro() {

        LOG.info("Operación registro.");

        try {
            FileWriter fw = new FileWriter("src\\main\\java\\com\\eep\\component\\logs.txt", true);

            String horaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            fw.write(horaActual);
            fw.write(" - ");
            fw.write("OPERACIÓN REGISTRO");

            fw.write("\n");
            fw.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    ArrayList operaciones;

    //Listar operaciones
    public List listarOperaciones() {

        File f = null;
        FileReader ficheroLeer = null;
        BufferedReader ficheroBuf = null;

        try {
            f = new File("src\\main\\java\\com\\eep\\component\\logs.txt");
            ficheroLeer = new FileReader(f);
            ficheroBuf = new BufferedReader(ficheroLeer);
            operaciones = new ArrayList();

            String linea;

            while ((linea = ficheroBuf.readLine()) != null) {
                operaciones.add(linea);
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
                    return operaciones;
                }
            } catch (IOException e) {
                System.out.println("No se puede cerrar el archivo." + e.getMessage());
            }
        }
        return operaciones;
    }
}
