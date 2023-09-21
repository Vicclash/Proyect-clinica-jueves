package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.Service.DistritoServices;
import com.project.consorcio.Service.EspecialidadServices;
import com.project.consorcio.Service.MedicoServices;
import com.project.consorcio.Service.SedeServices;
import com.project.consorcio.entity.Distrito;
import com.project.consorcio.entity.Especialidad;
import com.project.consorcio.entity.Medico;
import com.project.consorcio.entity.Sede;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	 private MedicoServices servicioMedi;
	
	@Autowired
	 private SedeServices servicioSede;
	
	@Autowired
	 private EspecialidadServices servicioEspe;
	
	@Autowired
	 private DistritoServices servicioDist;
	
	@RequestMapping("/lista")
	public String index (Model model) {
		model.addAttribute("medicos",servicioMedi.listarTodos());
		model.addAttribute("Sedes",servicioSede.listarTodos());
		model.addAttribute("Especialidad",servicioEspe.listarTodos());
		model.addAttribute("Distrito",servicioDist.listarTodos());
		return "medico";
	}
@RequestMapping("/grabar")
public String grabar(@RequestParam ("codigo") Integer cod,
					 @RequestParam ("nombre") String nom,
					 @RequestParam ("Apellido") String ape,
					 @RequestParam ("fechaNacimineto") String fec,
					 @RequestParam ("sexo") String sexo,
					 @RequestParam ("estadoCivil") String est,
					 @RequestParam ("dni") int dni,
					 @RequestParam ("sueldo") int sue,
					 @RequestParam ("direccion") String dir,
					 @RequestParam ("Especialidad") int codEspe,
					 @RequestParam ("Sede") int codSede,
					 @RequestParam ("Distrito") int codDis,
					 RedirectAttributes redirect) {
	try {
		Medico med=new Medico();
		med.setNombre(nom);
		med.setApellido(ape);
		med.setFechaNacimiento(LocalDate.parse(fec));
		med.setSexo(sexo);
		med.setEstadoCivil(est);
		med.setDni(dni);
		med.setSueldo(sue);
		med.setDireccion(dir);
		Especialidad esp=new Especialidad();
		esp.setCodigo(codEspe);
		med.setEspecialidad(esp);
		Sede sed=new Sede();
		sed.setCodigo(codSede);
		med.setSede(sed);
		Distrito dis=new Distrito();
		dis.setCodigo(codDis);
		med.setDistrito(dis);
		if (cod==0) {
			servicioMedi.registrar(med);
			redirect.addFlashAttribute("MENSAJE","Medico registrado");
		}
		else {
			med.setCodigo(cod);
			servicioMedi.actualizar(med);
			redirect.addFlashAttribute("MENSAJE","Medico actualizado");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "redirect:/medico/lista";
}
@RequestMapping("/buscar")
@ResponseBody
public Medico buscar(@RequestParam("codigo") Integer cod) {
	return servicioMedi.buscarPorID(cod);
}
}
