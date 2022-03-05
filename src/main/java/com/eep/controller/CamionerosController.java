package com.eep.controller;

import com.eep.component.CamioneroComponent;
import com.eep.service.CamioneroService;
import com.epp.model.Camionero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CamionerosController {

    @Autowired
    @Qualifier("camioneroService")
    private CamioneroService camioneroService;

    @Autowired
    @Qualifier("camioneroComponent")
    private CamioneroComponent camioneroComponent;

    //ALTA
    @GetMapping("/alta")
    public String irAlta(@ModelAttribute Camionero camionero, Model model) {
        model.addAttribute("camionero", new Camionero());
        return "alta";
    }

    @PostMapping("/comprobacionalta")
    public String verNuevoRegistro(@ModelAttribute("camionero") @Valid Camionero camionero, BindingResult result, String nombre, Model model) {

        if (result.hasErrors()) {
            return "alta";
        }

        if (camioneroService.checkASCII(camionero) == false) {
            model.addAttribute("mensaje", "El nombre del transportista no supera la longitud de carácteres mínima exigida: 500.");
            return "mensajes_usuarios";
        }

        camioneroService.anyadirCamionero(camionero, nombre);
        camioneroComponent.camioneroAnyadido(camionero);
        return "registroExitoso";
    }

    //LISTAR
    @GetMapping("/list")
    public ModelAndView listAllCamioneros() {
        ModelAndView mav = new ModelAndView("mostrado");
        mav.addObject("camioneros", camioneroService.listarCamioneros());
        camioneroComponent.listadoCamioneros();
        return mav;
    }

    //REG.OPERACIONES
    @GetMapping("/operaciones")
    public ModelAndView registroOperaciones() {
        ModelAndView mav = new ModelAndView("operaciones");
        mav.addObject("operaciones", camioneroComponent.listarOperaciones());
        camioneroComponent.operacionRegistro();
        return mav;
    }

    //BAJA
    @GetMapping("/baja")
    public String baja(Model model) {
        model.addAttribute("camioneros", new Camionero());
        return "baja";
    }

    @GetMapping("/nombre")
    public String buscarPorNombreYOApellidos(Model model,
                                             @RequestParam String nombre, String apellidos,
                                             @ModelAttribute("camioneros") Camionero camionero) {

        if (camioneroService.checkCoincidentes(nombre, apellidos) == true) {
            model.addAttribute("camionerosEncontrados", camioneroService.camionerosCoincidentes(nombre, apellidos));
            return "camionerosEncontrados";
        } else if (camioneroService.coincidenciaUnica(nombre, apellidos) == true) {
            if (camioneroService.checkAparicionMensajeUltimoCamionero() == true) {
                model.addAttribute("camionerosEncontrados", camioneroService.camionerosCoincidentes(nombre, apellidos));//devolverá un único camionero
                return "mensajeultimocamionero";
            } else {
                Camionero camioneroEnviadoAlRegistro = camioneroService.devolverCamioneroPorNombreYOApellidos(nombre, apellidos);//*
                model.addAttribute("camionerosPorNombre", camioneroService.borrarCamionerosPorNombreYOApellidos(nombre, apellidos));
                camioneroComponent.camioneroEliminado(camioneroEnviadoAlRegistro);//*
                return "redirect:/list";
            }
        } else {
            model.addAttribute("mensaje", "El usuario que usted busca no ha sido encontrado.");
            return "mensajes_usuarios";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String borrarPorId(@PathVariable int id) {
        Camionero camioneroEnviadoAlRegistro = camioneroService.devolverCamioneroPorId(id);//*
        camioneroService.borrarCamionerosId(id);
        camioneroComponent.camioneroEliminado(camioneroEnviadoAlRegistro);//*
        return "redirect:/list";
    }

    //MODIFICACIÓN
    @GetMapping("/busquedadecamionero")
    public String busquedaCamionero(Model model) {
        model.addAttribute("camionero", new Camionero());
        return "modificacion";
    }

    @GetMapping("/buscar")
    public String buscarCamionero(@RequestParam String nombre, String apellidos,
                                  @ModelAttribute("camionero") Camionero camionero,
                                  Model model) {
        if (camioneroService.checkCoincidentes(nombre, apellidos) == true) {
            model.addAttribute("camionerosEncontradosModificar", camioneroService.camionerosCoincidentes(nombre, apellidos));
            return "camionerosEncontradosModificar";
        } else if (camioneroService.coincidenciaUnica(nombre, apellidos) == true) {
            Camionero camioneroEnviado = camioneroService.devolverCamioneroPorNombreYOApellidos(nombre, apellidos);//devolución de un camionero
            model.addAttribute("camionero", camioneroEnviado);
            return "updateform";
        } else {
            model.addAttribute("mensaje", "El usuario que usted busca no ha sido encontrado.");
            return "mensajes_usuarios";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarPorId(@PathVariable int id,
                              Model model) {
        Camionero camioneroEnviado = camioneroService.devolverCamioneroPorId(id);//devolución de un camionero
        model.addAttribute("camionero", camioneroEnviado);
        return "updateform";
    }   

    @PostMapping("/modificacionrealizada")
    public String modificacionHecha(@ModelAttribute("camionero")
                                    @Valid Camionero camionero,
                                    BindingResult result,
                                    Model model) {

        if (result.hasErrors()) {
            return "updateform";
        }

        if (camioneroService.checkASCII(camionero) == false) {
            model.addAttribute("mensaje", "El nombre del transportista no supera la longitud de carácteres mínima exigida: 500.");
            return "mensajes_usuarios";
        }

        camioneroComponent.camioneroModificado(camionero);
        camioneroService.modificarCamionero(camionero);

        return "updateexitoso";
    }

    //BIENVENIDA
    @GetMapping("/bienvenida")
    public String bienvenida(Model model) {
        return "bienvenida";
    }
}