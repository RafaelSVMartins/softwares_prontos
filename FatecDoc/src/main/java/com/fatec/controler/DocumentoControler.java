package com.fatec.controler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fatec.model.Aluno;
import com.fatec.model.Documento;
import com.fatec.model.StatusDocumento;
import com.fatec.repository.AlunoRepository;
import com.fatec.repository.DocumentoRepository;
import com.fatec.service.ChecaData;

@Controller
@RequestMapping("/documentos")
public class DocumentoControler {
	@Autowired
	private DocumentoRepository drepository;
	@Autowired
	private AlunoRepository arepository;
	
	private ChecaData check;
	
	private Aluno aluno;
	private static final String CadastroV="CadastroDocumento";
	
	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("alunoid") final Long alunoid, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CadastroV);
		this.aluno = this.arepository.findOne(alunoid);
 		mv.addObject(new Documento());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Documento documento, Errors erro, RedirectAttributes attributes){
		if (erro.hasErrors()) {
			return CadastroV;
		}
		try {
			documento.setAluno(aluno);
			this.check = new ChecaData(documento.getRecebido());
			if (documento.isPendente()) {
				documento.setAnterior_2meses(this.check.testeCalendar());
			}
			this.drepository.save(documento);
			attributes.addFlashAttribute("alunoid",documento.getAluno().getId());
			return "redirect:/documentos";
		} catch(IllegalArgumentException e) {
			erro.rejectValue("recebido",null,e.getMessage());
			return CadastroV;
		}
	}
	
	@RequestMapping(value="{id}")
	public ModelAndView edicao(@PathVariable Long id, RedirectAttributes attributes) {
		Documento documento = this.drepository.findOne(id);
		ModelAndView mv = new ModelAndView(CadastroV);
		mv.addObject(documento);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		this.drepository.delete(id);
		attributes.addAttribute("mensagem","Titulo excluído com sucesso!");
		return "redirect:/documentos";
	}
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Documento> todosDocumentos = this.drepository.findAll();
		ModelAndView mv = new ModelAndView("PesquisaDocumento");
		mv.addObject("documentos",todosDocumentos);
		return mv;
	}
	@ModelAttribute("todosStatusDocumento")
	public List<StatusDocumento> todosStatusDocumentos() {
		return Arrays.asList(StatusDocumento.values());
	}
	
}
