package com.project.consorcio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.consorcio.Service.MedicamentoServices;
import com.project.consorcio.Service.TipoMedicamentoServices;
import com.project.consorcio.entity.Medicamento;
import com.project.consorcio.entity.TipoMedicamento;

//anotacion que indica que la clase es un controlador por lo tanto permite recibir peticiones de los clientes y envia respuesta
@Controller
//permite crear una direccion URL o Ruta para acceder al controlador
@RequestMapping("/medicamento")
public class MedicamentoController {
	@Autowired
	private MedicamentoServices servicioMed;
	
	@Autowired
	private TipoMedicamentoServices servicioTipo;
	
	@RequestMapping("/lista")
	//model es una interfaz que permite crear atributos que luego seran enviados a la pagina
	public String index(Model model) {
		model.addAttribute("medicamentos",servicioMed.listarTodos());
		model.addAttribute("tipos", servicioTipo.listarTodos());
		return "medicamento";
	}
	//@RequestParam, permite 
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
						 @RequestParam("nombre") String nom, 
						 @RequestParam("descripcion") String des,
						 @RequestParam("stock")int stock,
						 @RequestParam("precio")double pre,
						 @RequestParam("fecha") String fec,
						 @RequestParam("Tipo") int codTipo,RedirectAttributes redirect) {
						
		try {
			//crear objeto de la entidad medicamento
			Medicamento med=new Medicamento();
			//setear atributos de los objetos "med" con los parametros
			med.setNombre(nom);
			med.setDescripcion(des);
			med.setStock(stock);
			med.setPrecio(pre);
			med.setFecha(LocalDate.parse(fec));
			//crear objeto de la entidad tipo medicamento
			TipoMedicamento tm= new TipoMedicamento();
			//setear atributo "codigo del objeto" "tm" con l parametro codTipo
			tm.setCodigo(codTipo);
			//invocar l metodo set tipoy enviar el objeto "tm"
			med.setTipo(tm);
			//validar parametro cod
			if(cod==0) {
			//inovcar metodo registrar
			servicioMed.registrar(med);
			//crear atributo tipo flash
			redirect.addFlashAttribute("MENSAJE","Medicamento registrado");
			}
			else {
				//setear atributo codigo
				med.setCodigo(cod);
				servicioMed.actualizar(med);
				redirect.addFlashAttribute("MENSAJE","Medicamento actualizado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/medicamento/lista";
	}
	//ruta o direccion url para buscar medicamento segun codigo
	@RequestMapping("/buscar")
	@ResponseBody
	public Medicamento buscar(@RequestParam("codigo") Integer cod) {
		return servicioMed.buscarPorID(cod);
	}
	
}
