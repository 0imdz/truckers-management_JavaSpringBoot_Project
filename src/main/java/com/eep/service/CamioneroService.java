package com.eep.service;

import com.epp.model.Camionero;

import java.util.List;


public interface CamioneroService {
	public abstract void anyadirCamionero(Camionero camionero, String nombre);
	public List<Camionero> listarCamioneros();
	public boolean checkCoincidentes(String nombre, String apellidos);
	public Camionero buscarCamioneroPorNombre(String nombre, String apellidos);
	public void modificarCamionero(Camionero camionero);
	public boolean checkASCII(Camionero camionero);
	public List<Camionero> camionerosCoincidentes(String nombre, String apellidos);
	public boolean coincidenciaUnica(String nombre, String apellidos);
	public List<Camionero> borrarCamionerosPorNombreYOApellidos(String nombre, String apellidos);
	public List<Camionero> borrarCamionerosId(int id);
	public Camionero devolverCamioneroPorNombreYOApellidos(String nombre, String apellidos);
	public Camionero devolverCamioneroPorId(int id);
	public boolean checkAparicionMensajeUltimoCamionero();
}
